package com.min.www.dto;

public class BoardDto {
	private int id;
	private String title;
	private String writer;
	private String writetime;
	private String content;
	private int hit;
	
	public BoardDto() {
		// TODO Auto-generated constructor stub
	}
	
	public BoardDto(int id,String title, String writer,String writetime,String content,int hit) {
		// TODO Auto-generated constructor stub
		id =this.id;
		title = this.title;
		writer = this.writer;
		writetime = this.writetime;
		hit = this.hit;
		content = this.content;
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
	public String getWritertime() {
		return writetime;
	}
	public void setWritertime(String writertime) {
		this.writetime = writertime;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	

}
