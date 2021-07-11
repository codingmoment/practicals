package com.demo.jpa;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.demo.jpa.entity.Person;
import com.demo.jpa.repository.PersonJpaRepository;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(JpaDemoApplication.class);

	@Autowired
	private PersonJpaRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("findById for 1001 : {}", repository.findById(1001));
		LOGGER.info("insert new person - {}", repository.insert(new Person("Spruha", "Australia", new Date())));
		LOGGER.info("update person - {}", repository.update(new Person(1001, "Nilesh", "Germany", new Date())));
		LOGGER.info("deleteById for 1002");
		repository.deleteById(1002);
		LOGGER.info("findAll: {}", repository.findAll());
	}
}
