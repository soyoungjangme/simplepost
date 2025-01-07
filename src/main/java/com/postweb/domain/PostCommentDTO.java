package com.postweb.domain;

import java.time.LocalDateTime;

public class PostCommentDTO {

	private Long commentNo; //댓글번호
	private Long userNo; //작성자
	private Long postNo; //게시물
	private String commentText; //댓글내용
	private LocalDateTime commentDate; //댓글작성일시
	
	public PostCommentDTO() {}

	public PostCommentDTO(Long commentNo, Long userNo, Long postNo, String commentText, LocalDateTime commentDate) {
		super();
		this.commentNo = commentNo;
		this.userNo = userNo;
		this.postNo = postNo;
		this.commentText = commentText;
		this.commentDate = commentDate;
	}

	public Long getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(Long commentNo) {
		this.commentNo = commentNo;
	}

	public Long getUserNo() {
		return userNo;
	}

	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}

	public Long getPostNo() {
		return postNo;
	}

	public void setPostNo(Long postNo) {
		this.postNo = postNo;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public LocalDateTime getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(LocalDateTime commentDate) {
		this.commentDate = commentDate;
	}

	@Override
	public String toString() {
		return "PostCommentDTO [commentNo=" + commentNo + ", userNo=" + userNo + ", postNo=" + postNo + ", commentText="
				+ commentText + ", commentDate=" + commentDate + "]";
	}
	
	
	
}
