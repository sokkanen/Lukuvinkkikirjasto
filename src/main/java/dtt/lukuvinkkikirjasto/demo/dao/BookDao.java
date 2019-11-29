package dtt.lukuvinkkikirjasto.demo.dao;

import dtt.lukuvinkkikirjasto.demo.database.Database;
import dtt.lukuvinkkikirjasto.demo.domain.Book;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class BookDao implements Dao<Book> {

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

        List<Book> books = list();

        int newId = books.size() == 0 ? 1 : books.get(books.size()-1).getId() +1;
        
        PreparedStatement statement;
        String sql = "INSERT INTO book (id, author, title, isbn, read_already) VALUES (?, ?, ?, ?, ?)";
        statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, newId);
        statement.setString(2, book.getAuthor());
        statement.setString(3, book.getTitle());
        statement.setString(4, book.getIsbn());
        statement.setBoolean(5, false);
        
        logger.info("Inserting new book {} by {} to Database", book.getTitle(), book.getAuthor());
        statement.executeUpdate();
        logger.info("Book insertion completed successfully");
        
        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next()) {
            int id = rs.getInt(1);
            book.setId(id);
        }
        
        statement.close();
        
        connection.close();
    }

    @Override
    public void delete(Book book) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement;
        String sql = "DELETE FROM book WHERE book.id = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, book.getId());

        logger.info("Deleting book {} by {} from Database", book.getTitle(), book.getAuthor());
        statement.executeUpdate();
        logger.info("Book deletion completed successfully");

        statement.close();
        connection.close();
    }


    @Override
    public List<Book> list() throws SQLException {

        ArrayList<Book> bookList = new ArrayList();
        Connection connection = database.getConnection();

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM book;");
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
    
    public Book findByIsbn(String isbn) throws SQLException {
        if (isbn.isEmpty()) {
            return null;
        }
        Connection conn = database.getConnection();
        
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM book WHERE isbn = ?");
        statement.setString(1, isbn);
        ResultSet res = statement.executeQuery();
        if(!res.next()) {
            return null;
        }


        Book onebook = getBook(res);
        logger.info("Execute a search for one book by name. Found {} book", onebook.getIsbn());
        
        statement.close();
        res.close();
        
        conn.close();
        return onebook;
    }

    public Book findById(Integer id) throws SQLException {
        if (id == null) {
            return null;
        }
        Connection conn = database.getConnection();
        
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM book WHERE id = ?");
        statement.setInt(1, id);
        ResultSet res = statement.executeQuery();
        if(!res.next()) {
            return null;
        }


        Book onebook = getBook(res);
        logger.info("Execute a search for one book by name. Found {} book", onebook.getIsbn());
        
        statement.close();
        res.close();
        
        conn.close();
        return onebook;
    }
    

    public Book getBook(ResultSet rs) throws SQLException {
        Book book = new Book(
                rs.getString("author"),
                rs.getString("title"),
                rs.getString("isbn")
        );
        book.setId(rs.getInt("id"));
        return book;
    }

}
