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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

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
    public String frontPage(Model model, @ModelAttribute Book book) throws SQLException {
        model.addAttribute("list", bookDao.list());
        return "books";
    }



    @PostMapping("/books")
    public String saveBook(Model model,@Valid @ModelAttribute Book book, BindingResult bindingResult) throws SQLException {
        if (bookDao.findByIsbn(book.getIsbn()) != null) {
            bindingResult.rejectValue("isbn", "error.book", "Book with this ISBN already added.");
        }

        if(bindingResult.hasErrors()) {
            model.addAttribute("list", bookDao.list());
            model.addAttribute("book", book);
            return "books";
        }



        bookDao.create(book);
        return "redirect:/";
    }

    public void setDao(BookDao dao) {
        this.bookDao = dao;
    }

}
