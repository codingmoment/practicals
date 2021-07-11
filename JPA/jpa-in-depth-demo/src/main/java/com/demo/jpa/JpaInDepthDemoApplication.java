package com.demo.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.demo.jpa.entity.TestCourse;
import com.demo.jpa.repository.CourseDataJpaRepository;
import com.demo.jpa.repository.StudentRepository;
import com.demo.jpa.repository.TestCourseDataJpaRepository;

@SpringBootApplication
@EnableCaching
public class JpaInDepthDemoApplication implements CommandLineRunner {
	@Autowired
	private TestCourseDataJpaRepository testCourseDataJpaRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaInDepthDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//studentRepository.setEmbeddedMarks();
	  List<TestCourse> allCoursesOne = testCourseDataJpaRepository.getAllCoursesOne(10L);
	  System.out.println("ONE");
	  System.out.println(allCoursesOne);
	  allCoursesOne = testCourseDataJpaRepository.getAllCoursesOne(10L);
	  System.out.println("ONE");
    System.out.println(allCoursesOne);
    List<TestCourse> allCoursesTwo = testCourseDataJpaRepository.getAllCoursesTwo(10L);
    System.out.println("TWO");
    System.out.println(allCoursesTwo);
	}

}
