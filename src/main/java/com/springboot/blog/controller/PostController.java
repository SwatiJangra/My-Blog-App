package com.springboot.blog.controller;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.dto.PostResponseDto;
import com.springboot.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // create blog post
    // http://localhost:8080/api/posts/create-post
    @PostMapping("/create-post")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    // get all posts rest api
    // http://localhost:8080/api/posts/get-all-posts
//    @GetMapping("/get-all-posts")
//    public List<PostDto> getAllPosts() {
//        return postService.getAllPosts();
//    }

    // By Using Pagination and Sorting
//    @GetMapping("/get-all-posts")
//    public List<PostDto> getAllPosts(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
//                                     @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
//        return postService.getAllPosts(pageNo, pageSize);
//    }
    // By using Response DTO

    //http://localhost:8080/api/posts/get-all-posts?pageNo=0&pageSize=10&sortBy=title&sortDir=asc
    @GetMapping("/get-all-posts")
    public PostResponseDto getAllPosts(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                       @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
                                       @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy) {
        return postService.getAllPosts(pageNo, pageSize, sortBy);
    }

    // get post by its id
    // http://localhost:8080/api/posts/get-post-by-id
    @GetMapping("/get-post-by-id/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    // update post rest api
    // http://localhost:8080/api/posts/update-post-by-id/{id}
    @PutMapping("/update-post-by-id/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable("id") long id) {
        PostDto postResponse = postService.updatePost(postDto, id);
        return  new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    // delete post rest api
    // http://localhost:8080/api/posts/delete-post-by-id/{id}
    @DeleteMapping("/delete-post-by-id/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable("id") long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>("Post entity deleted successfully!", HttpStatus.OK);
    }
}
