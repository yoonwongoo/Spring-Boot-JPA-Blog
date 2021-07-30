package com.yoon.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yoon.blog.dto.ResponseDto;
import com.yoon.blog.model.RoleType;
import com.yoon.blog.model.User;
import com.yoon.blog.repository.UserRepository;
import com.yoon.blog.service.UserService;

@RestController//데이터전용
public class UserApiController {
	
	@Autowired
	private UserService userService;
	 
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) {//username password email 인데 role은 강제로 넣어주자.
		
		user.setRole(RoleType.USER);
		userService.회원가입(user);
		
		System.out.println("save함수");
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);//json오브젝트를 json으로 리턴해줌.
	}

}
