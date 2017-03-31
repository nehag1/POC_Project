package com.shopAddress;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This Class is main Spring Boot Application
 * 
 * @author grover_n
 *
 */

@SpringBootApplication
public class ShopAddressApplication {

	final static Logger logger = Logger.getLogger(ShopAddressApplication.class);

	public static void main(String[] args) {
		System.setProperty("-Dlog4j.configuration", "D:\\Logging\\log4j-application.log");
		logger.debug("Starting Spring Application");
		SpringApplication.run(ShopAddressApplication.class, args);
		logger.debug("Ending Spring Application");
	}
}
