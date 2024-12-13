package edu.t1.task8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("edu.t1")
@EnableJpaRepositories("edu.t1")
@EntityScan("edu.t1")
@EnableScheduling
public class MainTask8 {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(MainTask8.class, args);
    }
}
