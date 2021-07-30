package com.yoon.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoon.blog.model.RoleType;
import com.yoon.blog.model.User;
import com.yoon.blog.repository.UserRepository;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	@Transactional
	public void 회원가입(User user) {
		userRepository.save(user);
	}//이 전체가 성공을 하면 커밋 아님 롤백인데 아직 롤백은 구현안함.
	
}
