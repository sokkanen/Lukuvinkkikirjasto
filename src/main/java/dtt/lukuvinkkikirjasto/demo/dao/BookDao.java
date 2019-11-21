package dtt.lukuvinkkikirjasto.demo.dao;

import dtt.lukuvinkkikirjasto.demo.database.Database;
import dtt.lukuvinkkikirjasto.demo.domain.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milla
 */
public class BookDao implements Dao<Book, Integer> {

    private final Database database;

    public BookDao(Database database) {
        this.database = database;

    }

    /**
     * Creates new book to database
     *
     * @param book
     */
    @Override
    public void create(Book book) {

        try {
            Connection connection = database.getConnection();

            PreparedStatement statement;

            statement = connection.prepareStatement("INSERT INTO Book (author,title, isbn, "
                    + "read) VALUES (?, ?, ?, ?)");
            statement.setString(1, book.getAuthor());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getIsbn());
            statement.setBoolean(4, false);

            statement.executeUpdate();
            statement.close();

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Book> list() {

        ArrayList<Book> bookList = new ArrayList();
        try {
            Connection connection = database.getConnection();

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Book;");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Book book = getBook(resultSet);
                bookList.add(book);

            }
            statement.close();
            resultSet.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bookList;
    }

    public Book getBook(ResultSet rs) throws SQLException {
        Book book = new Book(
                rs.getString("author"),
                rs.getString("title"),
                rs.getString("isbn"),
                rs.getBoolean("read")
        );
        return book;
    }

}
