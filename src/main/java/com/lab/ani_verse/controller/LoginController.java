package com.lab.ani_verse.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lab.ani_verse.mapper.MemberMapper;
import com.lab.ani_verse.vo.MemberVO;

import lombok.extern.slf4j.Slf4j;
 import org.springframework.web.bind.annotation.RequestParam;
 
 

@Slf4j
@RestController
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
		// authenticated 사용자만 접근 가능
		@GetMapping("/test")
		public String login() {
			
			String name = "name";
			
			return name + "완료";
		}
		@GetMapping("/tests")
		public ResponseEntity<Map<String, Object>> test() {
		    Map<String, Object> res = new HashMap<>();
		    String name = "name";
		    res.put("message", name + "ok");
		    return ResponseEntity.ok(res);
		}

}
