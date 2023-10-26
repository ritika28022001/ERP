package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ErpApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ErpApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ErpApplication.class);
	}

//	@Bean
//	public WebMvcConfigurer configurer() {
//		return new WebMvcConfigurer() {
//		 @Override
//		 public void addCorsMappings( CorsRegistry reg) {
//			 reg.addMapping("/**").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
//		 }
//		};
//}
}