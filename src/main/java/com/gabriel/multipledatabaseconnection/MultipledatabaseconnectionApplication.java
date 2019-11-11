package com.gabriel.multipledatabaseconnection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan("com.gabriel")
@EnableAspectJAutoProxy
@SpringBootApplication
public class MultipledatabaseconnectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultipledatabaseconnectionApplication.class, args);
	}
}
