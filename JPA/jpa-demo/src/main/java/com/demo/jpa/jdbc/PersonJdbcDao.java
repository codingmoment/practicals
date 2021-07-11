package com.demo.jpa.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.demo.jpa.entity.Person;

@Repository
public class PersonJdbcDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	class PersonRowMapper implements RowMapper<Person> {
		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			Person person = new Person();
			person.setId(rs.getInt("id"));
			person.setName(rs.getString("name"));
			person.setLocation(rs.getString("location"));
			person.setBirthDate(rs.getTimestamp("birth_date"));
			return person;
		}
	}

	public List<Person> findAll() {
		return jdbcTemplate.query("SELECT id, name, location, birth_date FROM person",
				new BeanPropertyRowMapper<Person>(Person.class));
	}

	public List<Person> findAllCustomRowMapper() {
		return jdbcTemplate.query("SELECT id, name, location, birth_date FROM person", new PersonRowMapper());
	}

	public Person findById(Integer id) {
		return jdbcTemplate.queryForObject("SELECT id, name, location, birth_date FROM person WHERE id = ?",
				new Object[] { id }, new BeanPropertyRowMapper<Person>(Person.class));
	}

	public int deleteById(Integer id) {
		return jdbcTemplate.update("DELETE person WHERE id = ?", id);
	}

	public int insert(Person person) {
		return jdbcTemplate.update("INSERT INTO person (id, name, location, birth_date) VALUES (?, ?, ?, ?)",
				person.getId(), person.getName(), person.getLocation(), new Timestamp(person.getBirthDate().getTime()));
	}

	public int update(Person person) {
		return jdbcTemplate.update("UPDATE person SET id = ?, name = ?, location = ?, birth_date = ? WHERE id = ?",
				person.getName(), person.getLocation(), new Timestamp(person.getBirthDate().getTime()), person.getId());
	}
}
