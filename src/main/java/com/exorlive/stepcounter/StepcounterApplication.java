package com.exorlive.stepcounter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationAutoConfiguration;

@SpringBootApplication
public class StepcounterApplication {

	public static void main(String[] args) {
		SpringApplication.run(StepcounterApplication.class, args);
	}

}
