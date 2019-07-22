package com.javacode.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rating_jobs")
public class RatingJob {
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "rating")
	private float rating;

	@ManyToOne 
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne 
	@JoinColumn(name = "job_id", nullable = false)
	private Job job;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public RatingJob(int id, float rating, User user, Job job) {
		this.id = id;
		this.rating = rating;
		this.user = user;
		this.job = job;
	}

}
