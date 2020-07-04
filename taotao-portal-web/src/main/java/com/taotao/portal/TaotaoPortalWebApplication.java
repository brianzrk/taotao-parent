package com.taotao.portal;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class TaotaoPortalWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaotaoPortalWebApplication.class, args);
        System.out.println("http://localhost:8083/");
    }
}