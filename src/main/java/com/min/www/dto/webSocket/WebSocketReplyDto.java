package com.min.www.dto.webSocket;

public class WebSocketReplyDto {

	// freeboardreplyalter Dtos
	private int alert_id;
	private int board_id;
	private int reply_id;
	private int isalerted;
	
	// freeboard Dtos
	
	private int id;
	private String title;
	private String writer;
	private String writetime;
	private String content;
	private int hit;
	
//	public WebSocketReplyDto() {
//		// TODO Auto-generated constructor stub
//	}
//	
//	public WebSocketReplyDto(int alert_id, int board_id,int reply_id,int isaltered ) {
//		// TODO Auto-generated constructor stub
//		this.alert_id = alert_id;
//		this.board_id = board_id;
//		this.reply_id = reply_id;
//		this.isaltered = isaltered;
//		
//				
//	}


	public int getAlert_id() {
		return alert_id;
	}

	public void setAlert_id(int alert_id) {
		this.alert_id = alert_id;
	}

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public int getReply_id() {
		return reply_id;
	}

	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}

	public int getIsaltered() {
		return isalerted;
	}

	public void setIsaltered(int isaltered) {
		this.isalerted = isaltered;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getWritetime() {
		return writetime;
	}

	public void setWritetime(String writetime) {
		this.writetime = writetime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}
	
	
	
	
	
	
}
