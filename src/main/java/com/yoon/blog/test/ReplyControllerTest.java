package com.yoon.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.yoon.blog.model.Board;
import com.yoon.blog.repository.BoardRepository;

@RestController
public class ReplyControllerTest {
	
	
	
	@Autowired
	private BoardRepository boardRepository;
	//무한참조확인. reply에서 현재 board 오브젝트를 참조하기때문에 무한참조현상 발생.
	@GetMapping("/test/board/{id}")
	public Board getBoard(@PathVariable int id) {
		
		
		return boardRepository.findById(id).get();
	}
}
