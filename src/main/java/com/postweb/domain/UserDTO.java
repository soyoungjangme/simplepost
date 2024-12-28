package com.postweb.domain;

import java.sql.Date;

public class UserDTO {

	private Long userNo; //유저번호
	private String userId; //아이디
	private String userPw; //비번
	private String userName; //유저이름
	private String userNick; //유저닉네임
	private Date userRegDate; //가입일
	
	//기본생성자
	public UserDTO() {}

	public UserDTO(Long userNo, String userId, String userPw, String userName, String userNick, Date userRegDate) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userNick = userNick;
		this.userRegDate = userRegDate;
	}

	public Long getUserNo() {
		return userNo;
	}

	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public Date getUserRegDate() {
		return userRegDate;
	}

	public void setUserRegDate(Date userRegDate) {
		this.userRegDate = userRegDate;
	}

	@Override
	public String toString() {
		return "UserDTO [userNo=" + userNo + ", userId=" + userId + ", userPw=" + userPw + ", userName=" + userName
				+ ", userNick=" + userNick + ", userRegDate=" + userRegDate + "]";
	}
	
}
