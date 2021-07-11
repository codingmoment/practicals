package com.demo.jpa.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.jpa.TupleToEntity;
import com.demo.jpa.entity.Student;

@SpringBootTest
public class MultipleSelectUsingCriteriaQueryTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(JpqlTests.class);

	@Autowired
	private EntityManager entityManager;

	@Test
	void testMultiColumnProjections() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Tuple> cq = cb.createTupleQuery();
		Root<Student> root = cq.from(Student.class);
		cq.multiselect(root.get("name").alias("name"), root.get("id").alias("id"));
		List<Tuple> resultList = entityManager.createQuery(cq).getResultList();
		// List<Student> students = ReflectionUtility.createInstances(Student.class,
		// resultList, Arrays.asList("name", "id"));
		List<Student> students = TupleToEntity.transformTupleListIntoEntityList(Student.class, resultList,
				Arrays.asList("name", "id"));
		LOGGER.info("Students: {}", students);
		assertFalse(resultList.isEmpty());
	}

}
