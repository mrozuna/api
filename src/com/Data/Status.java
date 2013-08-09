package com.Data;



public class Status {

	private Long id;


	private long userId;


	private long gameWin;

	
	private long gameLost;

	private long gameDraw;

	
	private String statusGameWin;


	private String statusGameLost;

	
	private String statusGameDraw;
	private long total;

	public Status() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public long getGameWin() {
		return gameWin;
	}

	public void setGameWin(long gameWin) {
		this.gameWin = gameWin;
	}

	public long getGameLost() {
		return gameLost;
	}

	public void setGameLost(long gameLost) {
		this.gameLost = gameLost;
	}

	public long getGameDraw() {
		return gameDraw;
	}

	public void setGameDraw(long gameDraw) {
		this.gameDraw = gameDraw;
	}

	public String getStatusGameWin() {
		return statusGameWin;
	}

	public void setStatusGameWin(String statusGameWin) {
		this.statusGameWin = statusGameWin;
	}

	public String getStatusGameLost() {
		return statusGameLost;
	}

	public void setStatusGameLost(String statusGameLost) {
		this.statusGameLost = statusGameLost;
	}

	public String getStatusGameDraw() {
		return statusGameDraw;
	}

	public void setStatusGameDraw(String statusGameDraw) {
		this.statusGameDraw = statusGameDraw;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}



}
