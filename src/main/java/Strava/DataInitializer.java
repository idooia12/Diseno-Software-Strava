package Strava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DataInitializer {
  
	private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);
	@Bean
	 CommandLineRunner initData() {
        return args -> {	
        // Create some users
        	
        };
}
}
