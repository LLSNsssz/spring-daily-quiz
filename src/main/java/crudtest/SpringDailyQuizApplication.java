package crudtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
    scanBasePackages = "crudtest.springweeklyquiz"
)
public class SpringDailyQuizApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDailyQuizApplication.class, args);
    }

}
