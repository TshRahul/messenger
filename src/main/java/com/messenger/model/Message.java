package com.messenger.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "MESSAGES")
public class Message {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "message")
	@NotEmpty(message = "message must not be empty")
	private String message;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "created_date")
	private Date created_date;
	
	@Column(name = "modified_date")
	private Date modified_date;
	
	@Column(name = "nooflikes")
	private long noOfLikes;
	
	@Column(name = "noofcomments")
	private long noOfComments;
	
	@Column(name = "isdeleted")
	private boolean isDeleted;

	@OneToMany(mappedBy = "message")
	private List<Comment> comments;
	
	
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public Date getModified_date() {
		return modified_date;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	public long getNoOfLikes() {
		return noOfLikes;
	}
	public void setNoOfLikes(long noOfLikes) {
		this.noOfLikes = noOfLikes;
	}
	public long getNoOfComments() {
		return noOfComments;
	}
	public void setNoOfComments(long noOfComments) {
		this.noOfComments = noOfComments;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", message=" + message + ", author=" + author + ", created_date=" + created_date
				+ ", modified_date=" + modified_date + ", noOfLikes=" + noOfLikes + ", noOfComments=" + noOfComments
				+ "]";
	}
	
	
	

}
