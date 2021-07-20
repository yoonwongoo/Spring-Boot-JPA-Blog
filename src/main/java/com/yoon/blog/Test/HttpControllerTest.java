package com.yoon.blog.Test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//사용자가 요청 -> 응답(HTML파일)
//사용자가 요청 -> 응답(data형식) @RestController

@RestController
public class HttpControllerTest {

	
	//http://localhost:8090/http/get
	@GetMapping("/http/get")
	public String getTest(member m) {
	
		return "get"+m.getId();
	}
	@PostMapping("http/post")
	public String postTest(@RequestBody member m) {
		
		return "post"+m.getId()+", "+m.getPassword()+", "+m.getUsername()+", "+m.getEmail();
	}
	@PutMapping("http/put")
	public String putTest() {
		
		return "put";
	}
	@DeleteMapping("http/delete")
	public String deleteTest() {
		
		return "delete";
	}
}
