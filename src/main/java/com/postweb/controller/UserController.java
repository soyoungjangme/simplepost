package com.postweb.controller;

import java.io.IOException;

import com.postweb.constants.UrlPaths;
import com.postweb.service.UserService;
import com.postweb.service.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("*.user")
public class UserController extends HttpServlet {

	private final UserService userService = new UserServiceImpl();
	
	public String parseCommand(HttpServletRequest req) {
		
		String uri = req.getRequestURI(); //요청된 URI
		String path = req.getContextPath(); //프로젝트 식별이름 ex) simplepost
		String command = uri.substring(path.length()); //uri에서 프로젝트 내 경로 ex) simplepost/아래에 있는 경로
		
		System.out.println("경로(command): " + command);
		
		return command;
	}
	
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String command = parseCommand(req);
		
		switch(command) {
			case UrlPaths.USER_SIGNUP:
	            req.getRequestDispatcher("userSignUp.jsp").forward(req, resp);
	            break;
			case UrlPaths.USER_LOGIN:
	            req.getRequestDispatcher("userLogin.jsp").forward(req, resp);
	            break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found");
		}
	}



	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String command = parseCommand(req);
		
		switch(command) {			
			case UrlPaths.USER_SIGNUP_FORM:
				userService.userSignUp(req, resp);
				break;
			case UrlPaths.USER_LOGIN_FORM:
				userService.checkLogin(req, resp);
				break;
			case "/user/duplicatedIdCheckForm.user":
				int checkCnt = userService.duplicatedId(req, resp);
	            resp.setContentType("text/plain"); // 간단한 정수값 반환
	            resp.getWriter().write(String.valueOf(checkCnt)); // 정수값 반환
	            break;
			default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found");
		}
		
	}
	
	
	
}
