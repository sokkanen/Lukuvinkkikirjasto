package dtt.lukuvinkkikirjasto.demo.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.fasterxml.jackson.databind.ser.Serializers;
import dtt.lukuvinkkikirjasto.demo.BaseTest;
import dtt.lukuvinkkikirjasto.demo.domain.Book;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class BookDaoTest extends BaseTest {

    private static Book testBook;
    private static Book testBook2;

    @BeforeAll
    public static void setUp() {
        testBook = new Book("Jorma Kinnunen", "Keihäsmies", "9789518830163");
        testBook2 = new Book("Raimo Haakana", "Minä ja Mussolini", "978-1-4028-9462-6");
    }

    @Test
    public void aSingleBookCanBeInserted() throws SQLException {
        bookDao.create(testBook);

        List<Book> fromDb = bookDao.list();
        assertEquals("Jorma Kinnunen", fromDb.get(0).getAuthor());
        assertEquals("Keihäsmies", fromDb.get(0).getTitle());
        assertEquals("9789518830163", fromDb.get(0).getIsbn());
        assertFalse(fromDb.get(0).isRead());
    }

    @Test
    public void twoBooksCanBeInserted() throws SQLException {
        bookDao.create(testBook);
        bookDao.create(testBook2);
        List<Book> fromDb = bookDao.list();
        System.out.println(fromDb);
        assertEquals("Jorma Kinnunen", fromDb.get(0).getAuthor());
        assertEquals("Keihäsmies", fromDb.get(0).getTitle());
        assertEquals("9789518830163", fromDb.get(0).getIsbn());
        assertFalse(fromDb.get(0).isRead());
        assertEquals("Raimo Haakana", fromDb.get(1).getAuthor());
        assertEquals("Minä ja Mussolini", fromDb.get(1).getTitle());
        assertEquals("978-1-4028-9462-6", fromDb.get(1).getIsbn());
        assertFalse(fromDb.get(1).isRead());
    }

    @Test
    public void aSingleBookCanBeDeleted() throws SQLException {
        bookDao.create(testBook);
        List<Book> fromDb = bookDao.list();
        assertEquals(1, fromDb.size());

        bookDao.delete(testBook);
        fromDb = bookDao.list();
        assertEquals(0, fromDb.size());
    }

    @Test
    public void aBookCanBeDeletedWhenThereAreMultipleBooksInDatabase() throws SQLException {
        bookDao.create(testBook);
        bookDao.create(testBook2);
        bookDao.create(new Book("Darth Vader", "How to love your son", "978-1-40834-737-9"));
        List<Book> fromDb = bookDao.list();
        assertEquals(3, fromDb.size());

        bookDao.delete(testBook2);
        fromDb = bookDao.list();
        assertEquals(2, fromDb.size());
    }
    
    @Test
    public void returnOneBook() throws SQLException {
        bookDao.create(testBook);
        bookDao.create(testBook2);
        Book book = bookDao.findByIsbn(testBook.getIsbn());
        assertEquals("Jorma Kinnunen", book.getAuthor());
        assertEquals("9789518830163", book.getIsbn());
    }
}