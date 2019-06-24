package com.boot.reactor.core.model;

public class UserDetails {
	
	private String userName;
	private String userDept;
	private Long userSalary;
	
	public UserDetails() {
		
	}
	
	public UserDetails(String userName, String userDept, Long userSalary) {
		this.userName = userName;
		this.userDept = userDept;
		this.userSalary = userSalary;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserDept() {
		return userDept;
	}
	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}
	public Long getUserSalary() {
		return userSalary;
	}
	public void setUserSalary(Long userSalary) {
		this.userSalary = userSalary;
	}
	
	@Override
	public String toString() {
		return "UserDetails [userName=" + userName + ", userDept=" + userDept + ", userSalary=" + userSalary + "]";
	}
}
