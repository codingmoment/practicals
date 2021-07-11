package com.demo.jpa;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import com.demo.jpa.entity.Person;
import com.demo.jpa.jdbc.PersonJdbcDao;

// Put @SpringBootApplication if you want to run Spring JDBC
public class SpringJdbcDemoApplication implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringJdbcDemoApplication.class);

	@Autowired
	private PersonJdbcDao personJdbcDao;

	public static void main(String[] args) {
		SpringApplication.run(SpringJdbcDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("findAll: {}", personJdbcDao.findAll());
		LOGGER.info("findById for 1001 : {}", personJdbcDao.findById(1001));
		LOGGER.info("deleteById for 1001 deleted rows - {}", personJdbcDao.deleteById(1001));
		LOGGER.info("insert new person created rows - {}",
				personJdbcDao.insert(new Person(1005, "Spruha", "Australia", new Date())));
		LOGGER.info("update person updated rows - {}",
				personJdbcDao.update(new Person(1005, "Spruha", "Germany", new Date())));
	}
}
