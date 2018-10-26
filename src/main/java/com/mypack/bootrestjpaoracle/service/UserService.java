package com.mypack.bootrestjpaoracle.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mypack.bootrestjpaoracle.entity.Post;
import com.mypack.bootrestjpaoracle.entity.User;
import com.mypack.bootrestjpaoracle.exception.UserNotFoundException;
import com.mypack.bootrestjpaoracle.repository.PostDao;
import com.mypack.bootrestjpaoracle.repository.UserDao;

@Service
@Transactional
public class UserService {


	@Autowired
	UserDao userDao;
	
	@Autowired
	PostDao postDao;

	public List<User> getAllUsers() {
		return this.userDao.findAll();
	}

	public User addUser(User user) {
		return this.userDao.save(user);
	}

	public Optional<User> findById(int id) {
		return userDao.findById(id);
	}
	public boolean deleteById(int id) {


		if (userDao.findById(id).isPresent()) {
			userDao.deleteById(id);
			return true;
		}else {
			throw new UserNotFoundException("id::"+id);

		}

	}

	public List<Post> findPostById(int id) {
		Optional<User> user =	userDao.findById(id);
		List<Post> post 	= 	user.get().getPost();
		
		if (post.size()>0) {
			return post;
		}else {
			throw new UserNotFoundException("id::"+id);
		}

	}

	public Post addpost(int id,Post post) {
		if (userDao.findById(id).isPresent()) {
			Optional<User> usr = userDao.findById(id);
			post.setUser(usr.get());
			Post savedpost = postDao.save(post);
			return savedpost;
		}else {
			throw new UserNotFoundException("id::"+id);

		}
	}


}