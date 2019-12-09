package dtt.lukuvinkkikirjasto.demo.service;

import dtt.lukuvinkkikirjasto.demo.bookdata.BookDto;
import dtt.lukuvinkkikirjasto.demo.bookdata.IsbnApiCaller;
import dtt.lukuvinkkikirjasto.demo.dao.BookDao;
import dtt.lukuvinkkikirjasto.demo.domain.Book;
import dtt.lukuvinkkikirjasto.demo.domain.Fireworks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.sql.SQLException;

@Service
public class BookService {

    Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    IsbnApiCaller isbnApiCaller;

    public Model returnModelForBook(Model model, BookDao bookDao, Book book, Boolean editmode, Boolean newbook) throws SQLException {
        model.addAttribute("listread", bookDao.listRead());
        model.addAttribute("list", bookDao.listUnread());
        model.addAttribute("editmode", editmode);
        model.addAttribute("book", book);
        model.addAttribute("newbook", newbook);
        
        
        return model;
    }

    private String formatTitle(String title) {
        return title.substring(1,title.length() - 1);
    }

    private String formatAuthor(String author) {
        return author.split(":")[0];
    }

    public Book ifIsbnFillBookInfo(Book book) throws IOException {

        if (!book.getIsbn().isEmpty()) {
            BookDto bookDto = isbnApiCaller.getBookDataFromIsbn(book.getIsbn());
            if (bookDto.getAuthors().size() != 0 && !bookDto.getAuthors().isEmpty()) {
                book.setAuthor(formatAuthor(bookDto.getAuthors().get(0)));
            }

            // Additional information not found as title if form title and API title empty
            if (book.getTitle().isEmpty() && bookDto.getTitle().equals("'Additional information not found'")) {
                book.setTitle(formatTitle(bookDto.getTitle()));
            } else if (!book.getTitle().isEmpty() && bookDto.getTitle().equals("'Additional information not found'")) {
                return book;
            } else {
                book.setTitle(formatTitle(bookDto.getTitle()));
            }

        }
        return book;
    }
}
