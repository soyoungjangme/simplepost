package com.postweb.service;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.postweb.domain.PostDTO;
import com.postweb.mapper.PostMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.mybatis.MybatisUtil;

public class PostServiceImpl implements PostService{
	
    private final SqlSessionFactory sqlSessionFactory = MybatisUtil.getSqlSessionFactory(); // 주의: 메소드명 변경

    //게시글 등록하기
	@Override
	public void registPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String postTitle = request.getParameter("post_title");
		String postContent = request.getParameter("post_content");
		
		System.out.println("글제목: " + postTitle);
		System.out.println("내용: " + postContent);
		
		PostDTO dto = new PostDTO();
		dto.setPostTitle(postTitle);
		dto.setPostContent(postContent);
		
		try (SqlSession sql = sqlSessionFactory.openSession()){ 
			PostMapper mapper = sql.getMapper(PostMapper.class);
			mapper.registPost(dto);
			System.out.println("생성된 게시글번호: " + dto.getPostNo());
			
			response.sendRedirect("postList.post");
			sql.commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("게시글 등록 중 error");
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
			response.sendRedirect(request.getContextPath() + "/errorPage.jsp");
			
	        return null;  
		}
	}


	//게시글 상세보기
	@Override
	public PostDTO getPostDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String postNoParam = request.getParameter("postNo");

		try (SqlSession sql = sqlSessionFactory.openSession()){
			PostMapper mapper = sql.getMapper(PostMapper.class);

			Long postNo = Long.parseLong(postNoParam);
			PostDTO post = mapper.getPostDetail(postNo);
			System.out.println("상세내용: " + post.toString());
			
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

		String postNoParam = request.getParameter("postNo");
		
		try (SqlSession sql = sqlSessionFactory.openSession()){
			PostMapper mapper = sql.getMapper(PostMapper.class);
			
			Long postNo = Long.parseLong(postNoParam);
			int result = mapper.deletePost(postNo);
			sql.commit();
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("게시물 삭제 중 error");
			response.sendRedirect(request.getContextPath() + "/errorPage.jsp");
			
			return 0;
		}
	}



}
