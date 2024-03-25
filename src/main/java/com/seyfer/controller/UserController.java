package com.seyfer.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seyfer.models.User;
import com.seyfer.repository.UserRepository;
import com.seyfer.service.UserService;




@RestController
public class UserController {
	
	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;
	@GetMapping("/users")
	public List<User> getUsers() {
	   List<User> users= userRepository.findAll();
	   return users;
	}
	
	@GetMapping("/users/{userid}")
	public User getUserById(@PathVariable("userid") Integer id) throws Exception{
		User user= userService.findUserById(id);
		return user;
	}
	
	@PostMapping("/users/add")
	public User createUser(@RequestBody User user) {
		
		User savedUser = userService.registerUser(user);
		return savedUser;
	}
	
	@PutMapping("/users/update/{userId}")
	public User updateUser(@RequestBody User user,@PathVariable Integer userId) throws Exception {
		
        User updateduser= userService.updateuser(user,userId);
        return updateduser;
        
	}
    
	@PutMapping("/users/follow/{userid1}/{userid2}")
	public User followUserHandler(@PathVariable Integer userid1, @PathVariable Integer userid2) throws Exception{
	   User user = userService.followUser(userid1, userid2);
	   return user;
	}
	
	@GetMapping("/users/search")
	public List<User> searchUser(@RequestParam("query") String query){
		List<User> users = userService.searchUser(query);
		return users;
	}
	
 
}
