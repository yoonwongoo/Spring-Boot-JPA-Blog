package com.yoon.blog.Test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


//@Getter @Setter
@Data
//@AllArgsConstructor//생성자
@NoArgsConstructor//그냥 기본 생성자.
public class member {
 
	private int id;
	private String username;
	private String password;
	private String email;
	
	
	@Builder
	public member(int id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	

}
