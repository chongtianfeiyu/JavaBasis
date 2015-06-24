package com.startcaft.model;

public class Manager {

	public Integer managerId;
	public String managerName;
	
	public Department department;
	
	public Integer getManagerId() {
		return managerId;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
}
