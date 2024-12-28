package com.postweb.service;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {
	public void userSignUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	public void checkLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
