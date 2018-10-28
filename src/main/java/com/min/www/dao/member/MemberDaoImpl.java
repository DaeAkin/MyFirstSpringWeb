package com.min.www.dao.member;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.www.dto.member.MemberDto;

@Repository("MemberDao")
public class MemberDaoImpl implements MemberDao{
	
	@Autowired
	SqlSession sqlSession;

	@Override
	public List<MemberDto> getMemberlist(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("getMemberlist",paramMap);
	}

	@Override
	
	public MemberDto getMember(String id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("getMember", id);
	}

	@Override
	public int insertMember(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlSession.insert("insertMember", paramMap);
	}

	@Override
	public int deleteMember(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlSession.delete("deleteMember",paramMap);
	}

	@Override
	public int memberInvalidCheck(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("memberInvalidCheck",paramMap);
	}

	@Override
	public int memberNickCheck(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("memberNickCheck",paramMap);
	}

	@Override
	public void insertMemberImage(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		 sqlSession.insert("insertImage", paramMap);
	}

	@Override
	public void deleteAllMember() {
		// TODO Auto-generated method stub
		sqlSession.delete("deleteAllMember");
		
	}

	@Override
	public int selectMemberCnt() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("selectMemberCnt");
	}

	@Override
	public void memberEdit(Map<String, Object> paramMap) {
		sqlSession.update("memberEdit",paramMap);
		
	}
	
	

}
