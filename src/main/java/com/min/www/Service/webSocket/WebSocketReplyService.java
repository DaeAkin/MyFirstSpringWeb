package com.min.www.Service.webSocket;

import java.util.List;
import java.util.Map;

import com.min.www.dto.webSocket.WebSocketReplyDto;

public interface WebSocketReplyService {
	
	public void insertReplys(Map<String, Object> paramMap);
	
	public List<WebSocketReplyDto> getAlterReplys(Map<String, Object> paramMap);

	public void updateIsAlerted(Map<String, Object> paramMap);
}
