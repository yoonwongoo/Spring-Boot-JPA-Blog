package com.yoon.blog.config.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yoon.blog.model.User;
import com.yoon.blog.repository.UserRepository;
//user오브젝트를 만든것을 사용할 수 없어서 userDetail을 상속받아서 타입을 맞춰준다.
@Service
public class PrincipalDetailService implements UserDetailsService{

	//로그인부분.
	//스프링이 로그인을 요청을 할 때 username, password 변수를 가로채는데,
	//password부분은 알아서 하고,
	//username만 DB에 있는지 확인을 해주면 됨.
	
	@Autowired  
	private UserRepository UserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User principal = UserRepository.findByUsername(username)
			 .orElseThrow(()->{
				 
				 return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다.");
				 
			 });
		return new PrincipalDetail(principal);//시큐리티 세션에 유저정보가 이때 저정된다. userdetails 타입으로 저장된다.
	}

}
