package com.luis.mongoproject.service;

import com.luis.mongoproject.domain.User;
import com.luis.mongoproject.dto.UserDTO;
import com.luis.mongoproject.exception.ObjectNotFoundException;
import com.luis.mongoproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(String id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public User insert(User user) {
		return userRepository.insert(user);
	}

	public void delete(String id) {
		findById(id);
		userRepository.deleteById(id);
	}

	public User update(User user) {
		return userRepository.save(user);
	}

	public User fromDTO(UserDTO objDto) {
		return User.builder()
				.id(objDto.getId())
				.name(objDto.getName())
				.email(objDto.getEmail())
				.build();
	}

	public UserDTO dtoFromUser(User user) {
		return UserDTO.builder()
				.id(user.getId())
				.name(user.getName())
				.email(user.getEmail())
				.build();
	}

 	public User updateUser(User user, UserDTO userDTO) {
		return user.builder()
				.id(userDTO.getId())
				.name(userDTO.getName())
				.email(userDTO.getEmail())
				.build();
	}
}
