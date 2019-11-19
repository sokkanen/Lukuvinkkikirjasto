package dtt.lukuvinkkikirjasto.demo.database;

import org.flywaydb.core.Flyway;
import javax.persistence.Entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Entity
public class Database {

    private final String url;
    private final String userName = "sa";
    private final String password = "";
    private final Flyway flyway;
    

    public Database(String url){
        this.url = "jdbc:sqlite:file:"+url;
        this.flyway = Flyway.configure().dataSource(this.url, userName, password).load();
        init();
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
