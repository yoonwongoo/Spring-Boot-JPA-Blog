package com.yoon.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
//import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder//빌더패턴
@Entity //@Entity 사용시 mysql에서 자동으로 테이블생성해줌.
//@DynamicInsert//insert할 경우에 null인필드를 제외하고 쿼리문을 실행하기위해 사용할 수도 있다.
public class User {

	@Id// pk가 된다.
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;//넘버링을 위해서 오라클기준 sequence, mysql기준 auto_increment
	
	
	@Column(nullable = false, length=100, unique=true)
	private String username; //아이디
	
	@Column(nullable = false, length=300)//해쉬비밀번호를 하기 위해 널널히 설정.
	private String password;//비밀번호

	@Column(nullable = false, length=50)
	private String email;//이메일
	
	//@ColumnDefault("'USER'")//default사용하지말고 그냥 넣어주자.
	@Enumerated(EnumType.STRING)//db에는 타입이 RoleType이 없기때문에 String을 해준다.
	private RoleType role; //enum을 사용해서하자..여기서는 일단 관리자 일반사용자의 구별.기본으로 일반사용자로 등록을해놓자.
	
	@Column
	private String oauth;// kakao
	
	
	@CreationTimestamp //자동으로 시간생성.오라클의 sysdate와 동일.
	private Timestamp createDate;//생성일
}
