package com.min.www.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.www.dto.BoardDto;
import com.min.www.dto.BoardReplyDto;
import com.min.www.util.TimeUtil;

@Repository("boardDao")
public class BoardDaoImpl implements BoardDao{
	
	@Autowired
	private SqlSession sqlSession;
	 



	@Override
	public int getContentCtn(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("selectContentCnt", paramMap);
	}

	@Override
	public List<BoardDto> getContentList(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		List<BoardDto> resultDto = sqlSession.selectList("selectContent", paramMap);
		return TimeUtil.TimeUtilChanger(resultDto);
	
	}

	@Override
	public BoardDto getContentView(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("selectContentView",paramMap);
	}

	@Override
	public int regReply(Map<String, Object> parMap) {
		// TODO Auto-generated method stub
		return sqlSession.insert("insertBoardReply", parMap);
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
	public void insertAlert(Map<String, Object> paramMap) {
		sqlSession.insert("insertAlert",paramMap);
		
	}
	
}
