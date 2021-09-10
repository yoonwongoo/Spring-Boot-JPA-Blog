package com.yoon.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
		String encPassword = encoder.encode(rawPassword);// 해쉬처리.
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}// 이 전체가 성공을 하면 커밋 아님 롤백인데 아직 롤백은 구현안함.

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
	@Transactional
	public void 회원수정(User user) {
		// 수정시에는 영속성 컨텍스트를 User오브젝트를 영속화시키고 수정을하자.
		// 셀렉을 최초로 해주며 영속성컨텍스트에 남으며 그때부터 영속성컨텍스트를 이용하여서 수정하자 그럼 알아서 더티체킹들어간다.
		User persistance = userRepository.findById(user.getId()).orElseThrow(() -> {
			return new IllegalArgumentException("회원찾기 실패");
		});
		if(persistance.getOauth()==null || persistance.getOauth().equals("")) {
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		persistance.setPassword(encPassword);
		persistance.setEmail(user.getEmail());
		}
		
	
		
	
	}

	@Transactional
	public void 회원삭제(int id) {

		userRepository.deleteById(id);
	}
	
	
	
	@Transactional(readOnly = true)
	public User 회원찾기(String username) {
		
		User user = userRepository.findByUsername(username).orElseGet(()->{
			return new User();//빈객체라도 던지니까 널은 아니다.
		});
		
		
		
		return user; 
		
	}
	
	
	
}
