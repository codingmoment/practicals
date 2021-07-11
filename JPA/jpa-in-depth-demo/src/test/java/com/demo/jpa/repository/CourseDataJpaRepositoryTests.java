package com.demo.jpa.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.demo.jpa.entity.Course;

@SpringBootTest
class CourseDataJpaRepositoryTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(CourseDataJpaRepository.class);

	@Autowired
	private CourseDataJpaRepository courseDataJpaRepository;

	@Test
	void findById100ShouldGiveCourse() {
		Optional<Course> courseOptional = courseDataJpaRepository.findById(100);
		assertTrue(courseOptional.isPresent());
	}

	@Test
	void findById800ShouldNotGiveCourse() {
		Optional<Course> courseOptional = courseDataJpaRepository.findById(800);
		assertFalse(courseOptional.isPresent());
	}

	@Test
	void createAndUpdateCourse() {
		Course course = new Course("iBatis");
		courseDataJpaRepository.save(course);

		course.setName("MyBatis");
		courseDataJpaRepository.save(course);

		LOGGER.info("Courses -> {}", courseDataJpaRepository.findAll());

		Sort sort = Sort.by(Sort.Direction.ASC, "name");
		LOGGER.info("Sorted Courses -> {}", courseDataJpaRepository.findAll(sort));
		assertFalse(false);
	}

	@Test
	void getPagedCourses() {
		PageRequest pageRequest = PageRequest.of(0, 5);
		Page<Course> page = courseDataJpaRepository.findAll(pageRequest);
		LOGGER.info("First Page -> {}", page.getContent());

		while (page.hasNext()) {
			Pageable nextPageable = page.nextPageable();
			page = courseDataJpaRepository.findAll(nextPageable);
			LOGGER.info("Page {} -> {}", page.getNumber(), page.getContent());
		}
		assertFalse(false);
	}

	@Test
	void findByName() {
		LOGGER.info("findByName -> {}", courseDataJpaRepository.findByName("AngularJS"));
		assertFalse(false);
	}
}
