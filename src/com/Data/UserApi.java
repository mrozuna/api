package com.Data;


public class UserApi {
 
	private String nameUser;

	private Long id;
	private String email;

//	private User user;

	private long totalPoints;

	public UserApi() {
		// TODO Auto-generated constructor stub
	}

	public long getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(long totalPoints) {
		this.totalPoints = totalPoints;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}
/*
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
*/

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
