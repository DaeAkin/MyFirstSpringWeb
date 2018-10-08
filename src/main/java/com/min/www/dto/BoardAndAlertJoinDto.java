package com.min.www.dto;

import java.sql.Date;

public class BoardAndAlertJoinDto {

	

	// boardReplyDto
	private String reply_id;
	private String IMAGEURL;
	private String ORIGINALIMAGEURL;
	
	
	//BordReplyAlert Dto
	private int alert_id;
//	private int board_id;
//	private int reply_id;
	private int isalerted;
	
	
	//replyDto
	
	private String board_id;
	private String parent_id;
	private String depth;
	private String reply_content;
	private String reply_writer;
	private String reply_password;
	private Date register_datetime;
	
	
	public BoardAndAlertJoinDto() {
		// TODO Auto-generated constructor stub
	}
	
	public BoardAndAlertJoinDto(String reply_id,String board_id,String parent_id,String depth
			,String reply_content,String reply_writer,String reply_password,Date register_datetime,
			String IMAGEURL,String ORIGINALIMAGEURL,int alert_id,int isalert) {
		// TODO Auto-generated constructor stub
		this.reply_id = reply_id;
		this.board_id = board_id;
		this.parent_id = parent_id;
		this.depth = depth;
		this.reply_content = reply_content;
		this.reply_writer = reply_writer;
		this.reply_password = reply_password;
		this.register_datetime = register_datetime;
		this.IMAGEURL = IMAGEURL;
		this.ORIGINALIMAGEURL = ORIGINALIMAGEURL;
		this.alert_id = alert_id;
		this.isalerted = isalert;
	}

	public String getReply_id() {
		return reply_id;
	}

	public void setReply_id(String reply_id) {
		this.reply_id = reply_id;
	}

	public String getBoard_id() {
		return board_id;
	}

	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getDepth() {
		return depth;
	}

	public void setDepth(String depth) {
		this.depth = depth;
	}

	public String getReply_content() {
		return reply_content;
	}

	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}

	public String getReply_writer() {
		return reply_writer;
	}

	public void setReply_writer(String reply_writer) {
		this.reply_writer = reply_writer;
	}

	public String getReply_password() {
		return reply_password;
	}

	public void setReply_password(String reply_password) {
		this.reply_password = reply_password;
	}

	public Date getRegister_datetime() {
		return register_datetime;
	}

	public void setRegister_datetime(Date register_datetime) {
		this.register_datetime = register_datetime;
	}

	public String getIMAGEURL() {
		return IMAGEURL;
	}

	public void setIMAGEURL(String iMAGEURL) {
		IMAGEURL = iMAGEURL;
	}

	public String getORIGINALIMAGEURL() {
		return ORIGINALIMAGEURL;
	}

	public void setORIGINALIMAGEURL(String oRIGINALIMAGEURL) {
		ORIGINALIMAGEURL = oRIGINALIMAGEURL;
	}

	public int getAlert_id() {
		return alert_id;
	}

	public void setAlert_id(int alert_id) {
		this.alert_id = alert_id;
	}

	public int getIsalerted() {
		return isalerted;
	}

	public void setIsalerted(int isalerted) {
		this.isalerted = isalerted;
	}
}
