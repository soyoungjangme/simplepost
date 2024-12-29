package com.postweb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.postweb.constants.UrlPaths;
import com.postweb.domain.PostDTO;
import com.postweb.domain.UserDTO;
import com.postweb.service.PostService;
import com.postweb.service.PostServiceImpl;
import com.postweb.service.UserService;
import com.postweb.service.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("*.post")
public class PostController extends HttpServlet {
	
	private final PostService postService = new PostServiceImpl();
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
		
		getUserInfo(req, resp);
		
		switch(command) {
			case UrlPaths.POST_LIST:
				getPostList(req, resp);
	            break;
	            
			case UrlPaths.POST_DETAIL:
				getPostDetail(req, resp);
				break;
				
			case UrlPaths.POST_REGIST:
	            req.getRequestDispatcher("postRegist.jsp").forward(req, resp);
	            break;
	            
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String command = parseCommand(req);
		
		switch(command) {
			case UrlPaths.POST_REGIST_FORM:
	            postService.registPost(req, resp);
	            break;
	            
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found");
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String command = parseCommand(req);
	    resp.setContentType("application/json");
	    resp.setCharacterEncoding("UTF-8");

	    try (PrintWriter out = resp.getWriter()) {
	        switch (command) {
	            case UrlPaths.POST_DELETE:
	                int result = postService.deletePost(req, resp);
	                if (result > 0) {
	                    out.write("{\"success\": true, \"message\": \"삭제 성공\"}");
	                } else {
	                    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	                    out.write("{\"success\": false, \"message\": \"삭제 실패\"}");
	                }
	                break;
	            default:
	                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "{\"success\": false, \"message\": \"Page not found\"}");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        try (PrintWriter out = resp.getWriter()) {
	            out.write("{\"success\": false, \"message\": \"서버 내부 오류 발생\"}");
	        }
	    }
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String command = parseCommand(req);
		
		switch(command) {
			case UrlPaths.POST_UPDATE:
				postService.updatePost(req, resp);
				break;
			
			default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found");
		}
	}

	// 게시글 목록 가져오기
	public void getPostList( HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            resp.sendRedirect(req.getContextPath() + "/errorPage.jsp");
        }
	}
	
	//게시글 상세 보기
	public void getPostDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
	        PostDTO postDetail = postService.getPostDetail(req, resp);

	        if (postDetail == null) {
	            if (!resp.isCommitted()) {
	                resp.sendRedirect(req.getContextPath() + "/errorPage.jsp");
	            }
	            return;
	        }

	        // JSON 응답이 이미 처리된 경우 메서드 종료
	        if (req.getHeader("Accept") != null && req.getHeader("Accept").contains("application/json")) {
	            return;
	        }

	        // HTML 응답 처리
	        LocalDateTime postRegDate = postDetail.getPostRegDate();
	        if (postRegDate != null) {
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	            String formattedDate = postRegDate.format(formatter);
	            req.setAttribute("formattedDate", formattedDate);
	        }

	        req.getRequestDispatcher("postDetail.jsp").forward(req, resp);
	    } catch (Exception e) {
	        e.printStackTrace();
	        if (!resp.isCommitted()) {
                resp.sendRedirect(req.getContextPath()  + "/errorPage.jsp");
	        }
	    }
	}
	
	public void getUserInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// session가져오기
		Long userNo = (Long) req.getSession().getAttribute("userNo");
		
		if(userNo != null) {
			UserDTO user = userService.getUserInfo(req, resp);
			if(user != null) {
				req.setAttribute("user", user);
			}
		} 
		
	}

	
	
}
