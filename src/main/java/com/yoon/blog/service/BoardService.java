package com.yoon.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoon.blog.dto.ReplySaveRequestDto;
import com.yoon.blog.model.Board;

import com.yoon.blog.model.User;
import com.yoon.blog.repository.BoardRepository;
import com.yoon.blog.repository.ReplyRepository;




@Service
public class BoardService {
	
	

	@Autowired
	private BoardRepository boardRepository;
	

	@Autowired
	private ReplyRepository replyRepository;
	
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
	
	
	
	@Transactional
	public void 댓글쓰기(ReplySaveRequestDto replyDto) {
		
		
		/*
		 * User user = userRepository.findById(replyDto.getUserId()).orElseThrow(() -> {
		 * 
		 * return new IllegalArgumentException("찾기 실패");
		 * 
		 * });
		 * 
		 * Board board = boardRepository.findById(replyDto.getBoardId()).orElseThrow(()
		 * -> {
		 * 
		 * return new IllegalArgumentException("찾기 실패");
		 * 
		 * }); Reply reply = new Reply(); reply.update(user, board,
		 * replyDto.getContent());
		 */
		replyRepository.mSave(replyDto.getUserId(), replyDto.getBoardId(), replyDto.getContent());
	}
	
	
	
	
	@Transactional
	public void 댓글삭제(int replyId) {
	
		replyRepository.deleteById(replyId);
		
	}
}
