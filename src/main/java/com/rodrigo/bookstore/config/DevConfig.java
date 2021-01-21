package com.rodrigo.bookstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.rodrigo.bookstore.services.DbService;

@Configuration
@Profile("dev")
public class DevConfig {
	@Autowired
	private DbService dbService;
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean databaseInstance() {
		if(strategy.equals("create")) {
			dbService.databaseInstance();
			return true;
		}
		
		return false;
	}
}
