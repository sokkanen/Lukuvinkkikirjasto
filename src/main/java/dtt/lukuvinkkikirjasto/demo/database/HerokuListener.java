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
        logger.info("HELLO! APP STARTING :D SUCH WOW LUKUKIRJASTO!");
        logger.info("This would be a great time to add system properties. :)");

    }
}
