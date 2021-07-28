package com.yoon.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yoon.blog.model.User;

/*
 * DAO
 * @Repository생략가능.
 * 자동으로 빈등록.
 * */
public interface UserRepository extends JpaRepository<User, Integer>{

}
