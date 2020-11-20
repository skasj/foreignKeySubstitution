package org.example.foreignKeySubstitution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class FKSApplication {

    public static void main(String[] args) {
        SpringApplication.run(FKSApplication.class, args);
    }
}
