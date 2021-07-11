package com.devmango.employee.domain;

import java.time.LocalDate;

public class Leave {
	private Long employeeId;
	private LocalDate leaveFrom;
	private LocalDate leaveTo;
	private String leaveType;

	public Leave(Long employeeId, LocalDate leaveFrom, LocalDate leaveTo, String leaveType) {
		super();
		this.employeeId = employeeId;
		this.leaveFrom = leaveFrom;
		this.leaveTo = leaveTo;
		this.leaveType = leaveType;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public LocalDate getLeaveFrom() {
		return leaveFrom;
	}

	public void setLeaveFrom(LocalDate leaveFrom) {
		this.leaveFrom = leaveFrom;
	}

	public LocalDate getLeaveTo() {
		return leaveTo;
	}

	public void setLeaveTo(LocalDate leaveTo) {
		this.leaveTo = leaveTo;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

}
