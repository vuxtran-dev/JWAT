package com.example.demo.user;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public void registerUser(String username, String password, String email) {
		String encodePassword = passwordEncoder.encode(password);
		User user = new User();
		user.setUsername(username);
		user.setPassword(encodePassword);
		user.setEmail(email);
		userRepository.save(user);
	}
	

	public User findByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.orElse(null); 
    }

}
