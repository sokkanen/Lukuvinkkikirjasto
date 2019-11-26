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
import dtt.lukuvinkkikirjasto.demo.dao.BookDao;
import dtt.lukuvinkkikirjasto.demo.domain.Book;
import java.util.List;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    private BookDao dao;
    
    @Test
    public void statusOk() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }
    
    @Test
    public void responseContainsHeader() throws Exception {
        MvcResult res = mockMvc.perform(get("/")).andReturn();
        
        String content = res.getResponse().getContentAsString();
        Assert.assertTrue(content.contains("Books"));
    }
    
//    @Ignore("Test skipped for now.")
//    @Test
    public void postAddsBookToDatabase() throws Exception {
        MvcResult res = mockMvc.perform(post("/books").param("title", "test").param("author", "pasi").param("isbn", "123-123")).andReturn();
        
        List<Book> books = dao.list();
        Assert.assertTrue(books.stream().anyMatch(book -> book.getAuthor().equals("pasi")));
    }
}
