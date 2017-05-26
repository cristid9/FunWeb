package serviceComponents;

import db.DBConnector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
@ComponentScan
public class Application {

    @Bean
    public DBConnector dbConnector() {
        return new DBConnector();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}