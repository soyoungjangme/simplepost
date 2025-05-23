package com.postweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.postweb.domain.PostCommentDTO;
import com.postweb.domain.PostDTO;
import com.postweb.mapper.PostMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.mybatis.MybatisUtil;

public class PostServiceImpl implements PostService{
	
    private final SqlSessionFactory sqlSessionFactory = MybatisUtil.getSqlSessionFactory();

    //게시글 등록하기
	@Override
	public void registPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//데이터 파싱
		ObjectMapper objectMapper = new ObjectMapper();
		PostDTO postDTO = objectMapper.readValue(request.getInputStream(), PostDTO.class);
		
		HttpSession session = request.getSession();
		Long userNo = (Long)session.getAttribute("userNo");
		
		postDTO.setPostWriterNo(userNo);
		
		System.out.println("글제목: " + postDTO.getPostTitle());
	    System.out.println("내용: " + postDTO.getPostContent());
	    System.out.println("유저번호: " + postDTO.getPostWriterNo());
		
		try (SqlSession sql = sqlSessionFactory.openSession()){ 
			PostMapper mapper = sql.getMapper(PostMapper.class);
			mapper.registPost(postDTO);
			System.out.println("생성된 게시글번호: " + postDTO.getPostNo());
			
			response.sendRedirect("postList.post");
			sql.commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("게시글 등록 중 error");
			response.sendRedirect(request.getContextPath() + "/errorPage.jsp");

			return;
		}
	}

	//게시글 목록보기
	@Override
	public List<PostDTO> getAllPosts(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try (SqlSession sql = sqlSessionFactory.openSession()){
			PostMapper mapper = sql.getMapper(PostMapper.class);
			List<PostDTO> allPosts = mapper.getAllPosts();
			System.out.println("게시글 목록: " + allPosts.toString());
			
			return allPosts;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("전체 게시글 불러오는 중 error");
			if(!response.isCommitted()) {
				response.sendRedirect(request.getContextPath() + "/errorPage.jsp");				
			}
	        return null;  
		}
	}


	//게시글 상세보기
	@Override
	public PostDTO getPostDetail(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    String postNoParam = request.getParameter("postNo");

	    try (SqlSession sql = sqlSessionFactory.openSession()) {
	        PostMapper mapper = sql.getMapper(PostMapper.class);

	        Long postNo = Long.parseLong(postNoParam);
	        PostDTO post = mapper.getPostDetail(postNo);
	        System.out.println("상세내용: " + post.toString());

	        // Accept 헤더를 확인하여 JSON 혹은 HTML 응답을 처리
	        String acceptHeader = request.getHeader("Accept");

	        // JSON 응답 처리
	        if (acceptHeader != null && acceptHeader.contains("application/json")) {
	            ObjectMapper objectMapper = new ObjectMapper();

	            // 필요한 데이터만 추출
	            Map<String, String> responseMap = new HashMap<>();
	            responseMap.put("postTitle", post.getPostTitle());
	            responseMap.put("postContent", post.getPostContent());

	            response.setContentType("application/json; charset=UTF-8");
	            response.getWriter().write(objectMapper.writeValueAsString(responseMap));
	            return post; // JSON 응답 후 메서드 종료
	        }

	        // HTML 응답 처리 (JSP로 데이터 전달)
	        return post;
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("상세 게시글 불러오는 중 error");
	        response.sendRedirect(request.getContextPath() + "/errorPage.jsp");

	        return null;
	    }
	}


	@Override
	public int deletePost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Long userNo = (Long)request.getSession().getAttribute("userNo");
		String postNoParam = request.getParameter("postNo");
		Long postNo = Long.parseLong(postNoParam);
		
		try (SqlSession sql = sqlSessionFactory.openSession()){
			PostMapper mapper = sql.getMapper(PostMapper.class);
			
			int result = mapper.deletePost(postNo,userNo);
			sql.commit();
			return result;	
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("게시물 삭제 중 error");
			response.sendRedirect(request.getContextPath() + "/errorPage.jsp");
			
			return 0;
		}
	}

	//게시물 수정
	@Override
	public void updatePost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// POSTDTO를 JSON에서 읽어오기
		ObjectMapper objectMapper = new ObjectMapper();
		PostDTO postDTO = objectMapper.readValue(request.getInputStream(), PostDTO.class); 
		
		try(SqlSession sql = sqlSessionFactory.openSession()) {
			PostMapper mapper = sql.getMapper(PostMapper.class);
			mapper.updatePost(postDTO);
			
			sql.commit();
		}catch(Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        response.getWriter().write("서버 오류가 발생했습니다.");
	        e.printStackTrace();
		}
	}

	//조회수 
	@Override
	public void updateHit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	    String postNoParam = request.getParameter("postNo");
	    Long userNo = (Long)request.getSession().getAttribute("userNo"); 
	    
		try(SqlSession sql = sqlSessionFactory.openSession()){
			PostMapper mapper = sql.getMapper(PostMapper.class);
			Long postNo = Long.parseLong(postNoParam);
			
			int checkHit = mapper.checkHit(postNo, userNo); //오늘 조회 유무
			
			if(checkHit < 1) { //조회 카운트 없을 때
				mapper.updateHit(postNo); //조회수 +1
				mapper.insertHit(postNo, userNo); //조회 이력 추가
			}
			
			sql.commit();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("조회 관련 서버 error");
			if(!response.isCommitted()) {
				response.sendRedirect(request.getContextPath() + "/errorPage.jsp");				
			}
		}		
	}

	@Override
	public void registComment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ObjectMapper objectMapper = new ObjectMapper();
	    PostCommentDTO dto = objectMapper.readValue(request.getInputStream(), PostCommentDTO.class);

		Long userNo = (Long)request.getSession().getAttribute("userNo");
		dto.setUserNo(userNo);
		
		try(SqlSession sql = sqlSessionFactory.openSession()){
			
			System.out.println("작성된 댓글 " + dto.toString());
			
			PostMapper mapper = sql.getMapper(PostMapper.class);
			
			mapper.registComment(dto);
			
			sql.commit();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("댓글작성 중 서버 error");
			if(!response.isCommitted()) {
				response.sendRedirect(request.getContextPath() + "/errorPage.jsp");				
			}
		}
	}

	// 댓글 가져오기
	@Override
	public ArrayList<PostCommentDTO> getPostComment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String postNoParam = request.getParameter("postNo");
		Long postNo = Long.parseLong(postNoParam);
		
		try(SqlSession sql = sqlSessionFactory.openSession()){
			PostMapper mapper = sql.getMapper(PostMapper.class);
			ArrayList<PostCommentDTO> getComment = mapper.getPostComment(postNo);
			
			return getComment;
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("댓글 호출 중 서버 error");
			if(!response.isCommitted()) {
				response.sendRedirect(request.getContextPath() + "/errorPage.jsp");				
			}
			return null;
		}
	}

	@Override
	public void deletePostComment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		 // JSON 데이터 파싱
		Map<String, Long> requestData = objectMapper.readValue(request.getInputStream(), new TypeReference<Map<String, Long>>() {});
        Long commentNo = requestData.get("commentNo");
        Long commentUserNo = requestData.get("userNo"); //댓글 작성자
        
        Long userNo = (Long)request.getSession().getAttribute("userNo"); // 현재 session 유저

		try(SqlSession sql = sqlSessionFactory.openSession()){
			PostMapper mapper = sql.getMapper(PostMapper.class);
			
			if(userNo == commentUserNo) {
				mapper.deletePostComment(commentNo);	
				sql.commit();
			}else {
				System.out.println("댓글 삭제 권한 없는 유저");
				if(!response.isCommitted()) {
					response.sendRedirect(request.getContextPath() + "/errorPage.jsp");				
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("댓글삭제 중 서버 error");
			if(!response.isCommitted()) {
				response.sendRedirect(request.getContextPath() + "/errorPage.jsp");				
			}
		}
	}

	@Override
	public Long getPostWriter(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String postNoParam = request.getParameter("postNo");
		Long postNo = Long.parseLong(postNoParam);
		
		try(SqlSession sql = sqlSessionFactory.openSession()){
			PostMapper mapper = sql.getMapper(PostMapper.class);
			Long postWriterNo = mapper.getPostWriter(postNo);
			
			return postWriterNo;
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("작성자 정보 호출 중 서버 error");
			if(!response.isCommitted()) {
				response.sendRedirect(request.getContextPath() + "/errorPage.jsp");				
			}
			return null;
		}
	}

}
