package com.yoon.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yoon.blog.model.User;

/*
 * DAO
 * @Repository생략가능.
 * 자동으로 빈등록.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
	/*
	 * //jpa naming 전략 쿼리 //SELECT * FROM user WHERE username =? AND password=?;
	 * User findByUsernameAndPassword(String username, String password);
	 * 
	 * 
	 * 
	 * @Query(value="SELECT * FROM user WHERE username = ?1 AND password =2?",
	 * nativeQuery = true) User login(String username, String password);
	 * 
	 */}
