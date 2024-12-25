package com.postweb.controller;

import java.io.IOException;
import java.util.List;

import com.postweb.constants.UrlPaths;
import com.postweb.domain.PostDTO;
import com.postweb.service.PostService;
import com.postweb.service.PostServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("*.post")
public class PostController extends HttpServlet {
	
	private final PostService postService = new PostServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doAction(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doAction(req, resp);
	}
	
	protected void doAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8"); //클라이언트에서 보낸 요청 인코딩 방식을 UTF-8로 하겠다.
		
		String uri = req.getRequestURI(); //요청된 URI
		String path = req.getContextPath(); //프로젝트 식별이름 ex) simplepost
		String command = uri.substring(path.length()); //uri에서 프로젝트 내 경로 ex) simplepost/아래에 있는 경로
		
		System.out.println("경로(command): " + command);
		
		
		switch(command) {
			case UrlPaths.POST_LIST:
				// 서비스 계층에서 데이터 가져오기
	            List<PostDTO> postList = postService.getAllPosts(req, resp);
	            
	            if (postList != null) {
	                // JSP에 데이터 전달
	                req.setAttribute("postList", postList);
	                req.getRequestDispatcher("postList.jsp").forward(req, resp);
	            } else {
	                // 오류 페이지로 리다이렉트
	                resp.sendRedirect(req.getContextPath() + "/errorPage.jsp");
	            }
	            break;
			case UrlPaths.POST_REGIST:
	            req.getRequestDispatcher("postRegist.jsp").forward(req, resp);
	            break;
			case UrlPaths.POST_REGIST_FORM:
	            postService.registPost(req, resp);
	            break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found");
		}
		
	}
	
	
}
