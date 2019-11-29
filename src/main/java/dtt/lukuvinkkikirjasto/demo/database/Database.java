package dtt.lukuvinkkikirjasto.demo.database;

import org.flywaydb.core.Flyway;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Database {

    private String url;
    private final String userName;
    private final String password;
    private final Flyway flyway;

    private Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public Database() {
        this.url = System.getProperty("JDBC_DATABASE_URL");
        this.userName = System.getProperty("JDBC_DATABASE_USERNAME");
        this.password = System.getProperty("JDBC_DATABASE_PASSWORD");
        this.flyway = Flyway.configure().dataSource(this.url, userName, password).load();
        boolean usingLocally = this.url.contains("build");
        initializeDbConnection(usingLocally);
        logger.info("Established database-connection to '{}'", this.url);
        doFlyWayMigration();
    }

    public void doFlyWayMigration(){
        logger.info("FLYWAY URL: {}", this.url);
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

    private void initializeDbConnection(boolean usingLocally) {
        try {
            if (usingLocally){
                Class.forName("org.sqlite.JDBC");
                logger.info("Connecting to SqLite");
                getConnection();
            } else {
                Class.forName("org.postgresql.Driver");
                logger.info("Connecting to PostgreSQL");
                getConnection();
            }
        } catch (Exception e) {
            System.out.println("Error in database connection");
            logger.warn("Error in database connection: {}", e.getMessage());
        }
    }

}
