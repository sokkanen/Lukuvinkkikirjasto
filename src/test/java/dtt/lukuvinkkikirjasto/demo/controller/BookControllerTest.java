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
        MvcResult res = mockMvc.perform(get("/")).andReturn();
        
        String content = res.getResponse().getContentAsString();
        Assert.assertTrue(content.contains("Books"));
    }
    
    @Test
    public void postAddsBookToDatabase() throws Exception {
        controller.setDao(bookDao);
        MvcResult res = mockMvc.perform(post("/books").param("title", "test").param("author", "pasi").param("isbn", "951-98548-9-4")).andReturn();
        
        Book book = bookDao.findByIsbn("951-98548-9-4");
        
        Assert.assertEquals(book.getIsbn(), "951-98548-9-4");
    }
    
    @Test
    public void cantAddTwoSameISBN() throws Exception {
        controller.setDao(bookDao);
        
        mockMvc.perform(post("/books").param("title", "test").param("author", "nakki").param("isbn", "951-98548-9-4")).andReturn();
        mockMvc.perform(post("/books").param("title", "test").param("author", "kalle").param("isbn", "951-98548-9-4")).andReturn();
        
        MvcResult res2 = mockMvc.perform(get("/")).andReturn();
        
        String content = res2.getResponse().getContentAsString();
        
        Assert.assertFalse(content.contains("kalle"));
        Assert.assertTrue(content.contains("nakki"));
    }
}
