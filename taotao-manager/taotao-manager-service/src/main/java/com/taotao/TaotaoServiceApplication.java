package com.taotao;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@MapperScan("com.taotao.mapper")
public class TaotaoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaotaoServiceApplication.class, args);
        System.out.println("http://localhost:8080/");
    }
}