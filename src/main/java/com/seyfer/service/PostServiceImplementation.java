package com.seyfer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seyfer.models.Post;
import com.seyfer.models.User;
import com.seyfer.repository.PostRepository;
import com.seyfer.repository.UserRepository;


@Service
public class PostServiceImplementation implements PostService {
	
	@Autowired
	PostRepository postRepository;

	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	@Override
	public Post createNewPost(Post post, Integer userId) throws Exception {
		
		User user= userService.findUserById(userId);
		Post newpost = new Post();
		newpost.setCaption(post.getCaption());
		newpost.setImage(post.getImage());
//		newpost.setCreatedAt(new );
		newpost.setVideo(post.getVideo());
		newpost.setUser(user);
		return newpost;
	}

	@Override
	public String deletePost(Integer postid, Integer userId) throws Exception {
		Post post = findPostById(postid);
		User user= userService.findUserById(userId);
		if(post.getUser().getId()!=user.getId())
			throw new Exception("we cant delete other user posts");
		postRepository.delete(post);
		return "post deleted successfully";
		
	}

	@Override
	public List<Post> findPostByUserId(Integer userId) {
		
		return postRepository.findPostByUserId(userId);
	}

	@Override
	public Post findPostById(Integer postId) throws Exception {
		Optional<Post> post= postRepository.findById(postId);
		if(post.isEmpty())
			throw new Exception("post not found with id"+postId);
		return post.get();
	}

	@Override
	public List<Post> findAllPost() {
		
		return postRepository.findAll();
	}

	@Override
	public Post savedPost(Integer postId, Integer userId) throws Exception {
		Post post = findPostById(postId);
		User user = userService.findUserById(userId);
		
		if(user.getSavedPost().contains(post))
			user.getSavedPost().remove(post);
		else
			user.getSavedPost().add(post);
		userRepository.save(user);
		return post;
	}

	@Override
	public Post likePost(Integer postId, Integer userId) throws Exception {
		
		Post post = findPostById(postId);
		User user= userService.findUserById(userId);
		
		if(post.getLiked().contains(user))
		   post.getLiked().remove(user);
		else
		   post.getLiked().add(user);
		
		return postRepository.save(post);
	}

}
