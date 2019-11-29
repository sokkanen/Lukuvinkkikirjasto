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
import dtt.lukuvinkkikirjasto.demo.domain.Book;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        assertTrue(content.contains("Books"));
    }
    
    @Test
    public void postAddsBookToDatabase() throws Exception {
        controller.setDao(bookDao);
        mockMvc.perform(post("/books").param("title", "test").param("author", "pasi").param("isbn", "9789521439087")).andReturn();
        
        Book book = bookDao.findByIsbn("9789521439087");
        
        assertEquals(book.getIsbn(), "9789521439087");
    }

    @Test
    public void deleteRemovesBookFromDatabase() throws Exception {
        controller.setDao(bookDao);
        mockMvc.perform(post("/books").param("title", "test").param("author", "pasi").param("isbn", "9789521439087")).andReturn();
        MvcResult res = mockMvc.perform(get("/")).andReturn();
        String content = res.getResponse().getContentAsString();
        assertTrue(content.contains("pasi"));

        Book book = bookDao.findByIsbn("9789521439087");
        int id = book.getId();
        mockMvc.perform(post("/books/delete/" + id)).andReturn();
        res = mockMvc.perform(get("/")).andReturn();
        content = res.getResponse().getContentAsString();

        assertFalse(content.contains("pasi"));
    }
    
    @Test
    public void cantAddTwoSameISBN() throws Exception {
        controller.setDao(bookDao);
        
        mockMvc.perform(post("/books").param("title", "test").param("author", "nakki").param("isbn", "9789521439087")).andReturn();
        mockMvc.perform(post("/books").param("title", "test").param("author", "kalle").param("isbn", "9789521439087")).andReturn();
        
        MvcResult res2 = mockMvc.perform(get("/")).andReturn();
        
        String content = res2.getResponse().getContentAsString();
        
        assertFalse(content.contains("kalle"));
        assertTrue(content.contains("nakki"));
    }
}