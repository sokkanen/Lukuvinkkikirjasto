package dtt.lukuvinkkikirjasto.demo;

import org.junit.rules.ExternalResource;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;


class ServerRule extends ExternalResource{

    private final int port;
    ConfigurableApplicationContext app;

    public ServerRule(int port) {
        this.port = port;
    }

    @Override
    protected void before() throws Throwable {
        System.setProperty("url", "./build/lukuvinkkitest.db");
        this.app = SpringApplication.run(LukuvinkkikirjastoApplication.class);
    }

    @Override
    protected void after() {
        app.close();
    }
}