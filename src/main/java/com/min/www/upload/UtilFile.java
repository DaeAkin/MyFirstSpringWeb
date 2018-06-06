package com.min.www.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component
public class UtilFile {
	
	/*
	 * In - Read - 읽기 전용 빨대
	 * Out - Write - 쓰기 전용 빨대
	 * 데터를 읽는 대상 (읽기용 빨대를 꽂는 대상)
	 * 파일데이터 / 키보드 / 네트워크 / 이미지나 동영상 데이터 
	 * 
	 * 데이터를 쓰는 대상 (쓰기용 빨대를 꽂는 대상
	 * 파일 데이터 / 모니터의 화면 / 네트워크를 통한 데이터 전송 
	 */
	//프로젝트 내 지정된 경로에 파일을 저장하는 메소드
	//DB에는 업로드된 전체 경로명만으로 지정되기 때문에(업로드한 파일 자체는 경로에 저장됨)
	//fileUpload() 메소드에서 전체 경로를 리턴받아 DB에 경로 그대로 저장.
	public String fileUpload(MultipartHttpServletRequest request, MultipartFile uploadFile, Object obj) {
		String path="";
		String fileName = "";
		
		OutputStream out = null;
		PrintWriter printWriter = null;
		
		try {
			fileName = uploadFile.getOriginalFilename();
			byte[] bytes = uploadFile.getBytes();
			path = getSaveLocation(request,obj);
			
			System.out.println("UtilFile fileUpload fileName : " + fileName);
			System.out.println("UtilFile fileUpload uploadPath : " + path );
			
			File file = new File(path);
			
			//파일명이 중복으로 존재할 경우
			if (fileName != null && !fileName.equals("")) {
				if(file.exists()) {
					// 파일명 앞에 업로드 시간 초단위로 붙여 파일명 중복을 방지.
					fileName = System.currentTimeMillis() + "_" + fileName;
					
					file = new File(path + fileName);
							
			
				}
			}
			
			System.out.println("UtilFile fileUpload final fileName : " + fileName);
			System.out.println("UtilFile fileUpload file :" + file);
			
			out = new FileOutputStream(file);
			
			System.out.println("UtilFile fileUpload out : " + out);
			
			out.write(bytes);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				
				if(out != null) {
					out.close();
				}
				
				if(printWriter != null) {
					printWriter.close();
				}
				
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		return path + fileName;
	}
	
	// 업로드 파일 저장 경로 얻는 메소드 
	// 업로드한 파일의 경로가 도메인 별로 달라야 했기 때문에 도메인의 형을 비교하여 파일 저장경로를 다르게 지정함.
	
	private String getSaveLocation(MultipartHttpServletRequest request, Object obj) {
		
		String uploadPath = request.getSession().getServletContext().getRealPath("/");
		String attachPath = "resources/files";
		/* 
		 * 어떤 타입의 대한 instanceof 연산의 결과가 true라는 것은 검사한 타입으로 형 변환이 가능하다는 것을 뜻한다.
		 * OO instanceof 피연산
		 */
//
       
        System.out.println("UtilFile getSaveLocation path : " + uploadPath + attachPath);
        
        return uploadPath + attachPath;



	}

}
