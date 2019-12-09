/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtt.lukuvinkkikirjasto.demo.controller;

/**
 *
 * @author sebserge
 */
import dtt.lukuvinkkikirjasto.demo.BaseTest;
import dtt.lukuvinkkikirjasto.demo.dao.BookDao;
import dtt.lukuvinkkikirjasto.demo.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

@AutoConfigureMockMvc
public class BookControllerTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookController controller;

    @Test
    public void statusOk() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    public void responseContainsHeader() throws Exception {
        controller.setDao(bookDao);
        MvcResult res = mockMvc.perform(get("/")).andReturn();

        String content = res.getResponse().getContentAsString();
        assertTrue(content.contains("books"));
    }

    @Test
    public void postAddsBookToDatabase() throws Exception {
        controller.setDao(bookDao);
        mockMvc.perform(post("/books").param("title", "").param("author", "").param("isbn", "9789511290384"))
                .andReturn();

        Book book = bookDao.findByIsbn("9789511290384");

        assertEquals(book.getIsbn(), "9789511290384");
    }

    @Test
    public void gettingBookInfoWithNoIsbnRedirects() throws Exception {
        controller.setDao(bookDao);
        mockMvc.perform(post("/books").param("title", "Title").param("author", "Author").param("isbn", ""))
                .andReturn();
        String bookId = bookDao.list().get(0).getId();
        MvcResult res = mockMvc.perform(get("/books/info/" + bookId)).andReturn();
        int status = res.getResponse().getStatus();
        assertEquals(302, status);
        String location = res.getResponse().getHeader("Location");
        assertEquals("/books/" + bookId, location);
    }

    @Test
    public void bookWithNoTitleAndNoIsbnReturnsError() throws Exception {
        controller.setDao(bookDao);
        MvcResult res = mockMvc.perform(post("/books").param("title", "").param("author", "Author").param("isbn", ""))
                .andReturn();
        String content = res.getResponse().getContentAsString();
        assertTrue(content.contains("Must inlude either Title or valid ISBN"));
    }

    @Test
    public void bookWithFalseISBNGivesBookATitle() throws Exception {
        MvcResult res = mockMvc.perform(post("/books").param("title", "").param("author", "").param("isbn", "0657283339")).andReturn();
        MvcResult res2 = mockMvc.perform(get("/books")).andReturn();
        String content = res2.getResponse().getContentAsString();
        assertTrue(content.contains("Additional information not found"));
    }

    @Test
    public void bookWithFalseISBNGivesBookGivenTitle() throws Exception {
        MvcResult res = mockMvc.perform(post("/books").param("title", "Should be").param("author", "").param("isbn", "0-3988-8523-0")).andReturn();
        MvcResult res2 = mockMvc.perform(get("/books")).andReturn();
        String content = res2.getResponse().getContentAsString();
        assertTrue(content.contains("Should be"));
    }

    @Test
    public void bookCanBeMarkedReadByPost() throws Exception {
        controller.setDao(bookDao);
        mockMvc.perform(post("/books").param("title", "Title").param("author", "Author").param("isbn", ""))
                .andReturn();
        Book book = bookDao.list().get(0);
        String bookId = book.getId();
        assertFalse(book.isRead());

        mockMvc.perform(post("/books/editread/" + bookId).param("title", "Title").param("author", "Author").param("isbn", ""))
                .andReturn();
        assertTrue(bookDao.findById(bookId).isRead());
    }
    @Test
    public void bookInfoCanBeGetWithNoIsbn() throws Exception {
        controller.setDao(bookDao);
        mockMvc.perform(post("/books").param("title", "Title").param("author", "Author").param("isbn", ""))
                .andReturn();
        String bookId = bookDao.list().get(0).getId();
        MvcResult res = mockMvc.perform(get("/books/" + bookId)).andReturn();
        String content = res.getResponse().getContentAsString();
        assertTrue(content.contains("Title"));
        assertTrue(content.contains("Author"));
    }

    @Test
    public void bookInfoCanBeGetWithIsbn() throws Exception {
        controller.setDao(bookDao);
        String isbn = "9789511290384";
        mockMvc.perform(post("/books").param("title", "").param("author", "").param("isbn", isbn))
                .andReturn();
        Book book = bookDao.findByIsbn(isbn);
        String title = book.getTitle();
        String author = book.getAuthor();
        String bookId = book.getId();
        MvcResult res = mockMvc.perform(get("/books/info/" + bookId)).andReturn();
        String content = res.getResponse().getContentAsString();
        assertTrue(content.contains(title));
        assertTrue(content.contains(author));
        assertTrue(content.contains(isbn));
    }


    @Test
    public void deleteRemovesBookFromDatabase() throws Exception {
        controller.setDao(bookDao);
        mockMvc.perform(post("/books").param("title", "").param("author", "").param("isbn", "9789511290384"))
                .andReturn();
        MvcResult res = mockMvc.perform(get("/")).andReturn();
        String content = res.getResponse().getContentAsString();
        assertTrue(content.contains("Ruby"));

        Book book = bookDao.findByIsbn("9789511290384");
        String id = book.getId();
        mockMvc.perform(post("/books/delete/" + id)).andReturn();
        res = mockMvc.perform(get("/")).andReturn();
        content = res.getResponse().getContentAsString();

        assertFalse(content.contains("Ruby"));
    }

    @Test
    public void cantAddTwoSameISBN() throws Exception {
        controller.setDao(bookDao);

        mockMvc.perform(post("/books").param("title", "").param("author", "").param("isbn", "9789511290384"))
                .andReturn();
        mockMvc.perform(post("/books").param("title", "").param("author", "").param("isbn", "9789511290384"))
                .andReturn();
        List<Book> books = bookDao.list();
        int booksFound = 0;
        for (Book b : books) {
            if (b.getIsbn().equals("9789511290384")) {
                booksFound++;
            }
        }
        assertEquals(1, booksFound);
    }

    @Test
    public void editingBookMovesDataToTheForm() throws Exception {
        controller.setDao(bookDao);
        mockMvc.perform(post("/books").param("title", "").param("author", "").param("isbn", "9789511290384"))
                .andReturn();
        String bookId = bookDao.list().get(0).getId();
        MvcResult result = mockMvc.perform(get("/books/edit/" + bookId)).andReturn();
        String content = result.getResponse().getContentAsString();

        assertTrue(content.contains("Edit book"));
        assertTrue(content.contains("placeholder=\"Title\" value=\"Hello Ruby\""));
        assertTrue(content.contains("placeholder=\"ISBN\" value=\"9789511290384\""));
    }

    @Test
    public void editingBookWithoutISBNFieldIsEmpty() throws Exception {
        mockMvc.perform(post("/books").param("title", "test").param("author", "nakki")).andReturn();
        String bookId = bookDao.list().get(0).getId();
        MvcResult result = mockMvc.perform(get("/books/edit/" + bookId)).andReturn();
        String content = result.getResponse().getContentAsString();

        assertTrue(content.contains("Edit book"));
        assertTrue(content.contains("placeholder=\"Title\" value=\"test\""));
        assertTrue(content.contains("placeholder=\"ISBN\" value=\"\"/>"));
    }

    @Test
    public void tryToEditBookThatDoesntExistsRedirectsBack() throws Exception {
        mockMvc.perform(get("/books/edit/100")).andExpect(redirectedUrl("/")).andReturn();
    }

    @Test
    public void editingBookUpdatesTheBookWithSameID() throws Exception {
        mockMvc.perform(post("/books").param("title", "test").param("author", "nakki")).andReturn();
        MvcResult result = mockMvc.perform(get("/books")).andReturn();
        String content = result.getResponse().getContentAsString();
        assertTrue(content.contains("test"));

        String bookId = bookDao.list().get(0).getId();

        mockMvc.perform(post("/books/edit/" + bookId).param("title", "testaus").param("author", "nakki")).andReturn();
        result = mockMvc.perform(get("/books")).andReturn();
        content = result.getResponse().getContentAsString();
        assertTrue(content.contains("testaus"));
    }

    @Test
    public void tryToEditBookAndAddSameISBNReturnsError() throws Exception {
        mockMvc.perform(post("/books").param("title", "test").param("author", "nakki").param("isbn", "9789521439087"))
                .andReturn();
        mockMvc.perform(post("/books").param("title", "test2").param("author", "nakki2")).andReturn();
        mockMvc.perform(post("/books/edit/2").param("title", "testaus2").param("author", "nakki5").param("isbn",
                "9789521439087")).andReturn();
        MvcResult result = mockMvc.perform(get("/books")).andReturn();
        String content = result.getResponse().getContentAsString();
        assertFalse(content.contains("testaus"));
        assertFalse(content.contains("nakki5"));
    }

    @Test
    public void tryToEditABookThatDoesntExistsReturnsError() throws Exception {
        mockMvc.perform(
                post("/books/edit/100").param("title", "test").param("author", "nakki").param("isbn", "9789521439087"))
                .andReturn();
        MvcResult result = mockMvc.perform(get("/books")).andReturn();
        String content = result.getResponse().getContentAsString();
        assertFalse(content.contains("nakki"));
    }

}