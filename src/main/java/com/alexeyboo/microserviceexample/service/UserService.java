package com.alexeyboo.microserviceexample.service;

import com.alexeyboo.microserviceexample.model.User;
import com.alexeyboo.microserviceexample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(String id) {
		return userRepository.findById(id).orElseThrow();
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public User update(User user, String id) {
		user.setId(id);
		return userRepository.save(user);
	}

	public boolean deleteById(String id) {
		if (userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
		}
		return userRepository.findById(id).isEmpty();
	}
}
