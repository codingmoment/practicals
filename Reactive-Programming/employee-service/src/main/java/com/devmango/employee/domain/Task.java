package com.devmango.employee.domain;

public class Task {
	private Long employeeId;
	private Long taskId;
	private String taskCode;
	
	public Task(Long employeeId, Long taskId, String taskCode) {
		super();
		this.employeeId = employeeId;
		this.taskId = taskId;
		this.taskCode = taskCode;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

}
