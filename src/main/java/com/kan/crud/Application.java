package com.kan.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
@SpringBootApplication
public class Application {

	public static void main(String args[]) {
		SpringApplication.run(Application.class);
	}

}