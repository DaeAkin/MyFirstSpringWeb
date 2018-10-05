package com.min.www.test;

import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import com.min.www.Service.member.MemberService;

import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.intThat;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Matchers.isNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ImageUploadTest {

	@Autowired
	MemberService memberService;
	
	//경로 구분자는 윈도우 \(백슬래쉬)를 사용, 맥은 /(슬래쉬) 
	String uploadUrl = "/Users/mindonghyeon/profileImage/";
	
	File file;
	
	byte[] byteFile;
	
	OutputStream os;
	@Before
	public void setUp() throws IOException {
		
		
		file = new File("/Users/mindonghyeon/imageUpload/" + "puppy.jpeg");
		
		
		
//		FileInputStream is = new FileInputStream(file);
//		BufferedReader br = new BufferedReader(new FileReader("/Users/mindonghyeon/imageUpload/" + "puppy.jpeg"));
//        while(true) {
//            String line = br.readLine();
//            if (line==null) break;
//            System.out.println(line);
//        }
		
//		  int i = 0;
//          while((i = is.read()) != -1) {
//
//
//
//		byteFile = IOUtils.toByteArray(is);
		
		
          
		
		
		
	}
	
	@Test
	public void copyFile() throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader("/Users/mindonghyeon/imageUpload/" + "puppy.jpeg"));
        while(true) {
        	System.out.println("??");
            String line = br.readLine();
            if (line==null) break;
            System.out.println(line);
            
        }
		
//		File file2 = new File(uploadUrl+"pp.jpeg");
//		
//		
//		FileCopyUtils.copy(byteFile, file2);
	}
}
