package com.messenger.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "USER_DETAILS")
public class User {
	
	@Id
	@Column(name = "username")
	@NotEmpty(message = "user name must not be empty")
	String username;
	
	@Column(name = "firstname")
	@NotEmpty(message = "first name must not be empty")
	String Firstname;
	
	@Column(name = "lastname")
	@NotEmpty(message = "last name must not be empty")
	String Lastname;
	
	@Column(name = "password")
	@NotEmpty(message = "Password must not be empty")
	String password;
	
	@Column(name = "roles")
	@NotEmpty(message = "Roles must not be empty")
	String roles;
	@Column(name = "created_date")
	Date creation_date;
	@Column(name = "active")
	boolean active;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstname() {
		return Firstname;
	}
	public void setFirstname(String firstname) {
		Firstname = firstname;
	}
	public String getLastname() {
		return Lastname;
	}
	public void setLastname(String lastname) {
		Lastname = lastname;
	}
	
	@JsonIgnore
	@JsonProperty(value = "password")

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "User [ username=" + username + ", Firstname=" + Firstname + ", Lastname="
				+ Lastname + ", password=" + password + ", roles=" + roles + ", creation_date=" + creation_date
				+ ", active=" + active + "]";
	}
	
	
	
	

}
