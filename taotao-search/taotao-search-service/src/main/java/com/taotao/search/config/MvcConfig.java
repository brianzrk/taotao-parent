package com.taotao.search.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author brian
 * @date 2020/6/27
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

}