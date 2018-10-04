package com.min.www.dto;

// 좋아요 , 싫어요 Dto
public class BoardOptionsDto {
	
	private int boardId;
	private String agree;
	private String disagree;
	
	public BoardOptionsDto() {
		// TODO Auto-generated constructor stub
	}
	
	public BoardOptionsDto(int boardId, String agree, String disagree) {
		// TODO Auto-generated constructor stub
		this.boardId = boardId;
		this.agree = agree;
		this.disagree = disagree;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getAgree() {
		return agree;
	}

	public void setAgree(String agree) {
		this.agree = agree;
	}

	public String getDisagree() {
		return disagree;
	}

	public void setDisagree(String disagree) {
		this.disagree = disagree;
	}
	
	
	

}
