package com.example.virtiverse.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/img/**") // Define the URL pattern to match
                .addResourceLocations("file:/C:/xampp/htdocs/img/"); // Specify the directory where static content is located


    }

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200") // Update with your Angular app's URL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("Origin", "Content-Type", "Accept");

    }
   @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
     configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }




}
