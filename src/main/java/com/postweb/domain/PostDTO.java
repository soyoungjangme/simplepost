package com.postweb.domain;

import java.sql.Date;
import java.time.LocalDateTime;

public class PostDTO {
	private Long postNo; //게시물번호
	private String postTitle; //게시물 제목
	private String postContent; //게시물 내용
	private LocalDateTime postRegDate; //게시물 등록일
	private Long postHit; //게시물 조회수
	
	//기본생성자
	public PostDTO() {}
	
	//생성자
	public PostDTO(Long postNo, String postTitle, String postContent, LocalDateTime postRegDate, Long postHit) {
		super();
		this.postNo = postNo;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.postRegDate = postRegDate;
		this.postHit = postHit;
	}
	
	//getter, setter
	public Long getPostNo() {
		return postNo;
	}

	public void setPostNo(Long postNo) {
		this.postNo = postNo;
	}
	
	public String getPostTitle() {
		return postTitle;
	}
	
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	
	public String getPostContent() {
		return postContent;
	}
	
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	
	public LocalDateTime getPostRegDate() {
		return postRegDate;
	}
	
	
	public void setPostRegDate(LocalDateTime postRegDate) {
		this.postRegDate = postRegDate;
	}
	
	public Long getPostHit() {
		return postHit;
	}
	
	public void setPostHit(Long postHit) {
		this.postHit = postHit;
	}

	@Override
	public String toString() {
		return "PostDTO [postNo=" + postNo + ", postTitle=" + postTitle + ", postContent=" + postContent
				+ ", postRegDate=" + postRegDate + ", postHit=" + postHit + "]";
	}
	
	
	
}
