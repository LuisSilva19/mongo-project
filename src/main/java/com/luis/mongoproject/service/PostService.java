package com.luis.mongoproject.service;

import com.luis.mongoproject.domain.Post;
import com.luis.mongoproject.exception.ObjectNotFoundException;
import com.luis.mongoproject.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;
	
	public Post findById(String id) {
		return postRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Post> findByTitle(String text) {
		return postRepository.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return postRepository.fullSearch(text, minDate, maxDate);
	}
}
