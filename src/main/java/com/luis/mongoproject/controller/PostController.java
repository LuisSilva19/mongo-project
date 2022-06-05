package com.luis.mongoproject.controller;

import com.luis.mongoproject.domain.Post;
import com.luis.mongoproject.service.PostService;
import com.luis.mongoproject.util.URL;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

	private final PostService service;

	@GetMapping("/{id}")
 	public Post findById(@PathVariable String id) {
		return service.findById(id);
	}
	
	@GetMapping("/titleSearch")
 	public List<Post> findByTitle(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		return service.findByTitle(text);
	}

	@GetMapping("/fullSearch")
 	public List<Post> fullSearch(
 			@RequestParam(value="text", defaultValue="") String text,
 			@RequestParam(value="minDate", defaultValue="") String minDate,
 			@RequestParam(value="maxDate", defaultValue="") String maxDate) {
		text = URL.decodeParam(text);
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());
		return service.fullSearch(text, min, max);
	}
}
