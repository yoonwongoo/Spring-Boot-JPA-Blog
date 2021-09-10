package com.yoon.blog.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoon.blog.model.KakaoProfile;
import com.yoon.blog.model.OAuthToken;
import com.yoon.blog.model.User;
import com.yoon.blog.service.UserService;

//인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
//그냥주소가 /이면 index.jsp사용.
@Controller

public class UserController {
	
	
    @Value("${yoon.key}")
	private String yoonKey; 
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
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
	
	
	//oauth방식의 카카오로그인을 이해를 위해 컨트롤러단에서 처리함.
	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(String code) {

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
		
		//json데이터를 객체에 담는다. 해당 사용 라이브러리는 gson, jsonSimple, ObjectMapper가 있다.
		
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		
		
		
		try {
			oauthToken= objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {	
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		System.out.println(oauthToken.getAccess_token());


		/* 토큰을 이용한 사용자 정보조회*/
		
		RestTemplate rt2 = new RestTemplate();
		
		
		
		//헤더 생성.
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer "+oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		
		
		//헤더와 바디를 엔터티에 넣어서 변수생성.ResponseEntity가 HttpEntity라는 타입을 받기하기때문에 만들어준 것.
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);// param은 알아서 자동으로 오버로딩
																										

		ResponseEntity<String> response2 = rt2.exchange(
				"https://kapi.kakao.com/v2/user/me", 
				HttpMethod.POST,
				kakaoProfileRequest2,
				String.class // String 형태로 응답이 온다
				
		);
		
		

		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {	
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println(kakaoProfile);
		//User오브젝트 필요한 것들 usernamem, password, email
		System.out.println("카카오id"+ kakaoProfile.getId());
		System.out.println("카카오email"+ kakaoProfile.getKakao_account().getEmail());
		

		System.out.println("카카오 블로그서버username"+kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId());
	
		
		
		
		User kakaoUser = User.builder()
				.username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
				.password(yoonKey)
				.email(kakaoProfile.getKakao_account().getEmail())
				.oauth("kakao")
				.build();
		
		 User originUser = userService.회원찾기(kakaoUser.getUsername());
		
		if(originUser.getUsername() == null) {
			System.out.println("기존회원이 아닙니다.");
			userService.회원가입(kakaoUser);	
			
		}
		System.out.println("자동로그인진행");
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), yoonKey));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		
		//이미 가입된 회원인지 최초의 가입하는 회원인지 분기하는게 필요.
		
		return "redirect:/";
	}
}
