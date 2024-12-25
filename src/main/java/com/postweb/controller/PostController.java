package com.postweb.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
	            	 // postRegDate를 포맷팅해서 담은 객체 생성
	                List<String> formattedDates = new ArrayList<>();
	                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	                for (PostDTO post : postList) {
	                    LocalDateTime postRegDate = post.getPostRegDate();
	                    if (postRegDate != null) {
	                        String formattedDate = postRegDate.format(formatter);
	                        formattedDates.add(formattedDate);
	                    } else {
	                        formattedDates.add(null);  // 날짜가 없으면 null 처리
	                    }
	                }

	                // 포맷된 날짜 리스트를 request에 저장
	                req.setAttribute("formattedDates", formattedDates);
	                req.setAttribute("postList", postList); // 원본 postList도 전달

	                req.getRequestDispatcher("postList.jsp").forward(req, resp);
	            } else {
	                // 오류 페이지로 리다이렉트
	                resp.sendRedirect("errorPage.jsp");
	            }
	            break;
	            
			case UrlPaths.POST_DETAIL:
				PostDTO postDetail = postService.getPostDetail(req, resp);
				
				if(postDetail != null) {
					LocalDateTime postRegDate = postDetail.getPostRegDate();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = postRegDate.format(formatter);
                    req.setAttribute("formattedDate", formattedDate);  // Add formatted date to request

					req.setAttribute("postDetail", postDetail);
					req.getRequestDispatcher("postDetail.jsp").forward(req, resp);
				} else {
					resp.sendRedirect("errorPage.jsp");
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
