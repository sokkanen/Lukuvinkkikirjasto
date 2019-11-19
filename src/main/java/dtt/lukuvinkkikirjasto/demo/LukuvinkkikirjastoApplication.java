package dtt.lukuvinkkikirjasto.demo;

import dtt.lukuvinkkikirjasto.demo.dao.BookDao;
import dtt.lukuvinkkikirjasto.demo.database.Database;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan( basePackages = {"lukuvinkkikirjasto"} )
public class LukuvinkkikirjastoApplication {

	public static void main(String[] args) {
                String dbName = "./build/lukusuositukset.sql";
		Database database = new Database(dbName);
		database.doFlyWayMigration();
                BookDao bookDao = new BookDao(database);
		SpringApplication.run(LukuvinkkikirjastoApplication.class, args);
	}
}
