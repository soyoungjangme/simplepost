package com.postweb.mapper;

import com.postweb.domain.UserDTO;

public interface UserMapper {
	public void userSignUp(UserDTO userDTO);
	public int duplicatedId(String userId);
	public Long checkLogin(UserDTO userDTO);
	public UserDTO getUserInfo(Long userNo);
}
