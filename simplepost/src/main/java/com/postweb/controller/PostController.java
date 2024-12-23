package com.postweb.controller;

import java.io.IOException;

import com.postweb.service.PostService;
import com.postweb.service.PostServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("*.post")
public class PostController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doAction(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doAction(req, resp);
	}
	
	protected void doAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8"); //클라이언트에서 보낸 요청 인코딩 방식을 UTF-8로 하겠따.
		
		String uri = req.getRequestURI(); //요청된 URI
		String path = req.getContextPath(); //프로젝트 식별이름 ex) simplepost
		String command = uri.substring(path.length()); //uri에서 프로젝트 내 경로 ex) simplepost/아래에 있는 경로
		
		System.out.println("경로(command): " + command);
		
		//service 선언
		PostService postService;
		
		if(command.equals("/post/postList.post")) { //게시물 목록
			req.getRequestDispatcher("postList.jsp").forward(req, resp);
		}
		if(command.equals("/post/postRegist.post")) { //게시물 작성
			req.getRequestDispatcher("postRegist.jsp").forward(req, resp);
		}
		if(command.equals("/post/postRegistForm.post")) { //게시물 작성 form
			postService = new PostServiceImpl();
			postService.registPost(req, resp);
		}
	}
	
	
}
