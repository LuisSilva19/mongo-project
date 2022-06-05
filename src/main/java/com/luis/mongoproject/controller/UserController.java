package com.luis.mongoproject.controller;

import com.luis.mongoproject.domain.Post;
import com.luis.mongoproject.domain.User;
import com.luis.mongoproject.dto.UserDTO;
import com.luis.mongoproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService service;
	
	@GetMapping()
 	public List<User> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
 	public User findById(@PathVariable String id) {
		return service.findById(id);
	}

	@PostMapping()
 	public User insert(@RequestBody UserDTO objDto) {
		User user = service.fromDTO(objDto);
		return service.insert(user);
	}

	@DeleteMapping("/{id}")
 	public void delete(@PathVariable String id) {
		service.delete(id);
	}

	@PutMapping("/{id}")
 	public User update(@RequestBody UserDTO userDTO, @PathVariable String id) {
		var user = service.findById(id);
		return service.update(service.updateUser(user, userDTO));
	}
	
	@GetMapping("/{id}/posts")
 	public List<Post> findPosts(@PathVariable String id) {
		return service.findById(id).getPosts();
	}
}
