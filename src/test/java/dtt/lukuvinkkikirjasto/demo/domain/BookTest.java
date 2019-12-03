package dtt.lukuvinkkikirjasto.demo.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dtt.lukuvinkkikirjasto.demo.BaseTest;
import dtt.lukuvinkkikirjasto.demo.domain.Book;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class BookTest {

    private Book testBook;
    private Book testBookWith10DigitIsbn;
    private Book testBookWith13DigitIsbn;

    public BookTest() {
        testBook = new Book("Jorma Kinnunen", "Keihäsmies", "123-123");
        testBookWith10DigitIsbn = new Book("Kirjailija", "Kirjan nimi", "0-7475-3269-9");
        testBookWith13DigitIsbn = new Book("Kirjailija", "Kirjan nimi", "978-1-4133-0454-1");
    }

    @Test
    public void testSetTitle() {
        testBook.setTitle("Uusi otsikko");
        assertEquals("Uusi otsikko", testBook.getTitle());
    }

    @Test
    public void testSetAuthor() {
        testBook.setAuthor("Uusi kirjailija");
        assertEquals("Uusi kirjailija", testBook.getAuthor());
    }

    @Test
    public void testSetIsbn() {
        testBook.setIsbn("0101010101");
        assertEquals("0101010101", testBook.getIsbn());
    }

    @Test
    public void testSetRead() {
        testBook.setRead(true);
        assertTrue(testBook.isRead());
    }

    @Test
    public void test10NumberIsbn() {
        assertEquals("error", testBook.getIsbn());
    }

    @Test
    public void testValid10NumberIsbn() {
        assertEquals("0-7475-3269-9", testBookWith10DigitIsbn.getIsbn());
    }

    @Test
    public void test13NumberIsbn() {
        assertEquals("978-1-4133-0454-1", testBookWith13DigitIsbn.getIsbn());
    }
}