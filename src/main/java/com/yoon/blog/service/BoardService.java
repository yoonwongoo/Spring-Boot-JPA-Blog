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
	
	@Transactional
	public void 글수정(int id, Board requestBoard){
		Board board = boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("해당ID 글찾기 실패");
					
				});
		
		board.setContent(requestBoard.getContent());
		board.setTitle(requestBoard.getTitle());
		//해당 함수종료시(서비스종료될 때)트랜잭션 종료시 -> 더티채킹이 일어나면서 자동 db flush 
	}
}
