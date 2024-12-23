package com.postweb.service;

import java.io.IOException;

import org.apache.ibatis.session.SqlSessionFactory;

import com.postweb.util.MybatisUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PostServiceImpl implements PostService{
	
	private final SqlSessionFactory sqlSessionFactory = MybatisUtil.getsqlSessionFactory();

	@Override
	public void registPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String postTitle = request.getParameter("post_title");
		String postContent = request.getParameter("post_content");
		
		System.out.println("글제목: " + postTitle);
		System.out.println("내용: " + postContent);
	}


}
