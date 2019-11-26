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
    
    @Test
    public void postAddsBookToDatabase() throws Exception {
        MvcResult res = mockMvc.perform(post("/books").param("title", "test").param("author", "pasi").param("isbn", "123-123-32")).andReturn();
        MvcResult res2 = mockMvc.perform(get("/")).andReturn();
        String content = res2.getResponse().getContentAsString();
        Assert.assertTrue(content.contains("123-123-32"));
    }
    
    @Test
    public void cantAddTwoSameISBN() throws Exception {
        mockMvc.perform(post("/books").param("title", "test").param("author", "pasi").param("isbn", "123-123-32")).andReturn();
        mockMvc.perform(post("/books").param("title", "test").param("author", "kalle").param("isbn", "123-123-32")).andReturn();
        MvcResult res2 = mockMvc.perform(get("/")).andReturn();
        String content = res2.getResponse().getContentAsString();
        Assert.assertFalse(content.contains("kalle"));
    }
}
