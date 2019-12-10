package dtt.lukuvinkkikirjasto.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class LukuvinkkikirjastoApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(LukuvinkkikirjastoApplication.class)
                .profiles("dev")
                .properties("selenium:false")
                .application()
                .run(args);
    }
}
