package com.lab.ani_verse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration // 너님 객체 다 빈으로 설정 할 거임 아래 빈으로 세팅해줘야함
public class redisConfig {
	
	@Value("${spring.redis.host}")
	private String host;
	
	@Value("${spring.redis.port}")
	private int port;
	
	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		return new LettuceConnectionFactory(host, port);
	}
	
	/*
	 Spring boot 2.0 부터는 RedisTemplate와 StringTemplate를 자동생성 해줘서 따로 빈으로 등록하지 않아도 된다.
	 RedisTemplate, StringTemplate 가 뭔데 그래서 말 그대로 템플릿 안에 그려주는 느낌인건가?
	 */
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		
		// 일반적인 key:value의 경우 시리얼라이저
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		
		// Hash를 사용할 경우 시리얼라이저
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		
		// 모든 경우
		redisTemplate.setDefaultSerializer(new StringRedisSerializer());
		
		return redisTemplate;
	}
	
}
