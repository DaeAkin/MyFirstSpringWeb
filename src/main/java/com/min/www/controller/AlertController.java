package com.min.www.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.min.www.Service.BoardService;
import com.min.www.Service.webSocket.WebSocketReplyService;
import com.min.www.dto.webSocket.WebSocketReplyDto;

@Controller
public class AlertController {
	
	@Autowired
	WebSocketReplyService webSocketReplyService;
	
	@Autowired
	BoardService boardService;
	/*
	 * alertViewTest 페이지로 이동.
	 */
	@RequestMapping(value ="alertView")
	public String AlterView(Map<String,Object> paramMap,Model model) {
		
		/*
		 *  게시글 제목에 댓글이 달렸습니다. 
		 *  [ 게시글 제목 ] 값은 번호로 달렸씁니다.
		 */

		List<WebSocketReplyDto> webSocketReplyDtos;
		
		
		webSocketReplyDtos = webSocketReplyService.getAlterReplys(paramMap);
		
		
		model.addAttribute("alerts", webSocketReplyDtos);
	
		
		return "alertViewTest";
	}
	
	
//	@RequestMapping(value ="alertView")
//	@ResponseBody
//	public Map<String, Object> getAlert(Map<String,Object> paramMap) {
//	
//		List<WebSocketReplyDto> webSocketReplyDtos;
//		Map<String, Object> resultMap = new HashMap<>();
//		
//		webSocketReplyDtos = webSocketReplyService.getAlterReplys(paramMap);
//		
//		resultMap.put("alerts", webSocketReplyDtos);
//		
//		return resultMap;
//	}
	
	/*
	 * 알림을 누르면 회색으로 변경해주게.
	 * isaltered가 0이면 확인을 안한 것이고, 1이면 확인을 한 것이다.
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/alert/checked")
	public void alertChecked(@RequestParam Map<String,Object> paramMap) {
		
		
		System.out.println("누름 확인");
		System.out.println("alert_id 의 값 :" + paramMap.get("alert_id"));
		
		webSocketReplyService.updateIsAlerted(paramMap);
	}
}
