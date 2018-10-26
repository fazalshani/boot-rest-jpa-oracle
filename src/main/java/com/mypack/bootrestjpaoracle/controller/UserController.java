package com.mypack.bootrestjpaoracle.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mypack.bootrestjpaoracle.dao.UserDaoService;
import com.mypack.bootrestjpaoracle.entity.Post;
import com.mypack.bootrestjpaoracle.entity.User;
import com.mypack.bootrestjpaoracle.exception.UserNotFoundException;
import com.mypack.bootrestjpaoracle.model.Hello;
import com.mypack.bootrestjpaoracle.model.Users_sample;
import com.mypack.bootrestjpaoracle.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	
    @Autowired
    UserService userService;//for JPA
    
    
    @Autowired
    UserDaoService us;// for normal static
    
   //===============Hardcoded=========================================//
    @GetMapping(path="/allbasic")
	public Hello getallbasicusers() {
		return new Hello("fafaaaaaaa");
	}
    @GetMapping(path="/allbasic/{name}")
	public Hello retrieveAllUsersbasic(@PathVariable String name) {
		return new Hello(name);
	}
    @GetMapping(path="/allbasic/bystatic")
	public List<Users_sample> retrieveAllUsersbystatic() {
		return us.findAll();
	}
	
	 
	 //==================Using JPA + Hibernate=========================================//
	@GetMapping(path="/jpa/users")
	public List<User> retrieveAllUsers() {
    	
    	List<User> localusers = (List<User>)userService.getAllUsers();
    	
		return localusers;
	}
	@GetMapping(path="/jpa/users/{id}")
	public Optional<User> retrieveUser(@PathVariable int id) {
		
		Optional<User> user = userService.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("id::"+id);
		}
		return user;
	}
	
	@DeleteMapping(path="/jpa/users/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id) {
		boolean status=true;
		status = userService.deleteById(id);
		if (status) {
			System.out.println("item succfully deleted");
		}
		return new ResponseEntity<>("Item Deleted",HttpStatus.OK);
	}
	
	@PostMapping("/jpa/users")
	public User createUser(@RequestBody User user) {
		
		User userCreted = userService.addUser(user);

		return userCreted;

	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrieveAllUsers(@PathVariable int id) {
		 List<Post> posts = userService.findPostById(id);
		
		if(posts.isEmpty()) {
			throw new UserNotFoundException("id-" + id);
		}
		
		return posts;
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public Post createPost(@PathVariable int id, @RequestBody Post post) {
		
		Post savedpost = userService.addpost(id,post);

		return savedpost;

	}
	//==============================================================//
    @RequestMapping(value = "/all", method = RequestMethod.GET,
    								produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers() {
    	List<User> localusers = userService.getAllUsers();
    	System.out.println("total users are::"+localusers.size());
        return localusers;
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.POST,
            							consumes = MediaType.APPLICATION_JSON_VALUE, 
            							produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody()
    public User addNewUser(@RequestBody User user) {
        return this.userService.addUser(user);
    }

}