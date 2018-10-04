package com.min.www.Service.webSocket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;

import com.min.www.Service.WebSocketHandler;
import com.min.www.dto.webSocket.WebSocketReplyDto;

public class WebSocketAdvice implements MethodInterceptor {

	
	@Autowired
	WebSocketHandler webSocketHandler;
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		
		Map<String, Object> paramMap = new HashMap<>();
		
		List<WebSocketReplyDto> webSocketReplyDtos =
				webSocketReplyService.getAlterReplys(paramMap);
		System.out.println(" WebSocketAdvice 호출");
		
		return null;
	}
	
	

}
