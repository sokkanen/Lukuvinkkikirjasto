package dtt.lukuvinkkikirjasto.demo;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest extends BaseTest {

    @Test
    public void testsAreDoneInTestDatabase() throws SQLException {
        assertEquals("jdbc:sqlite:file:./build/lukuvinkkitest.db", connection.getMetaData().getURL());
    }
}
