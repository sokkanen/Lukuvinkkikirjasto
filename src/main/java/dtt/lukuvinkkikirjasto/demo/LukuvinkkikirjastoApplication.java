package dtt.lukuvinkkikirjasto.demo;

import dtt.lukuvinkkikirjasto.demo.database.Database;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan( basePackages = {"lukuvinkkikirjasto"} )
public class LukuvinkkikirjastoApplication {

	public static void main(String[] args) {
		Database database = new Database();
		database.doFlyWayMigration();
		SpringApplication.run(LukuvinkkikirjastoApplication.class, args);
	}
}
