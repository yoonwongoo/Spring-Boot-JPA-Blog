package com.yoon.blog.controller;

import org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

//인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
//그냥주소가 /이면 index.jsp사용.
@Controller

public class UserController {
	@GetMapping("/auth/joinForm")
	public String joinForm() {

		return "user/joinForm";
	}

	@GetMapping("/auth/loginForm")
	public String loginForm() {

		return "user/loginForm";
	}

	@GetMapping("/user/updateForm")
	public String userUpdateForm() {

		return "user/updateForm";
	}

	@GetMapping("/auth/kakao/callback")
	public @ResponseBody String kakaoCallback(String code) {

		RestTemplate rt = new RestTemplate();
		
		
		
		//헤더 생성.
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		
		//바디 생성.
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

		params.add("grant_type", "authorization_code");
		params.add("client_id", "3a3719faa062fef49a84827ac2f479e3");
		params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
		params.add("code", code);

		//헤더와 바디를 엔터티에 넣어서 변수생성.ResponseEntity가 HttpEntity라는 타입을 받기하기때문에 만들어준 것.
		HttpEntity<MultiValueMap<String, String>> kakaotokenRequest = new HttpEntity<>(params, headers);// 바디와 헤더를 가지는
																										// 엔터티가 됨.

		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token", 
				HttpMethod.POST,
				kakaotokenRequest,
				String.class // String 형태로 응답이 온다
		);

		return "카카오 토큰인증."+response;

	}
}
