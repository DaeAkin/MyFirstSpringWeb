package com.min.www.controller;



/*
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	@Resource(name="uploadPath")
	String uploadPath;
	
	@RequestMapping(value="/upload/uploadForm",method=RequestMethod.GET)
	public void uploadForm() {
		
	}
	
	@RequestMapping(value="/upload/uploadForm", method=RequestMethod.POST)
	public ModelAndView uploadForm(MultipartFile file, ModelAndView mav) throws Exception {
		//파일의 원본이름 저장
		String savedName = file.getOriginalFilename();
		
		logger.info("파일이름 : " +file.getOriginalFilename());
		logger.info("파일크기 :" +file.getSize());
		logger.info("컨텐트 타입 : " +file.getContentType());
		
		return mav;
	}
	
	

}
*/