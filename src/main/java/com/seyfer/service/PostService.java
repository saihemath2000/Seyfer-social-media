package com.seyfer.service;

import java.util.List;

import com.seyfer.models.Post;

public interface PostService {
   Post createNewPost(Post post, Integer userId) throws Exception;
   
   String deletePost(Integer postid, Integer userId) throws Exception;
   
   List<Post> findPostByUserId(Integer userId);
   
   Post findPostById(Integer postId) throws Exception;
   
   List<Post> findAllPost();
   
   Post savedPost(Integer postId, Integer userId) throws Exception;
   
   Post likePost(Integer postId,Integer userId) throws Exception;
}
