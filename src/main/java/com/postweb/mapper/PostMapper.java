package com.postweb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.postweb.domain.PostDTO;

public interface PostMapper {
	public void registPost(PostDTO postDTO);
	public List<PostDTO> getAllPosts();
	public PostDTO getPostDetail(Long postNo);
	public int deletePost(@Param("postNo") Long postNo, @Param("userNo") Long UserNo);
	public void updatePost(PostDTO postDTO);
}
