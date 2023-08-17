package com.hrm.main.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity(name = "RegisterUser")
public class RegisterUserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
	@SequenceGenerator(name = "id_seq", initialValue = 1, allocationSize = 1, sequenceName = "id_seq")
	private int Id;
	private String userName;
	private String emailId;
	private String password;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RegisterUserEntity(int id, String userName, String emailId, String password) {
		super();
		Id = id;
		this.userName = userName;
		this.emailId = emailId;
		this.password = password;
	}

	public RegisterUserEntity() {
		super();
	}

}
