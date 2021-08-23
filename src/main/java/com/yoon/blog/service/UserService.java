package com.yoon.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoon.blog.model.RoleType;
import com.yoon.blog.model.User;
import com.yoon.blog.repository.UserRepository;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional
	public void 회원가입(User user) {
		
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);//해쉬처리.
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}//이 전체가 성공을 하면 커밋 아님 롤백인데 아직 롤백은 구현안함.
	
	
	/*
	 * @Transactional(readOnly = true)//셀렉할때 트랙잭션 시작, 서비스 종료시에 트랙잭션 종료 (정합성) public
	 * User 로그인(User user) {
	 * System.out.println(userRepository.findByUsernameAndPassword(user.getUsername(
	 * ), user.getPassword())); return
	 * userRepository.findByUsernameAndPassword(user.getUsername(),
	 * user.getPassword());
	 * 
	 * }
	 */
	
}
