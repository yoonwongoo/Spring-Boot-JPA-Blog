package com.yoon.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.yoon.blog.dto.ResponseDto;



@ControllerAdvice//모든 예외가 여기 클래스로 들어옴.
@RestController
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(value=Exception.class)//IllegalAgumentException예외인 경우에.
	public ResponseDto<String> handleArgumentException(Exception e) {
		 
		return  new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
	
		
	}

}
