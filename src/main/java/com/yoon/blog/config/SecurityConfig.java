package com.yoon.blog.config;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.yoon.blog.config.auth.PrincipalDetailService;

@Configuration
@EnableWebSecurity // 컨트롤러로 가기전에 필터링을 해준다. 즉 시큐리티 필터등록
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리체크하겠다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PrincipalDetailService principalDetailService;
	
	
	@Bean 
	public BCryptPasswordEncoder encodePWD() {   
		
		return new BCryptPasswordEncoder();
		
	}
	
	
	//시큐리티가 대신 로그인을 해준다 하지만 그떄 패스워드를 가로채기를 함. 
	//해당 패스워드가 무엇으로 해쉬가 되어서 회원가입이 되었는지 알아야 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수있음.
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
		 
	}
	
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		
		  http
		  	.csrf().disable()//csrf토큰 비활성화 (테스트 시 걸어두면 좋음.) 시큐리티가 토큰없으면 자동으로 요청 막아버림.
		  	.authorizeRequests() //인가에 대한 요청이 들어오면.
		  	 .antMatchers("/","/auth/**","/js/**","/css/**","/image/**","/dummy/**") //이페이지가 아닌 모든 페이지의 요청은 "/auth/loginForm"으로 이동,
		  	 .permitAll() 
		  	 .anyRequest()
		  	 .authenticated()
		  	.and()
		  	 .formLogin()
		  	 .loginPage("/auth/loginForm")
		  	 .loginProcessingUrl("/auth/loginProc")//시큐리티가 해당 주소로 요청이 오는 로그인을 가로챔. 대신 로그인 해줌.
		  	 .defaultSuccessUrl("/"); 
		  	 //.failureUrl(null)
		 
	}

}
