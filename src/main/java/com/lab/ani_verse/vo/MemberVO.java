package com.lab.ani_verse.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberVO {
	// ID
	private String memberId;
	// 이름
	private String memberName;
	// 패스워드
	private String memberPw;
	// 만든시간
	private String memberRegDate;
	// 권한
	private List<String> authList;
	
//	public Map<String, Object> getClaims() {
//		//claim 담을 Map
//		
//		Map<String, Object> mapClaim = new HashMap<>();
//		mapClaim.put("memberId", memberId);
//		mapClaim.put("memberName", memberName);
//		mapClaim.put("memberRegDate", memberRegDate);
//		mapClaim.put("authList", authList);
//		
//		return mapClaim;
//	}
}
