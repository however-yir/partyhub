package org.example.djxt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@MapperScan("org.example.djxt.mapper")
public class DjxtApplication {

    public static void main(String[] args) {
        SpringApplication.run(DjxtApplication.class, args);
    }

}
