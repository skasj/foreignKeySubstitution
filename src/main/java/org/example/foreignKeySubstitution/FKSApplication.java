package org.example.foreignKeySubstitution;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("org.example.foreignKeySubstitution.modal.entity")
@SpringBootApplication
public class FKSApplication {

    public static void main(String[] args) {
        SpringApplication.run(FKSApplication.class, args);
    }
}
