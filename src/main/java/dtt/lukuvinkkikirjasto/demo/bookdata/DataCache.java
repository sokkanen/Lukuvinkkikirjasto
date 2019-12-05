package dtt.lukuvinkkikirjasto.demo.bookdata;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class DataCache {

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("bookdata");
    }

}
