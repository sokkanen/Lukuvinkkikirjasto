package dtt.lukuvinkkikirjasto.demo;

import org.junit.rules.ExternalResource;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

class ServerRule extends ExternalResource{

    private final int port;
    ConfigurableApplicationContext app;

    public ServerRule(int port) {
        this.port = port;
    }

    @Override
    protected void before() throws Throwable {
        app = new SpringApplicationBuilder(LukuvinkkikirjastoApplication.class)
                .profiles("test")
                .properties("selenium:true")
                .headless(true)
                .application()
                .run();
    }

    @Override
    protected void after() {
        app.close();
    }
}