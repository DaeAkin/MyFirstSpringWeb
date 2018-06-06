package com.min.www.dto.member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class MemberDto implements UserDetails{
	String Mnumber;
	String id;
	String nickname;
	String password;
	String email;
	String imageurl;
	String ORIGINALIMAGEURL;
	private List<String> roles;
	
	
	
	
	public MemberDto() {
		// TODO Auto-generated constructor stub
	}
	
	public MemberDto(String Mnumber,String id,String nickname, String password, String email, String imageurl,String ORIGINALIMAGEURL) {
		// TODO Auto-generated constructor stub
		Mnumber = this.Mnumber;
		nickname = this.nickname;
		id = this.id;
		password = this.password;
		email = this.email;
		imageurl = this.imageurl;
		ORIGINALIMAGEURL = this.ORIGINALIMAGEURL;
		
	}
	
	/*
		Beginning UserDetails Interface
	 */
	/* 사용자에게 부여 된 권한을 리턴.
	 * (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<SimpleGrantedAuthority> grants = new ArrayList<>();
		for(String role : roles) {
			grants.add(new SimpleGrantedAuthority(role));
		}
		return grants;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return id;
	}
/*
 * 사용자 계정이 만료되었는지 여부를 나타
 * (non-Javadoc)
 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
 */
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
/*
 * 사용자가 잠겨 있는지 여부를나타냄.
 * (non-Javadoc)
 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
 */
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
/*
 * 사용자의 자격 증명(암호)가 만료되었는지 여부를 나타냄.
 * (non-Javadoc)
 * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
 */
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
/*
 * 사용자의 사용 가능 여부를 나타냄.
 * (non-Javadoc)
 * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
 */
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
	

	public String getORIGINALIMAGEURL() {
		return ORIGINALIMAGEURL;
	}

	public void setORIGINALIMAGEURL(String oRIGINALIMAGEURL) {
		ORIGINALIMAGEURL = oRIGINALIMAGEURL;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMnumber() {
		return Mnumber;
	}

	public void setMnumber(String mnumber) {
		Mnumber = mnumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
	
	
	

}
