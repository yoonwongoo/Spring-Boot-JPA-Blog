package com.yoon.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yoon.blog.model.Board;



@Repository

public interface BoardRepository extends JpaRepository<Board, Integer>{
	
	

}
