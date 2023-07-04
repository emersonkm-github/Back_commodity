package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // 根据需要调整映射模式
                .allowedOrigins("http://localhost:3000") // 指定允许访问你的 API 的源
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 指定允许的 HTTP 方法
                .allowedHeaders("*") // 指定请求中允许的头部
                .allowCredentials(true); // 允许客户端发送 cookies
    }
}
