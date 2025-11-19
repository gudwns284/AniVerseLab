package com.lab.ani_verse.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lab.ani_verse.service.MailService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping(value="/api/mail")
public class MailController {
	
	private final MailService mailService;
	
	public MailController(MailService mailService) {
		this.mailService = mailService;
	}
	
	@GetMapping("/send")
	public ResponseEntity<String> sendMail(
	 @RequestParam String to,
	 @RequestParam String subject,
	 @RequestParam String content
	 ) {
//		mailService.sendHtmlMail("@naver.com", "제목입니다용", "편지왔습니다요");
		mailService.sendHtmlMail(to, subject, content);
		
		return ResponseEntity.ok("메일 발송 완료");
	}
	
	@GetMapping("/mailCertified")
	public ResponseEntity<String> mailCertified(
			@RequestParam("toEmail") String toEmail
			) throws MessagingException {
//		mailService.sendHtmlMail("@naver.com", "제목입니다용", "편지왔습니다요");
		mailService.mailCertified(toEmail);
		
		return ResponseEntity.ok("인증메일 발송완료");
	}
	
}
