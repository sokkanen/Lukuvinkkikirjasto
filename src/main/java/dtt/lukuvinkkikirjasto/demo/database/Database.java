package dtt.lukuvinkkikirjasto.demo.database;

import org.flywaydb.core.Flyway;
import javax.persistence.Entity;

import java.sql.Connection;

@Entity
public class Database {

    private final String url = "jdbc:sqlite:file:./build/lukusuositukset.sql";
    private final String userName = "sa";
    private final String password = "";
    private final Flyway flyway;

    public Database(){
        this.flyway = Flyway.configure().dataSource(url, userName, password).load();
    }

    public void doFlyWayMigration(){
        flyway.migrate();
    }

    // TODO: Actual connection to database
    /*public Connection connect(){

    }*/
}
