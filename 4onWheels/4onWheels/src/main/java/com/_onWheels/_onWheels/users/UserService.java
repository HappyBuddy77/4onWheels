package com._onWheels._onWheels.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User registerUser(UserRegistrationDto registrationDto) {
		if (userRepository.existsByEmail(registrationDto.getEmail())) {
			throw new RuntimeException("Email already registered" + registrationDto.getEmail());

		}
		if (!isPasswordValid(registrationDto.getPassword())) {
			throw new RuntimeException(
					"Password must be at least 8 characters with 1 upper can and 1 special character");

		}

		// Create new user
		User user = new User();
		user.setEmail(registrationDto.getEmail().toLowerCase());
		user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
		user.setFirstName(registrationDto.getFirstName());
		user.setLastName(registrationDto.getLastName());
		user.setCreatedAt(LocalDateTime.now());

		// Save to database
		User savedUser = userRepository.save(user);
		System.out.print("User registered: " + savedUser.getEmail());

		return savedUser;
	}

	private boolean isPasswordValid(String password) {
		if (password == null || password.length() < 8) {
			return false;
		}
		boolean hasUppercase = false;
		boolean hasSpecialChar = false;
		String specialChars = "@$!%*?&-_";

		for (int i = 0; i < password.length(); i++) {
			char c = password.charAt(i);

			if (Character.isUpperCase(c)) {
				hasUppercase = true;
			}
			if (specialChars.contains(String.valueOf(c))) {
				hasSpecialChar = true;
			}

		}

		return hasUppercase && hasSpecialChar;

	}

	public boolean emailExists(String email) {
		return userRepository.existsByEmail(email.toLowerCase());
	}
	
}
