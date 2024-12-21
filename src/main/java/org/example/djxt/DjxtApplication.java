package org.example.djxt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("org.example.djxt.mapper") // 指定要扫描的 Mapper 接口所在的基础包路径
public class DjxtApplication {

    public static void main(String[] args) {
        SpringApplication.run(DjxtApplication.class, args);
    }

}
