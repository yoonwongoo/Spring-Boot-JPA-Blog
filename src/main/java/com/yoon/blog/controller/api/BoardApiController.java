package com.yoon.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yoon.blog.dto.ResponseDto;
import com.yoon.blog.model.Board;
import com.yoon.blog.service.BoardService;

@RestController
public class BoardApiController {

	@Autowired
	private BoardService BoardService; 
	
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board){
		
		
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1); 
	}
	
}
