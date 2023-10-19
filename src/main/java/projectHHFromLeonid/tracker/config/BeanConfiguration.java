package projectHHFromLeonid.tracker.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.time.Duration;

@Configuration
public class BeanConfiguration {

    @Bean
    @Qualifier("hh_resttemplate")
    public RestTemplate getRestTemplateHH() {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(10))
                .build();
        return restTemplate;
    }

    @Bean
    @Qualifier("super_job_resttemplate")
    public RestTemplate getRestTemplateSuperJob() {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(60))
                .build();
        return restTemplate;
    }
}
