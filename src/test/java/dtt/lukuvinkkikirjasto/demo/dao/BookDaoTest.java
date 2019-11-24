package dtt.lukuvinkkikirjasto.demo.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
        testBook = new Book("Jorma Kinnunen", "Keihäsmies", "123-123");
        testBook2 = new Book("Raimo Haakana", "Minä ja Mussolini", "321-321");
    }

    @Test
    public void aSingleBookCanBeInserted() throws SQLException {
        bookDao.create(testBook);
        List<Book> fromDb = bookDao.list();
        assertEquals("Jorma Kinnunen", fromDb.get(0).getAuthor());
        assertEquals("Keihäsmies", fromDb.get(0).getTitle());
        assertEquals("123-123", fromDb.get(0).getIsbn());
        assertFalse(fromDb.get(0).isRead());
    }

    @Test
    public void twoBooksCanBeInserted() throws SQLException {
        bookDao.create(testBook);
        bookDao.create(testBook2);
        List<Book> fromDb = bookDao.list();
        assertEquals("Jorma Kinnunen", fromDb.get(0).getAuthor());
        assertEquals("Keihäsmies", fromDb.get(0).getTitle());
        assertEquals("123-123", fromDb.get(0).getIsbn());
        assertFalse(fromDb.get(0).isRead());
        assertEquals("Raimo Haakana", fromDb.get(1).getAuthor());
        assertEquals("Minä ja Mussolini", fromDb.get(1).getTitle());
        assertEquals("321-321", fromDb.get(1).getIsbn());
        assertFalse(fromDb.get(1).isRead());
    }
}
