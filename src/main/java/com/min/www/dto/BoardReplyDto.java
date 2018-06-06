package com.min.www.dto;

import java.sql.Date;


public class BoardReplyDto {
	
	private String reply_id;
	private String board_id;
	private String parent_id;
	private String depth;
	private String reply_content;
	private String reply_writer;
	private String reply_password;
	private Date register_datetime;
	private String IMAGEURL;
	private String ORIGINALIMAGEURL;
	
	
	
	public BoardReplyDto() {
		// TODO Auto-generated constructor stub
	}
	
	public BoardReplyDto(String reply_id,String board_id,String parent_id,String depth
			,String reply_content,String reply_writer,String reply_password,Date register_datetime,
			String IMAGEURL,String ORIGINALIMAGEURL) {
		// TODO Auto-generated constructor stub
	}
	
	
	public String getORIGINALIMAGEURL() {
		return ORIGINALIMAGEURL;
	}

	public void setORIGINALIMAGEURL(String oRIGINALIMAGEURL) {
		ORIGINALIMAGEURL = oRIGINALIMAGEURL;
	}

	public String getIMAGEURL() {
		return IMAGEURL;
	}

	public void setIMAGEURL(String iMAGEURL) {
		IMAGEURL = iMAGEURL;
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
	
	
	
	

}
