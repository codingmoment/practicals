package com.demo.jpa.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.demo.jpa.entity.Course;
import com.demo.jpa.entity.Marks;
import com.demo.jpa.entity.Passport;
import com.demo.jpa.entity.Student;

@Repository
@Transactional
public class StudentRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public Student findById(int id) {
		return entityManager.find(Student.class, id);
	}

	public void deleteById(int id) {
		Student student = findById(id);
		entityManager.remove(student);
	}

	public Student save(Student student) {
		if (student.getId() == null) {
			entityManager.persist(student);
		} else {
			student = entityManager.merge(student);
		}
		return student;
	}

	public void playWithOneToOneRelationship() {
		Passport passport = new Passport("Z123456");
		entityManager.persist(passport);
		Student student = new Student("Harsh");
		student.setPassport(passport);
		entityManager.persist(student);
	}

	public int createStudentWithCourse(Student student, Course course) {
		student.getCourses().add(course);
		course.getStudents().add(student);

		entityManager.persist(student);
		entityManager.persist(course);

		return student.getId();
	}

	public void addCourseToStudent(int studentId, Course course) {
		Student student = findById(studentId);
		student.getCourses().add(course);
		course.getStudents().add(student);

		entityManager.persist(student);
		entityManager.persist(course);
	}

	public void setEmbeddedMarks() {
		Student student = findById(1001);
		student.setMarks(new Marks(90, 30, 50));
		save(student);
		findById(1001);
	}

}
