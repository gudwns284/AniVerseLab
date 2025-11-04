package com.lab.ani_verse.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lab.ani_verse.vo.MemberVO;

@Mapper
public interface MemberMapper {
	// ID 등록
	public int insertMember(MemberVO memberVO);
	// ID 권한 등록
	public int insertAuth(@Param("memberId") String memberId, @Param("authName") String authName);
	// ID 조회
	public MemberVO read(String memberId);
}
