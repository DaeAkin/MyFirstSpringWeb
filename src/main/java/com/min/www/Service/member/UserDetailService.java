package com.min.www.Service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.min.www.dto.member.MemberDto;

public class UserDetailService implements UserDetailsService{
	
	@Autowired
	private UserService

	@Override
	public UserDetails loadUserByUsername(String lgnId) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		MemberDto memberDto = null;
		
		try {
			System.out.println("id =============>" +lgnId);
			memberDto =
		}
		return null;
	}
	

}
