package dtt.lukuvinkkikirjasto.demo;

import dtt.lukuvinkkikirjasto.demo.dao.BookDao;
import dtt.lukuvinkkikirjasto.demo.database.Database;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * A base test-class for all the JUnit-tests in the application.
 *
 * Extend your test-class from BaseTest to enable db-operations in your tests..
 */
@SpringBootTest(properties = {"selenium:true"})
@ActiveProfiles("test")
public class BaseTest {

    // Public-visibility to enable usage in extended test-classes
    public static BookDao bookDao;
    static Connection connection;
    static Database database;

    /**
     * Creates test-database and initializes database-connection.
     * @throws SQLException
     */
    @BeforeAll
    public static void initialInitialization() throws SQLException, IOException{
        initialize();
        removeTestData();
    }

    /**
     * Imports clear-all-tables.sql from /test/resources/
     * Runs every line in the file as a SQL-script to remove all data from test-db after each test.
     */
    @BeforeEach()
    public void initialization() throws SQLException, IOException{
        removeTestData();
        System.setProperty("testing", "true");
    }

    /**
     * Imports clear-all-tables.sql from /test/resources/
     * Runs every line in the file as a SQL-script to remove all data from test-db after each test.
     */
    @AfterEach
    public void cleanUp() throws IOException, SQLException {
        removeTestData();
    }

    @AfterAll
    public static void tearDown() throws SQLException {
        System.setProperty("test", "false");
        connection.close();
    }

    public static void initialize() throws SQLException{
        database = new Database();
        connection = database.getConnection();
        bookDao = new BookDao(database);
    }

    public static void removeTestData() throws IOException, SQLException {
        Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file = new File(classLoader.getResource("clear-all-tables.sql").getFile());
        BufferedReader reader  = new BufferedReader(new FileReader(file));
        while (reader.read() > 0) {
            try {
                String sqlStatement = reader.readLine();
                PreparedStatement stmt = connection.prepareStatement(sqlStatement);
                stmt.executeUpdate();
                logger.info("Executed SQL-script \"{}\"", sqlStatement);
                stmt.close();
            } catch (IOException e){
                logger.warn("IO Exception with clear-all-tables: \"{}\"", e.getMessage());
                throw e;
            } catch (SQLException ee){
                logger.warn("SQL Exception with clear-all-tables: \"{}\"", ee.getMessage());
                throw ee;
            }
        }
        reader.close();
    }
}
