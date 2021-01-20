package com.hilu0318.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class InstagramImageDownLoaderApplication extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(InstagramImageDownLoaderApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(InstagramImageDownLoaderApplication.class, args);
	}
	
	

}
