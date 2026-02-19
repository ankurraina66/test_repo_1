package com.levent.consultantapi.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.levent.consultantapi.service.InfoService;
import com.levent.consultantapi.service.info.impl.InfoServiceImpl1;
import com.levent.consultantapi.service.info.impl.InfoServiceImpl2;
import com.levent.consultantapi.service.info.impl.InfoServiceImpl3;
import com.levent.consultantapi.service.info.impl.InfoServiceImpl4;

@Configuration
//@PropertySource(value = "classpath:implementation.properties")		// Use his when file is in classpath, ex: main/src/resources
@PropertySource("file:./config/implementation.properties")
public class AppConfig {
	
	@Value("${greeter.implementation}")
	String impl;
	
	String username = "hello";
	String password = "pwd";
	
	@Bean
	public InfoService getImplementationFromPropertiesFile() {
		System.out.println("Implementation: " + impl);
		
		switch(impl) {
			case "impl1": {
				return new InfoServiceImpl1();
			}
			
			case "impl2": {
				return new InfoServiceImpl2();
			}
			
			case "impl3": {
				return new InfoServiceImpl3();
			}
			
			case "impl4": {
				return new InfoServiceImpl4();
			}
			
			default: {
				throw new IllegalStateException("No such implementation: " + impl);
			}
		}
	}
	
	public static void run() throws Exception {
		String username = "test"; // user input
		String password = "test"; // user input

		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/testdb", "root", "password");

		Statement stmt = conn.createStatement();

		// ❌ SQL Injection vulnerability
		String sql = "SELECT * FROM users WHERE username = '"
				+ username + "' AND password = '" + password + "'";

		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {
			System.out.println("Login successful");
		} else {
			System.out.println("Invalid credentials");
		}

		conn.close();
	}

}
