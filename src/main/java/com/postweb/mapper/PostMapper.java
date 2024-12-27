package com.postweb.mapper;

import java.util.List;

import com.postweb.domain.PostDTO;

public interface PostMapper {
	public void registPost(PostDTO postDTO);
	public List<PostDTO> getAllPosts();
	public PostDTO getPostDetail(Long postNo);
	public int deletePost(Long postNo);
	public void updatePost(PostDTO postDTO);
}
