package com.campccino;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;

import org.mybatis.spring.annotation.MapperScan;
@SpringBootApplication
@ComponentScan(basePackages = {"com.campccino"})
@MapperScan("com.campccino.mapper")
public class CampCcinoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampCcinoApplication.class, args);
	}

}
