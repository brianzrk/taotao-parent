package com.taotao;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableDubbo
@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.taotao.search.repository")
public class TaotaoSearchApplication {

    public static void main(String[] args) {

        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(TaotaoSearchApplication.class, args);
        System.out.println("https://localhost:8084");

    }
}