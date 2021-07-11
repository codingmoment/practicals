package com.demo.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.jpa.entity.TestCourse;

@Repository
public interface TestCourseDataJpaRepository extends JpaSpecificationExecutor<TestCourse>, JpaRepository<TestCourse, Long> {
  @Query(value = "SELECT c.id AS id, c.name AS name, 100 AS marks FROM course c", nativeQuery = true)
  List<TestCourse> getAllCoursesOne(@Param("resourceId") Long resourceId);

  @Query(value = "SELECT c.id AS id, c.name AS name, 200 AS marks FROM course c", nativeQuery = true)
  List<TestCourse> getAllCoursesTwo(@Param("resourceId") Long resourceId);

}
