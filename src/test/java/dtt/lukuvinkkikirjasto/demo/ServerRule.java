package dtt.lukuvinkkikirjasto.demo;

import dtt.lukuvinkkikirjasto.demo.dao.BookDao;
import org.junit.rules.ExternalResource;
import spark.Spark;

public class ServerRule extends ExternalResource {
    
    private final int port;

    public ServerRule(int port) {
        this.port = port;
    }

    @Override
    protected void before() throws Throwable {
        Spark.port(port);
    }

    @Override
    protected void after() {
        Spark.stop();
    }


    
}
