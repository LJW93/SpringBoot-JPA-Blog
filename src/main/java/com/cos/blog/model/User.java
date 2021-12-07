package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // User 클래스가 자동으로 MySQL에 테이블이 생성이 되게 함
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@DynamicInsert // insert 시에 null 필드를 제외시켜준다
public class User {
	
	@Id // PrimaryKey
	@GeneratedValue(strategy=GenerationType.IDENTITY)// 프로젝트에 연결된 DB의 넘버링 전략을 따라간다
	private int id; // 시퀀스, auto_increment
	
	@Column(nullable=false,length=30)
	private String username; // 아이디
	
	@Column(nullable=false,length=100) // 해쉬로 변경하기 위해 넉넉하게 잡음
	private String password; // 패스워드
	
	@Column(nullable=false,length=50)
	private String email; // 메일
	
	//@ColumnDefault("user")
	//DB는 RoleType이라는게 없다. 
	@Enumerated(EnumType.STRING)
	private RoleType role; // Eunm을 쓰는게 좋다.
	
	@CreationTimestamp // 시간이 자동으로 입력
	private Timestamp createDate;
	

}
