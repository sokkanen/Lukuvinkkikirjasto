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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping(value={"/", "/books"})
    public String frontPage(Model model, @ModelAttribute Book book) throws SQLException {
        model.addAttribute("list", bookDao.list());
        model.addAttribute("editmode", false);
        return "books";
    }
    
    @PostMapping("/books")
    public String saveBook(Model model, @Valid @ModelAttribute Book book, BindingResult bindingResult) throws SQLException {
        
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

    @PostMapping("/books/delete/{bookId}")
    public String deleteBook(@PathVariable String bookId) throws SQLException {
        Book book = bookDao.findById(Integer.parseInt(bookId));
        bookDao.delete(book);
        return "redirect:/";
    }

    @GetMapping("/books/edit/{id}")
    public String editBook(Model model, @PathVariable(value="id") String id) throws SQLException {
        Book book = bookDao.findById(Integer.parseInt(id));
        if(book != null) {
            if(book.getIsbn() == "error"){
                book.setIsbn("");
            }
            model.addAttribute("editmode", true);
            model.addAttribute("book", book);
            model.addAttribute("list", bookDao.list());

            return "books";
        }
            return "redirect:/";
    }
    
    public void setDao(BookDao dao) {
        this.bookDao = dao;
    }

    @PostMapping("/books/edit/{id}")
    public String restfullyEditBook(Model model, @Valid @ModelAttribute Book book, BindingResult bindingResult) throws SQLException {
        Book old = bookDao.findByIsbn(book.getIsbn());
        if (old != null && old.getId() != book.getId()) {
            bindingResult.rejectValue("isbn", "error.book", "Book with this ISBN already added.");
        }
        if(bindingResult.hasErrors()) {
            model.addAttribute("list", bookDao.list());
            model.addAttribute("editmode", true);
            model.addAttribute("book", book);
            return "books";
        }

        bookDao.editBook(book);
        
        return "redirect:/books";
    }
    
}
