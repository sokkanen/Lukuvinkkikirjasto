package dtt.lukuvinkkikirjasto.demo.database;

import org.flywaydb.core.Flyway;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class Database {

    private final String url;
    private String userName;
    private String password = "";
    private final Flyway flyway;

    @Autowired
    private Environment env;

    private Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public Database(@Value("./build/lukusuositukset.db") String url) {
        boolean inCloud = !getCloudUrl().equals("");

        this.url = inCloud ? getCloudUrl() : localConfig(url);
        this.userName = inCloud ? env.getProperty("JDBC_DATABASE_USERNAME") : "sa";
        this.password = inCloud ? env.getProperty("JDBC_DATABASE_PASSWORD") : "";

        this.flyway = Flyway.configure().dataSource(this.url, userName, password).load();
        initializeDbConnection(inCloud);
        logger.info("Established database-connection to '{}'", this.url);
        flyway.migrate();
    }

    public void doFlyWayMigration(){
        flyway.migrate();
    }

    private String localConfig(String url){
        String extension = url;
        if (System.getProperty("url") != null){
            extension = System.getProperty("url");
        }
        return"jdbc:sqlite:file:" + extension;
    }

    private String getCloudUrl(){
        try {
            return env.getProperty("JDBC_DATABASE_URL");
        } catch (NullPointerException e){
            return "";
        }
    }
    
    /**
     * Opens connection to database
     * @return connection
     */
    public Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url, userName, password);
        return connection;
    }

    /**
     * Establishes a database-connection.
     * @param inCloud If run in Heroku --> true, else false
     */
    private void initializeDbConnection(boolean inCloud) {
        try {
            if (inCloud){
                Class.forName("org.postgresql.Driver");
                logger.info("Connecting to PostgreSQL");
                getConnection();
            } else {
                Class.forName("org.sqlite.JDBC");
                logger.info("Connecting to SqLite");
                getConnection();
            }
        } catch (Exception e) {
            logger.warn("Error in database connection: {}", e.getMessage());
        }
    }
}
