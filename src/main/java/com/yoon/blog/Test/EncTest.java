package com.yoon.blog.Test;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class EncTest {
		
	//회원가입 비밀번호 해쉬 테스트 
	@Test
	public void hashEncode() {
		String a = new BCryptPasswordEncoder().encode("1234");
		System.out.println(a);
		
	}
}
