package com.yoon.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yoon.blog.config.auth.PrincipalDetail;
import com.yoon.blog.dto.ReplySaveRequestDto;
import com.yoon.blog.dto.ResponseDto;
import com.yoon.blog.model.Board;
import com.yoon.blog.model.Reply;
import com.yoon.blog.service.BoardService;



@RestController
public class BoardApiController {

	@Autowired
	private BoardService boardService;

	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {

		boardService.글쓰기(board, principal.getUser());// 유저오브젝트를 가져온다.

		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id) {

		boardService.글삭제(id);

		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board) {

		boardService.글수정(id, board);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	
	//데이터를 받을때는 dto를 만들자 ->가독성.
	
	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto reply){
		
		
		boardService.댓글쓰기(reply);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	
	@DeleteMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseDto<Integer> replyDelete(@PathVariable int replyId){
		
		boardService.댓글삭제(replyId);
		
		
	return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
}
