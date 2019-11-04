package com.fiuni.sd.issuetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:memcache.xml")
@EnableCaching
public class IssuetApplication {

	public static void main(String[] args) {
		SpringApplication.run(IssuetApplication.class, args);
	}

}
