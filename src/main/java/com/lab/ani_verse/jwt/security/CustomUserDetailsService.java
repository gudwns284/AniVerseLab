package com.lab.ani_verse.jwt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.lab.ani_verse.mapper.MemberMapper;
import com.lab.ani_verse.vo.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.debug("유저 확인 {}", username);
		
		MemberVO memberVO = memberMapper.read(username);
		
		if(memberVO != null) {
			return new CustomUser(memberVO);
		} else {
			throw new UsernameNotFoundException(username);
		}
		
	}
	
}
