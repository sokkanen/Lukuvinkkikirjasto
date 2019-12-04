package dtt.lukuvinkkikirjasto.demo.service;

import dtt.lukuvinkkikirjasto.demo.dao.BookDao;
import dtt.lukuvinkkikirjasto.demo.domain.Book;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.sql.SQLException;

@Service
public class BookService {
    public Model returnModelForBook(Model model, BookDao bookDao, Book book, Boolean editmode) throws SQLException {
        model.addAttribute("listread", bookDao.listRead());
        model.addAttribute("list", bookDao.listUnread());
        model.addAttribute("editmode", editmode);
        model.addAttribute("book", book);
        return model;
    }
}
