package com.min.www.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.www.dto.BoardAndAlertJoinDto;
import com.min.www.dto.BoardDto;
import com.min.www.dto.BoardOptionsDto;
import com.min.www.dto.BoardReplyDto;
import com.min.www.util.TimeUtil;

@Repository("boardDao")
public class BoardDaoImpl implements BoardDao{
	
	@Autowired
	private SqlSession sqlSession;
	 
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	/* SqlSession setter를 명시해줬음에도 불구하고 DI가 되질 않고 
		@Autowired을 명시해줘야 DI가 되는걸까? XML오류인가? 
		
	 */




	@Override
	public int getContentCtn(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		System.out.println("sql" + sqlSession);
		return sqlSession.selectOne("selectContentCnt", paramMap);
	}

	@Override
	public List<BoardDto> getContentList(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		
		return sqlSession.selectList("selectContent", paramMap);
	
	}

	@Override
	public BoardDto getContentView(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("selectContentView",paramMap);
	}

	@Override
	public void regReply(Map<String, Object> parMap) {
		// TODO Auto-generated method stub
		 sqlSession.insert("insertBoardReply", parMap);
	}

	@Override
	public List<BoardReplyDto> getReplyList(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("selectBoardReplyList", paramMap);
	}

	@Override
	public int delReply(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlSession.delete("deleteBoardReply",paramMap);
	}

	@Override
	public int getBoardCheck(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("selectBoardCnt",paramMap);
	}

	@Override
	public int delBoard(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlSession.delete("deleteBoard",paramMap);
	}
	@Override
	public int regContent(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		// 알림용 DB에도 추가해주기. 
			
		return sqlSession.insert("insertContent",paramMap);
	}
	@Override
	public int insertFile(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlSession.insert("insertFile",paramMap);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.min.www.dao.BoardDao#getSearchedContentCnt(java.util.Map)
	 * 
	 * 제목+내용으로 검색할 경우 호출되는 DAO 메소드이다.
	 */

	@Override
	public List<BoardDto> getSearchedContentCntList(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		List<BoardDto> resultDto = sqlSession.selectList("selectSeachedContentCntList",paramMap);
		System.out.println("제목 +내용 검색 DAO()");
		/*
		 *  데이터 씻기고 return 해주가 .
		 */
		return TimeUtil.TimeUtilChanger(resultDto);
	}
	
	/*
	 * 
	 * (non-Javadoc)
	 * @see com.min.www.dao.BoardDao#getSearchedContentCnt2(java.util.Map)
	 * 
	 * 글쓴이로만 검색할 경우 호출되는 DAO 메소드이다. 
	 */

	@Override
	public List<BoardDto> getSearchedContentCntList2(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		List<BoardDto> resultDto = sqlSession.selectList("selectSeachedContentCntList2",paramMap);
		return TimeUtil.TimeUtilChanger(resultDto);
	}
	/*
	 * 검색할 건이 몇개인지리턴하는 DAO
	 * 
	 * (non-Javadoc)
	 * @see com.min.www.dao.BoardDao#searchedContentCnt(java.util.Map)
	 */
	@Override
	public int searchedContentCnt(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("selectSeachedContentCnt",paramMap);
	}

	
	/*
	 * 소켓을 이용한 댓글갯수 가져오기.
	 * (non-Javadoc)
	 * @see com.min.www.dao.BoardDao#getSocketReply(java.util.Map)
	 */
	@Override
	public List<BoardReplyDto> getSocketReply(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("selectSocketReply",paramMap);
	}



	@Override
	public void deleteAllContent() {
		// TODO Auto-generated method stub
		System.out.println("check");
	sqlSession.delete("deleteAllContent");
	
	}




	@Override
	public void testSys() {
		// TODO Auto-generated method stub
		sqlSession.insert("test");
	}




	@Override
	public BoardDto getBoardOne(int id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("getBoardOne",id);
	}




	@Override
	public void deleteAllReply() {
		// TODO Auto-generated method stub
		
		sqlSession.delete("deleteAllReply");
	}




	@Override
	public BoardReplyDto getBoardReplyOne(String reply_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("getReplyOne",reply_id);
	}

	@Override
	public void deleteAllBoard() {
		// TODO Auto-generated method stub
		
		sqlSession.delete("deletelAllBoard");
		
	}

	@Override
	public int getBoardCnt() {
		// TODO Auto-generated method stub
		
		return sqlSession.selectOne("getBoardCnt");
	}

	@Override
	public void boardAgree(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void boardDisgree(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertBoardAgree(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		sqlSession.insert("insertBoardAgree",paramMap);
		
	}

	@Override
	public BoardOptionsDto getBoardAgreeAndDisagreeOne(int boardid) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("getBoardAgreeAndDisagreeOne",
				boardid);
	}

	@Override
	public void deleteAllBoardAgree() {
	
		
		sqlSession.delete("deleteAllBoardAgree");
		
	}

	@Override
	public BoardOptionsDto isCanAgreeWithBoard(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("isCanAgreeWithBoard" , paramMap);
	}

	@Override
	public int getBoardReplyCnt() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("getBoardReplyCnt");
		
	}

	@Override
	public int boardAlertCnt() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("boardAlertCnt");
	}

	@Override
	public void deleteAllBoardAlert() {
		// TODO Auto-generated method stub
			sqlSession.delete("deleteAllBoardAlert");
	}

	@Override
	public void checkingTheBoardReply(int alertid) {
		// TODO Auto-generated method stub
		
		sqlSession.update("checkingTheBoardReply",alertid);
		
	
	}

	@Override
	public List<BoardAndAlertJoinDto> getAlerts(String writer) {
		// TODO Auto-generated method stub
		
		System.out.println("boardDao의 getAlert() 들어옴 ");
		System.out.println("test용 getAlerts writer :" +writer);
		System.out.println("getAlerts : 전");
		
		List<BoardAndAlertJoinDto> list = sqlSession.selectList("getAlerts",writer);
		System.out.println("getAlerts : 후");
		System.out.println("size test : " + list.size());
		return 	sqlSession.selectList("getAlerts",writer);
	}

	@Override
	public void insertReplyAlert(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		sqlSession.insert("insertReplyAlert",paramMap);
	}

	@Override
	public BoardReplyDto getOneReply(String reply_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("getOneReply",reply_id);
	}

	@Override
	public int returnTest() {
		// TODO Auto-generated method stub
		return 5;
	}



//	@Override
//	public void likeBoardAgree(Map<String, Object> paramMap) {
//		// TODO Auto-generated method stub
//		sqlSession.insert("likeBoardAgree",paramMap);
//	}
//
//	@Override
//	public void dislikeBoardDisagree(Map<String, Object> paramMap) {
//		// TODO Auto-generated method stub
//		sqlSession.insert("dislikeBoardDisagree",paramMap);
//		
//	}
	
	
}
