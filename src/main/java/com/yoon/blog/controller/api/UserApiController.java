package com.yoon.blog.controller.api;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.yoon.blog.dto.ResponseDto;

import com.yoon.blog.model.User;
import com.yoon.blog.service.UserService;


@RestController//데이터전용
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {//username password email 인데 role은 강제로 넣어주자.
		
		
		userService.회원가입(user);
		System.out.println("save함수");
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);//json오브젝트를 json으로 리턴해줌.
	}
	
	/* 로그인 메서드
	 * @PostMapping("/api/user/login") public ResponseDto<Integer>
	 * login(@RequestBody User user, HttpSession session){
	 * 
	 * System.out.println("login함수호출"); User principal = userService.로그인(user);
	 * 
	 * if(principal != null) { session.setAttribute("principal",
	 * principal);//key,value
	 * 
	 * }
	 * return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	 * 
	 * }
	 */
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user){
		
		userService.회원수정(user);
		//트랜잭션이 완료는 되었지만, 세션은 변경되지않음. 직접 세션값을 변경을 해준다. 
		//세션등록 
		//로그인처리
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);// 홀더에서 context에 입장해서
		//컨트롤러단에서 해줘야 서비스단에서 트랜잭션이 종료되며 db에 반영이 된다. 바뀐 정보로 세션을 다시 만들어서 유지해준다.
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id){
	
		
		userService.회원삭제(id);
		//SecurityContextHolder.clearContext();
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
		
	}
}
