package com.demo.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.demo.jpa.entity.Course;

@RepositoryRestResource(path = "courses")
public interface CourseDataJpaRepository extends JpaRepository<Course, Integer> {

	List<Course> findByName(String name);

	List<Course> findByNameAndId(String name, Integer id);

	List<Course> findByNameOrderById(String name);

	List<Course> findByNameOrderByIdDesc(String name);

	List<Course> countByName(String name);

	List<Course> deleteByName(String name);

	@Query("SELECT c FROM Course c")
	List<Course> getAllCoursesUsingJPQL();

	@Query(value = "SELECT * FROM Course c", nativeQuery = true)
	List<Course> getAllCoursesUsingNativeQuery();

	@Query(name = "get_all_courses")
	List<Course> getAllCoursesUsingNamedQuery();
}
