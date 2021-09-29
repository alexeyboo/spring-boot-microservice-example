package com.alexeyboo.microserviceexample.controller;

import com.alexeyboo.microserviceexample.config.RabbitMQConfig;
import com.alexeyboo.microserviceexample.model.User;
import com.alexeyboo.microserviceexample.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RabbitTemplate template;

	@GetMapping("/users")
	public List<User> findAll() {
		return userService.findAll();
	}

	@GetMapping("/users/{id}")
	public User findById(@PathVariable String id) {
		return userService.findById(id);
	}

	@PostMapping("/users")
	public User save(@RequestBody User user) {
		return userService.save(user);
	}

	@PutMapping("/users/{id}")
	public User update(@RequestBody User user, @PathVariable String id) {
		return userService.update(user, id);
	}

	@DeleteMapping("/users/{id}")
	public boolean delete(@PathVariable String id) {
		return userService.deleteById(id);
	}

	@GetMapping("/publish")
	public void publish() {
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setName("Alexey");
		user.setSurname("Bondarev");
		user.setYear(1991);
		template.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, user);
	}
}
