package com.messenger.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "LIKES")
public class Like {
	
	@Id
	@Column(name = "like_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long like_Id;
	@Column(name = "message_id")
	long message_id;
	
	@Column(name = "isliked")
	boolean isLiked;
	@Column(name = "liked_by")
	@NotEmpty(message = "Liked by must not be empty")
	String likedBy;
	
	@Column(name= "liked_date")
	private Date liked_date;
	
	
	
	public Like(int message_id, int like_Id, boolean isLiked, String likedBy) {
		super();
		this.message_id = message_id;
		this.like_Id = like_Id;
		this.isLiked = isLiked;
		this.likedBy = likedBy;
	}
	
	public long getMessage_id() {
		return message_id;
	}
	public void setMessage_id(long message_id) {
		this.message_id = message_id;
	}
	public long getLike_Id() {
		return like_Id;
	}
	public void setLike_Id(int like_Id) {
		this.like_Id = like_Id;
	}
	public boolean isLiked() {
		return isLiked;
	}
	public void setLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}
	public String getLikedBy() {
		return likedBy;
	}
	public void setLikedBy(String likedBy) {
		this.likedBy = likedBy;
	}

	public Date getCreated_date() {
		return liked_date;
	}

	public void setCreated_date(Date liked_date) {
		this.liked_date = liked_date;
	}

	@Override
	public String toString() {
		return "Like [message_id=" + message_id + ", like_Id=" + like_Id + ", isLiked=" + isLiked + ", likedBy="
				+ likedBy + "]";
	}
	
	
	
	

}
