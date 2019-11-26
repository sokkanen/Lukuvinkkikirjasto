package dtt.lukuvinkkikirjasto.demo.database;

import org.flywaydb.core.Flyway;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {

    private final String url;
    private final String userName = "sa";
    private final String password = "";
    private final Flyway flyway;

    private Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public Database(@Value("./build/lukusuositukset.db") String url){
        String extension = url;
        if (System.getProperty("url") != null){
            extension = System.getProperty("url");
        }
        this.url = "jdbc:sqlite:file:" + extension;
        this.flyway = Flyway.configure().dataSource(this.url, userName, password).load();
        init();
        logger.info("Established database-connection to '{}'", this.url);
        flyway.migrate();
    }

    public void doFlyWayMigration(){
        flyway.migrate();
    }
    
    /**
     * Opens connection to database
     * @return connection
     */
    public Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url, userName, password);
        return connection;
    }
    
    public void init() {
        try {
            Class.forName("org.sqlite.JDBC");
            getConnection();
        } catch (Exception e) {
            System.out.println("Error in database connection");
        }
    }
}
