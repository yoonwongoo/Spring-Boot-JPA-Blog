package com.yoon.blog.config.auth;


import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.yoon.blog.model.User;

import lombok.Data;


//시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails타입의 오브젝트를 
//시큐리티의 고유한 세션저장소에 저장을 해준다. 
@Data
public class PrincipalDetail implements UserDetails {
	
	private User user; //콤포지션

	
	
	public  PrincipalDetail(User user) {
		this.user= user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		
	/*collectors.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				// TODO Auto-generated method stub
				return "ROLE_"+user.getRole(); //ROLE_USER ROLE_를 꼭 붙여줘야한다.
			}
		});
	*/
		
		collectors.add(()->{return "ROLE_"+user.getRole();});
		
		return collectors;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
		//계정이 만료되지 않았는지 리턴한다.(true:만료안됨.)
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
		//계정이 잠겨있는지 않았는지 리턴한다.(true:잠기지 않음.)
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
		//비밀번호가 만료되지않았는지 리턴한다.(true:만료되지않음.)
	}

	@Override
	public boolean isEnabled() {
	 
		return true;
		//계정이 활성화(사용가능한지)되었는지 리턴한다. (true:활성화되어있음.)
	}
	
}
