package com.alexeyboo.microserviceexample.mq;

import com.alexeyboo.microserviceexample.config.RabbitMQConfig;
import com.alexeyboo.microserviceexample.model.User;
import com.alexeyboo.microserviceexample.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
@EnableRabbit
public class MessageListener {

	@Autowired
	private UserService service;

	@RabbitListener(queues = RabbitMQConfig.QUEUE)
	public void listen(User user) {
		log.info("Message is consumed = " + user);
		service.save(user);
	}
}
