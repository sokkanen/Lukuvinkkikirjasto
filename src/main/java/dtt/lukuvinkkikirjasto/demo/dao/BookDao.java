package dtt.lukuvinkkikirjasto.demo.dao;

import dtt.lukuvinkkikirjasto.demo.database.Database;
import dtt.lukuvinkkikirjasto.demo.domain.Book;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author milla
 */
@Repository
public class BookDao implements Dao<Book, Integer> {

    Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final Database database;

    public BookDao(Database database) {
        this.database = database;
    }

    /**
     * Creates new book to database
     *
     * @param book
     * @throws java.sql.SQLException
     */
    @Override
    public void create(Book book) throws SQLException {

        Connection connection = database.getConnection();

        PreparedStatement statement;

        statement = connection.prepareStatement("INSERT INTO Book (author,title, isbn) VALUES (?, ?, ?)");
        statement.setString(1, book.getAuthor());
        statement.setString(2, book.getTitle());
        statement.setString(3, book.getIsbn());

        logger.info("Inserting new book {} by {} to Database", book.getTitle(), book.getAuthor());
        statement.executeUpdate();
        logger.info("Book insertion completed successfully");
        statement.close();

        connection.close();
    }

    @Override
    public List<Book> list() throws SQLException {

        ArrayList<Book> bookList = new ArrayList();
        Connection connection = database.getConnection();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Book;");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Book book = getBook(resultSet);
            bookList.add(book);
        }
        logger.info("Executed a search for all books. Found {} book(s)", bookList.size());
        statement.close();
        resultSet.close();
        connection.close();
        
        return bookList;
    }

    public Book getBook(ResultSet rs) throws SQLException {
        Book book = new Book(
                rs.getString("author"),
                rs.getString("title"),
                rs.getString("isbn")
        );
        return book;
    }

}
