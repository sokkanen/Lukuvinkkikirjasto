package dtt.lukuvinkkikirjasto.demo.database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import java.lang.invoke.MethodHandles;
public class HerokuListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
    private Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment env = event.getEnvironment();
        boolean inCloud = env.getProperty("DYNO") != null ? setHerokuProperties(env) : setLocalProperties(env);
        if (inCloud){
            logger.info("Properties for Cloud environment set.");
        } else {
            logger.info("Properties for Local environment set");
        }
    }

    private boolean setHerokuProperties(ConfigurableEnvironment env){
        logger.info("Found HEROKU environment. Setting System properties.");
        System.setProperty("JDBC_DATABASE_URL", env.getProperty("JDBC_DATABASE_URL"));
        System.setProperty("JDBC_DATABASE_USERNAME", env.getProperty("JDBC_DATABASE_USERNAME"));
        System.setProperty("JDBC_DATABASE_PASSWORD", env.getProperty("JDBC_DATABASE_PASSWORD"));
        return true;
    }

    private boolean setLocalProperties(ConfigurableEnvironment env){
        System.setProperty("JDBC_DATABASE_URL", "jdbc:sqlite:file:./build/lukusuositukset.db");
        System.setProperty("JDBC_DATABASE_USERNAME", "sa");
        System.setProperty("JDBC_DATABASE_PASSWORD", "");
        System.setProperty("JDBC_DATABASE_TEST_URL", "jdbc:sqlite:file:./build/lukuvinkkitest.db");
        return false;
    }
}