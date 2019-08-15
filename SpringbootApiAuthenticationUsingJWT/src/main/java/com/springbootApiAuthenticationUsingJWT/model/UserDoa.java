package com.springbootApiAuthenticationUsingJWT.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
@Entity
@Table(name="userDoa")
public class UserDoa {
	@Id
	@GeneratedValue
	@Column(name="useId")
	int useId;
	
	@Column(name="userName")
	String userName;
	
	@Column(name="userPassword")
	@JsonIgnore //don't send this field to client during api hitting
	String userPassword;

	public int getUseId() {
		return useId;
	}

	public void setUseId(int useId) {
		this.useId = useId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Override
	public String toString() {
		return "UserDoa [useId=" + useId + ", userName=" + userName + ", userPassword=" + userPassword + "]";
	}
}
