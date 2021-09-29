package com.alexeyboo.microserviceexample.controller;

import com.alexeyboo.microserviceexample.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper objectMapper;

	@Test
	void saveTest() throws Exception {
		User user = new User("1", "Pakalu", "Papito", 1966);

		this.mockMvc
				.perform(post("/users")
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(user))
				)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Pakalu"))
				.andExpect(jsonPath("$.surname").value("Papito"))
				.andExpect(jsonPath("$.year").value(1966))
				.andExpect(jsonPath("$.id").isNotEmpty())
				.andReturn();
	}
}