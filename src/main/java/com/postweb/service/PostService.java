package com.postweb.service;

import java.io.IOException;
import java.util.List;

import com.postweb.domain.PostDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface PostService {
	public void registPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	public List<PostDTO> getAllPosts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	public PostDTO getPostDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}