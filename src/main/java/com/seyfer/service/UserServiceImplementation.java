package com.seyfer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seyfer.models.User;
import com.seyfer.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public User registerUser(User user) {
		User newuser=  new User();
		newuser.setFirstName(user.getFirstName());
		newuser.setLastName(user.getLastName());
		newuser.setEmail(user.getEmail());
		newuser.setPassword(user.getPassword());
		newuser.setId(user.getId());
		
		User savedUser = userRepository.save(newuser);
		return savedUser;
	}

	@Override
	public User findUserById(Integer userid) throws Exception {
		// TODO Auto-generated method stub
		Optional<User> wanted= userRepository.findById(userid);
		if(wanted.isPresent())
			return wanted.get();
		throw new Exception("user doesnt exist with userid"+userid);
	}

	@Override
	public User findUserByEmail(String email) {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(email);
		return user;
	}

	@Override
	public User followUser(Integer userid1, Integer userid2) throws Exception {
		User user1= findUserById(userid1);
		User user2= findUserById(userid2);
		
		user1.getFollowings().add(user2.getId());
		user2.getFollowers().add(user1.getId());
		
		userRepository.save(user1);
		userRepository.save(user2);
		
		
		return user1;
	}

	@Override
	public User updateuser(User user,Integer userid) throws Exception {
		// TODO Auto-generated method stub
        Optional<User> user1= userRepository.findById(userid);
        
        if(user1.isEmpty())
        	throw new Exception("user"+userid+"not present to update");
        
        User olduser= user1.get();
		
        if(user.getFirstName()!=null)
           olduser.setFirstName(user.getFirstName());
        if(user.getLastName()!=null)
            olduser.setLastName(user.getLastName());
        if(user.getEmail()!=null)
            olduser.setEmail(user.getEmail());
        User updateduser= userRepository.save(olduser);
        return updateduser;
	}

	@Override
	public List<User> searchUser(String query) {
		
		return userRepository.searchUser(query);
	}

}
