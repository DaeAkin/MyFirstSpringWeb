package com.min.www.dto;

public class MDto {
	
	private int Mnumber;
	private String id;
	private String password;
	
	 public MDto(int Mnumber,String id, String password) {
		// TODO Auto-generated constructor stub
		 Mnumber = this.Mnumber;
		 id = this.id;
		 password = this.password;
	}
	public MDto() {
		// TODO Auto-generated constructor stub
	}
	
	
	public int getMnumber() {
		return Mnumber;
	}
	public void setMnumber(int mnumber) {
		Mnumber = mnumber;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
