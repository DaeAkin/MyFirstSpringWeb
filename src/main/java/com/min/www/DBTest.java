package com.min.www;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sun.crypto.provider.RSACipher;




public class DBTest {

	static final String	JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://127.0.0.1/myfirstweb?useSSL=false";
	
	static final String USERNAME = "root";
	static final String PASSWORD = "djaak412";
	
	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			System.out.println(" \n - MySql Connection");
			stmt = conn.createStatement();
			
			String sql;
			sql = "select id from freeboard";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String id = rs.getString("id");
				
				System.out.println("\n id =" + id);
				
			}
			
		rs.close();
		stmt.close();
		conn.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		
	}
			
}
