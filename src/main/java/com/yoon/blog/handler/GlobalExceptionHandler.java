package com.yoon.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;



@ControllerAdvice//모든 예외가 여기 클래스로 들어옴.
@RestController
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(value=IllegalArgumentException.class)//IllegalAgumentException예외인 경우에.
	public String handleArgumentException(IllegalArgumentException e) {
		
		return"<h1>"+e.getMessage()+"<h1>";
		
	}

}
