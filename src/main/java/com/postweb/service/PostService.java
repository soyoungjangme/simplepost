package com.postweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.postweb.domain.PostCommentDTO;
import com.postweb.domain.PostDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface PostService {
	public void registPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	public List<PostDTO> getAllPosts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	public PostDTO getPostDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	public int deletePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	public void updatePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	public void updateHit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	public void registComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	public ArrayList<PostCommentDTO> getPostComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	public void deletePostComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	public Long getPostWriter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
