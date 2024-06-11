package com.home.dataprocessing;

import com.home.dataprocessing.util.JsonToMapConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataProcessingApplication {

	private static final Logger logger = LoggerFactory.getLogger(DataProcessingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DataProcessingApplication.class, args);

		logger.debug("Application Started");
	}

}
