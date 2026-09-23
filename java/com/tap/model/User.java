package com.tap.model;

import java.sql.Timestamp;

public class User {
	
	private int id;
	private String name;
	private String email;
	private String password;
	private String phone;
	private String adress;
	private String role;
	private Timestamp createdDate;
	private Timestamp lastLoginDate;
	
	
	
	
	
	

	public User(int id, String name, String email, String password, String phone, String adress, String role,
			Timestamp createdDate, Timestamp lastLoginDate) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	    this.password = password;
		this.phone = phone;
		this.adress = adress;
		this.role = role;
		this.createdDate = createdDate;
		this.lastLoginDate = lastLoginDate;
	}





	public User(String name, String email, String password, String phone, String adress, String role) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.adress = adress;
		this.role = role;
	}



	public User(String name, String email, String password, String phone, String adress, String role,
			Timestamp createdDate, Timestamp lastLoginDate) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.adress = adress;
		this.role = role;
		this.createdDate = createdDate;
		this.lastLoginDate = lastLoginDate;
	}
	
	

	
	public int getId() {
		return id;
	}


    public void setId(int id) {
		this.id = id;
	}



    public Timestamp getCreatedDate() {
		return createdDate;
	}


    public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}


    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Timestamp getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Timestamp lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", phone=" + phone
				+ ", adress=" + adress + ", role=" + role + ", createdDate=" + createdDate + ", lastLoginDate="
				+ lastLoginDate + "]";
	}





	
	
	

}
