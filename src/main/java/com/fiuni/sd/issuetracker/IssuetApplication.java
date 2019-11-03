package com.fiuni.sd.issuetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class IssuetApplication {

	public static void main(String[] args) {
		SpringApplication.run(IssuetApplication.class, args);
	}

}
