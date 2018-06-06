package com.min.www.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.min.www.dao.BoardDao;
import com.min.www.dto.BoardReplyDto;
import com.min.www.util.BoardReplyAlter;
import com.min.www.util.TimeUtil;

@Service
public class WebSocketHandler extends TextWebSocketHandler{
	
	@Autowired
	BoardDao boardDao;
	
	private final Logger logger = LogManager.getLogger(getClass());
	
	/*
	 *  웹 소켓이 종료되고 나서 서버단에서 실행해야 할 일들을 정의해주는 메소드 
	 * (non-Javadoc)
	 * @see org.springframework.web.socket.handler.AbstractWebSocketHandler#afterConnectionClosed(org.springframework.web.socket.WebSocketSession, org.springframework.web.socket.CloseStatus)
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("소켓 닫힘 ");
	}
	/*
	 *  연결이 성사되고 나서 해야할 일들.
	 * (non-Javadoc)
	 * @see org.springframework.web.socket.handler.AbstractWebSocketHandler#afterConnectionEstablished(org.springframework.web.socket.WebSocketSession)
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("소켓 생성");
	}
	
	/*
	 * 웹소켓 서버단으로 메세지가 도착했을 때 해주어야할 일들을 정의하는 메소드 
	 * 
	 * 가장 중요한 메소드이다 .
	 * jsp파일에서 클라이언트가 현재 접속중인 닉네임을 웹소켓을 통해서 서버로 보내면 이 메소드가 실행된다.
	 * 그 현재 접속중인 닉네임은 meesage.getPlayload() 라는 함수를 통해서 메세지에 담긴 텍스트값을 얻을 수 있다.
	 * 
	 * 즉 클라이언트로부터 현재 접속중인 사용자의 닉네임을 얻고 그것을 통해 게시물의 댓글을 알려주는 ~~
	 * (non-Javadoc)
	 * @see org.springframework.web.socket.handler.AbstractWebSocketHandler#handleTextMessage(org.springframework.web.socket.WebSocketSession, org.springframework.web.socket.TextMessage)
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// TODO Auto-generated method stub
		 try {
		System.out.println("###### handleTextMessage satrt######");
		Map<String, Object> paramMap = new HashMap<>();
		
		List<BoardReplyDto> boardReplyDtos;
		
		paramMap.put("id", message.getPayload()); // jsp에서 소켓으로 보낸 메세지를 받음 ( 지금은 닉네임받을 예정) 
		
		boardReplyDtos = boardDao.getSocketReply(paramMap);  // 소켓에서 보낸 닉네임을 DB 넣어서 사용자가 쓴 게시물의 댓글들을 가져옴.
		
		
//		System.out.println(boardReplyDtos.get(0).getRegister_datetime());
//		
//		for(int i=0; i<boardReplyDtos.size(); i++) {
//			System.out.println(boardReplyDtos.get(i).getRegister_datetime());
//
//		}
		
		
//		int count = TimeUtil.timeComparison(paramMap, boardReplyDtos); // 로그아웃 한 시점보다 날짜가 더 최신인 댓글 갯수 
		
		int count = BoardReplyAlter.getReplys(paramMap, boardReplyDtos); // 로그인한 유저가 쓴 게시글의 모든 댓글을 가져온다.
		
		
		/*
		 * 댓글 달때 freeboardreplyalter 에도 값 넣어 줘야한다 . 나중에 꼭 수정할 것 지금은 임의로 데이터를 넣었다. 
		 */
		

		
		
	
		
		System.out.println("댓글의 갯수 ? " + count);
		System.out.println("소켓에서 받은 메세지 :" +message.getPayload());
		
		session.sendMessage(new TextMessage(count+"")); // count +"" 문자열로 변
		
		/*
		 * 
		 *  테스트용 코드 
		 */
		
		System.out.println("###### handleTextMessage end######");
		 } catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
		}
		
	}

}
