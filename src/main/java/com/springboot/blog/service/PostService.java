package com.springboot.blog.service;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.dto.PostResponseDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

//    List<PostDto> getAllPosts();
//    List<PostDto> getAllPosts(int pageNo, int pageSize);
    PostResponseDto getAllPosts(int pageNo, int pageSize, String sortBy);
    PostDto getPostById(long id);
    PostDto updatePost(PostDto postDto, long id);
    void deletePostById(long id);
}
