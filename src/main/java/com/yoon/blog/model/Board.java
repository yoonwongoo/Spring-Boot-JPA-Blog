package com.yoon.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder//빌더패턴
@Entity
public class Board {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)//auto_increament
	@Id
	private int id;

	@Column(nullable =false, length =100)
	private String title;
	
	@Lob//대용량 데이터
	private String content;//섬머노트 라이브러리 사용.
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userId")
	private User user;//DB는 오브젝트를 저장할 수 없다. 하지만 자바는 오브젝트를 저장할 수 있다.
	
	//fetch=FetchType.LAZY는 필요할때 떙겨온다.
	@OneToMany(mappedBy = "board", fetch=FetchType.EAGER)//mappedBy 연관관계의 주인이 아니다. DB의 컬럼을 만들지마세요.
	@JsonIgnoreProperties({"board"})// Reply 테이블에서 조인되어있는 board오브젝트를 호출하지않는다. 무한참조방지. 즉. getter호출방지.
	@OrderBy("id desc")
	private List<Reply> replys;
	
	private int count;//조회수
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
