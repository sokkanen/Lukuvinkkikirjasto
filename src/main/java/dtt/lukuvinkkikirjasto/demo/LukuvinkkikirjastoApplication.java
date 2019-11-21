package dtt.lukuvinkkikirjasto.demo;

import dtt.lukuvinkkikirjasto.demo.controller.BookController;
import dtt.lukuvinkkikirjasto.demo.dao.BookDao;
import dtt.lukuvinkkikirjasto.demo.database.Database;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan( basePackages = {"lukuvinkkikirjasto"} )
@ComponentScan(basePackages = {"lukuvinkkikirjasto"})
public class LukuvinkkikirjastoApplication {

	public static void main(String[] args) {
                String dbName = "./build/lukusuositukset.db";
		Database database = new Database(dbName);
		database.doFlyWayMigration();
                BookDao bookDao = new BookDao(database);
                BookController controller = new BookController(bookDao);
		SpringApplication.run(LukuvinkkikirjastoApplication.class, args);
	}
}
