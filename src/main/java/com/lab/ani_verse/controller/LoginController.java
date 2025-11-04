package com.lab.ani_verse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lab.ani_verse.mapper.MemberMapper;
import com.lab.ani_verse.vo.MemberVO;

import lombok.extern.slf4j.Slf4j;
 

@Slf4j
@RestController
@RequestMapping("/")
public class LoginController {
	
	@Autowired
	private MemberMapper mapper;
	
		// authenticated 사용자만 접근 가능
		@PostMapping("/signup")
		@ResponseBody
		public String signup(@RequestBody MemberVO memberVo) {

			
			mapper.insertMember(memberVo);
			
			 return "완료";
		}
		

}
