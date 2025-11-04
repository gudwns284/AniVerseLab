package com.lab.ani_verse.jwt.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.lab.ani_verse.vo.MemberVO;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class CustomUser extends User{
	
	private MemberVO memberVO;
	
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);	
	}
	
	public CustomUser(MemberVO memberVO) {
		super(
				memberVO.getMemberId(),
				memberVO.getMemberPw(), 
				memberVO.getAuthList().stream().map(
						authName -> new SimpleGrantedAuthority(authName)
						).collect(Collectors.toList()));
		this.memberVO = memberVO;
	}

	
}
