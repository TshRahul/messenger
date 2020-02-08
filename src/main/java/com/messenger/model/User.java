package com.messenger.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "USER_DETAILS")
public class User {

    @Id
    @Column(name = "username")
    @NotEmpty(message = "user name must not be empty")
    private String username;
	
	@Column(name = "firstname")
	@NotEmpty(message = "first name must not be empty")
	private String firstname;
	
	@Column(name = "lastname")
	@NotEmpty(message = "last name must not be empty")
	private String lastname;
	
	@Column(name = "password")
	@NotEmpty(message = "Password must not be empty")
    private String password;
	
	@Column(name = "email")
	@Email
	@NotEmpty(message = "Email must not be empty")
    private String email;
	
	@Column(name = "roles")
	@NotEmpty(message = "Roles must not be empty")
    private String roles;
	@Column(name = "created_date")
	Date creation_date;
	@Column(name = "active")
    private boolean active;
	
	 @Column(name="profile_image")  
	    public String profileImage;  
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	@JsonIgnore
	@JsonProperty(value = "password")

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	String getRoles() {
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
	boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	@Override
	public String toString() {
		return "User [ username=" + username + ", Firstname=" + firstname + ", Lastname="
				+ lastname + ", password=" + password + ", roles=" + roles + ", creation_date=" + creation_date
				+ ", active=" + active + "]" +  ", profileImage="  
                + profileImage + "]";
	}
	
	
	
	

}
