package com.yoon.blog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoon.blog.model.Board;
import com.yoon.blog.model.User;
import com.yoon.blog.repository.BoardRepository;



@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Transactional
	public void 글쓰기(Board board, User user) {
		board.setUser(user);
		board.setCount(0);
		boardRepository.save(board);
	}
	
	
	@Transactional(readOnly = true)
	public Page<Board> 글목록(Pageable pageable){
		
		return boardRepository.findAll(pageable);
		
	}
	
	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글상세보기 실패");
					
				});
	} 
	
	@Transactional
	public void 글삭제(int id) {
		
		boardRepository.deleteById(id);
		
	}
}
