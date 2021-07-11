package com.demo.jpa.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EntityManagerTests {

	@Autowired
	private CourseRepository courseRepository;

	@Test
	void playWithEntityManager() {
		courseRepository.playWithPersistAndMerge();
		courseRepository.playWithDetach();
		courseRepository.playWithClear();
		courseRepository.playWithRefresh();
		assertTrue(true);
	}
}
