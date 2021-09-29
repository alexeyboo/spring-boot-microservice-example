package com.alexeyboo.microserviceexample.service;

import com.alexeyboo.microserviceexample.config.RabbitMQConfig;
import com.alexeyboo.microserviceexample.model.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AppStartupRunner implements ApplicationRunner {
	@Autowired
	private UserService service;

	@Autowired
	private RabbitTemplate template;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setName("Alexey");
		user.setSurname("Bondarev");
		user.setYear(1991);
		template.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, user);
	}
}