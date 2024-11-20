package com.ssafy.campcino;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import org.mybatis.spring.annotation.MapperScan;
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@ComponentScan(basePackages = {"com.ssafy.campcino", "com.ssafy.enjoyCamp"})
@MapperScan("com.ssafy.campcino.repository")
public class CampCinoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampCinoApplication.class, args);
	}

}
