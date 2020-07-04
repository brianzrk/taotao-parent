package com.taotao;

//import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class TaotaoWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaotaoWebApplication.class, args);
        System.out.println("http://localhost:8081/");
    }
}