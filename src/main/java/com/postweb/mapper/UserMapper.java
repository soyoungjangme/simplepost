package com.postweb.mapper;

import com.postweb.domain.UserDTO;

public interface UserMapper {
	public void userSignUp(UserDTO userDTO);
	public int checkLogin(UserDTO userDTO);
}
