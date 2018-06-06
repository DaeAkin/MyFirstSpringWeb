package com.min.www.dao.webSocket;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.www.dto.webSocket.WebSocketReplyDto;
@Repository("WebSocketReplyDao")
public class WebSocketReplyDaoImpl implements WebSocketReplyDao{

	
	@Autowired
	SqlSession sqlsession;
	
	
	@Override
	public void insertReplys(Map<String, Object> paramMap) {
		
		
	}

	@Override
	public List<WebSocketReplyDto> getAlterReplys(Map<String, Object> paramMap) {
		// 
		return sqlsession.selectList("getAlterReplys");
	}

	@Override
	public void updateIsAlerted(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		
		 sqlsession.update("updateIsAlerted", paramMap);
	}
	
	
	
}
