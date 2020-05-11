package fun7App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "fun7App.repository")
@SpringBootApplication
public class SpringBootFun7Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootFun7Application.class, args);
    }
}

