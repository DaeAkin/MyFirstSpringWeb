package com.min.www.Service.webSocket;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.min.www.dao.webSocket.WebSocketReplyDao;
import com.min.www.dto.webSocket.WebSocketReplyDto;

@Service
public class WebSocketReplyServiceImpl implements WebSocketReplyService {
	
	@Resource(name="WebSocketReplyDao")
	WebSocketReplyDao webSocketReplyDao;

	@Override
	public void insertReplys(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<WebSocketReplyDto> getAlterReplys(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return webSocketReplyDao.getAlterReplys(paramMap);
	}

	@Override
	public void updateIsAlerted(Map<String, Object> paramMap) {
		

		
		webSocketReplyDao.updateIsAlerted(paramMap);
		
	}

}
