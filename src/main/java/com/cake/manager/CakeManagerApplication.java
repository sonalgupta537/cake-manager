package com.cake.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * The Class CakeManagerApplication.
 */
@SpringBootApplication
@CrossOrigin(origins = "*")
public class CakeManagerApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(CakeManagerApplication.class, args);
		System.out.println("Welcome to Cake Manager");
	}

}
