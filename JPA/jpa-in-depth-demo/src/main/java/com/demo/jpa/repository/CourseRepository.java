package com.demo.jpa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.demo.jpa.entity.Course;
import com.demo.jpa.entity.Review;

@Repository
@Transactional
public class CourseRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public Course findById(int id) {
		return entityManager.find(Course.class, id);
	}

	public void deleteById(int id) {
		Course course = findById(id);
		entityManager.remove(course);
	}

	public Course save(Course course) {
		if (course.getId() == null) {
			entityManager.persist(course);
		} else {
			course = entityManager.merge(course);
		}
		return course;
	}

	public void playWithPersistAndMerge() {
		// Creating a new course
		// course1 is "managed" entity
		Course course1 = new Course("Java");
		entityManager.persist(course1);

		// Updating an existing course
		Course course2 = new Course(300, "ReactJS 2.0");
		// course1 is "detached" entity
		entityManager.merge(course2);

		// Flush: This will insert course1 and update course2
		entityManager.flush();

		// Just updating the entities
		course1.setName("Java 14");
		course2.setName("ReactJS 3.0");

		// Flush
		// course1 being "managed" entity will be updated with new name.
		// course2 being "detached" entity will not be updated.
		entityManager.flush();
	}

	public void playWithDetach() {
		// Creating a new course
		// course1 is "managed" entity
		Course course1 = new Course("AngularJS");
		entityManager.persist(course1);

		// Flush: This will insert course1.
		entityManager.flush();

		// Just updating the entities
		course1.setName("Angular 2.0");

		// Flush
		// course1 being "managed" entity will be updated with new name.
		entityManager.flush();

		// Detaching course1
		entityManager.detach(course1);

		// Just updating the entities
		course1.setName("Angular 6");

		// Flush
		// course1 being "detached" entity will not be updated.
		entityManager.flush();
	}

	public void playWithClear() {
		// Creating a new course
		// course1 is "managed" entity
		Course course1 = new Course("Hibernate");
		entityManager.persist(course1);

		// Flush: This will insert course1.
		entityManager.flush();

		// Just updating the entities
		course1.setName("Hibernate 3.0");

		// Flush
		// course1 being "managed" entity will be updated with new name.
		entityManager.flush();

		// Detaching all entities
		entityManager.clear();

		// Just updating the entities
		course1.setName("Hibernate 5.0");

		// Flush
		// course1 being "detached" entity will not be updated.
		entityManager.flush();
	}

	public void playWithRefresh() {
		// Creating a new course
		// course1 is "managed" entity
		Course course1 = new Course("Javascript");
		entityManager.persist(course1);

		// Flush: This will insert course1.
		entityManager.flush();

		// Just updating the entities
		course1.setName("ES6");

		// Refresh course1 from database
		entityManager.refresh(course1);

		// Flush
		// course1 will be updated but with old values from DB
		entityManager.flush();
	}

	public void addReviewsForCourse(int courseId, List<Review> reviews) {
		Course course = findById(courseId);

		reviews.forEach(review -> {
			course.getReviews().add(review);
			review.setCourse(course);
			entityManager.persist(review);
		});
	}
}
