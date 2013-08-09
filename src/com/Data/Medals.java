package com.Data;






public class Medals {

	private Long idMedals;

	
	private long total;

	
	private String MedalsType;


	private long userId;

	public Medals() {
		// TODO Auto-generated constructor stub
	}

	public Long getIdMedals() {
		return idMedals;
	}

	public void setIdMedals(Long idMedals) {
		this.idMedals = idMedals;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}


	public String getMedalsType() {
		return MedalsType;
	}

	public void setMedalsType(String medalsType) {
		MedalsType = medalsType;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
}
