package com.yoon.blog.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import com.yoon.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{

	@Modifying//update,delete, insert 시 작성.
	@Query(value="INSERT INTO reply(userId, boardId, content, createDate)VALUES(?1, ?2, ?3, now())" , nativeQuery = true)
	int mSave(int userId, int boardId, String content);
	 //resultType을 int로줌. 업데이트된 행의 개수 를 리턴 1이면 한 개 세이브, 0이면 하나도 안됨. -1이면 오류.
}
