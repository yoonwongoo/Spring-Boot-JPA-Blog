package com.yoon.blog.test;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogTest {

	
	@GetMapping("/test")
	public String Hello() {
	
		return"안녕스트링부트";
		
	}
}
