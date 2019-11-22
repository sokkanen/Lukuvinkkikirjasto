/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtt.lukuvinkkikirjasto.demo.controller;

import dtt.lukuvinkkikirjasto.demo.dao.BookDao;
import dtt.lukuvinkkikirjasto.demo.domain.Book;
import java.sql.SQLException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author sebserge
 */
@Controller
public class BookController {
    
    private BookDao bookDao;
    public BookController(BookDao dao) {
        this.bookDao = dao;
    }
    
    @GetMapping("/")
    public String getAllBooks(Model model) throws SQLException {
        model.addAttribute("list", bookDao.list());
        return "books";
    }
    
    @PostMapping("/books")
    public String saveBook(@RequestParam String name, @RequestParam String author, @RequestParam String isbn) throws SQLException {
        Book b = new Book(author, name, isbn, false);
        bookDao.create(b);
        return "redirect:/";
    }
    
}
