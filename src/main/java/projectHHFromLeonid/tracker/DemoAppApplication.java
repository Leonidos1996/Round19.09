package projectHHFromLeonid.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import projectHHFromLeonid.tracker.integration.hh.HHIntegrationService;

@SpringBootApplication
@EnableJpaRepositories
public class  DemoAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoAppApplication.class, args);


    }

}