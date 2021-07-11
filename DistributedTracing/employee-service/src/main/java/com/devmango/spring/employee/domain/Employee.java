package com.devmango.spring.employee.domain;

import java.time.LocalDate;
import java.util.EnumSet;

public class Employee {
	private Long employeeId;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String emailId;
	private EnumSet<Role> roles = EnumSet.of(Role.DEVELOPER, Role.TECH_LEAD, Role.SCRUM_MASTER);

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public EnumSet<Role> getRoles() {
		return roles;
	}

	public void setRoles(EnumSet<Role> roles) {
		this.roles = roles;
	}
}
