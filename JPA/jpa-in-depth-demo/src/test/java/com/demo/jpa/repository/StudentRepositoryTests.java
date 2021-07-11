package com.demo.jpa.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.jpa.entity.Address;
import com.demo.jpa.entity.Course;
import com.demo.jpa.entity.Marks;
import com.demo.jpa.entity.Passport;
import com.demo.jpa.entity.Student;

@SpringBootTest
class StudentRepositoryTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentRepositoryTests.class);

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private EntityManager entityManager;

	@Test
	void playWithOneToOneRelationship() {
		studentRepository.playWithOneToOneRelationship();
		assertTrue(true);
	}

	@Test
	void eagerFetchShouldReturnPassport() {
		Student student = studentRepository.findById(1001);
		LOGGER.info("Student - {}", student);
		LOGGER.info("Passport - {}", student.getPassport());
		assertEquals("W123456", student.getPassport().getNumber());
	}

	@Test
	void eagerFetchShouldThrowLazyInitializationException() {
		Student student = studentRepository.findById(1001);
		LOGGER.info("Student - {}", student);
		Address address = student.getAddress();
		assertThrows(LazyInitializationException.class, () -> {
			address.getCity();
		});
	}

	@Test
	@Transactional
	void lazyFetchShouldReturnCity() {
		Student student = studentRepository.findById(1001);
		LOGGER.info("Student - {}", student);
		Address address = student.getAddress();
		assertEquals("Pune", address.getCity());
	}

	@Test
	@Transactional
	void fetchingPassportShouldReturnStudent() {
		Passport passport = entityManager.find(Passport.class, 2001);
		Student student = passport.getStudent();
		assertEquals("Nilesh", student.getName());
	}

	@Test
	@Transactional
	void fetchingStudentsWithCourses() {
		Student student = studentRepository.findById(1001);
		LOGGER.info("Student -> {}", student);
		List<Course> courses = student.getCourses();
		LOGGER.info("Courses -> {}", courses);
		assertEquals(3, courses.size());
	}

	@Test
	@Transactional
	void fetchingCoursesWithStudents() {
		Course course = entityManager.find(Course.class, 200);
		LOGGER.info("Course -> {}", course);
		List<Student> students = course.getStudents();
		LOGGER.info("Students -> {}", students);
		assertEquals(2, students.size());
	}

	@Test
	@Transactional
	void createStudentWithCourse() {
		int studentId = studentRepository.createStudentWithCourse(new Student("Spruha"), new Course("Microservices"));
		Student student = studentRepository.findById(studentId);
		assertEquals("Spruha", student.getName());
		assertEquals(1, student.getCourses().size());
	}

	@Test
	@Transactional
	void addCourseToStudent() {
		studentRepository.addCourseToStudent(1001, new Course("Microservices"));
		Student student = studentRepository.findById(1001);
		assertEquals(4, student.getCourses().size());
	}

	@Test
	@Transactional
	void callsShouldBeCachedInFirstLevelCache() {
		Student student = studentRepository.findById(1001);
		LOGGER.info("Student 1st call -> {}", student);
		student = studentRepository.findById(1001);
		LOGGER.info("Student 2nd call -> {}", student);
		assertEquals("Nilesh", student.getName());
	}

	@Test
	void callsShouldNotBeCachedInFirstLevelCache() {
		Student student = studentRepository.findById(1001);
		LOGGER.info("Student 1st call -> {}", student);
		student = studentRepository.findById(1001);
		LOGGER.info("Student 2nd call -> {}", student);
		assertEquals("Nilesh", student.getName());
	}
	
	@Test
	@Transactional
	void setEmbeddedMarks() {
		Student student = studentRepository.findById(1001);
		student.setMarks(new Marks(90, 30, 50));
		studentRepository.save(student);
		student = studentRepository.findById(1001);
		assertEquals("Nilesh", student.getName());
	}
}
