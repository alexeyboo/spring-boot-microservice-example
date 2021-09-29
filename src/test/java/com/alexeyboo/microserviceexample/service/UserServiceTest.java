package com.alexeyboo.microserviceexample.service;

import com.alexeyboo.microserviceexample.model.User;
import com.alexeyboo.microserviceexample.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	UserRepository userRepository;
	@InjectMocks
	UserService userService;

	static List<User> expectedUsers;
	static User expectedUser1;
	static User expectedUser2;

	@BeforeAll
	static void init() {
		expectedUsers = Collections.EMPTY_LIST;
		expectedUser1 = new User("1", "Pakalu", "Papito", 1966);
		expectedUser2 = new User("2", "Jim", "Halpert", 1978);
	}

	@Test
	void findAll() {
		when(userRepository.findAll()).thenReturn(expectedUsers);
		List<User> actual = userService.findAll();
		assertEquals(expectedUsers, actual);
	}

	@Test
	void findById() {
		when(userRepository.findById("id")).thenReturn(Optional.of(expectedUser1));
		User actual = userService.findById("id");
		assertEquals(expectedUser1, actual);
	}

	@Test
	void save() {
		when(userRepository.save(expectedUser1)).thenReturn(expectedUser1);
		User actual = userService.save(expectedUser1);
		assertEquals(expectedUser1, actual);
	}

	@Test
	void update() {
		when(userRepository.save(expectedUser1)).thenReturn(expectedUser2);
		User actual = userService.update(expectedUser1, "1");
		assertEquals(expectedUser2, actual);
	}

	@Test
	void deleteById() {
		when(userRepository.findById("1"))
				.thenReturn(Optional.of(expectedUser1))
				.thenReturn(Optional.empty());
		boolean actual = userService.deleteById("1");
		verify(userRepository).deleteById("1");
		assertTrue(actual);
	}
}