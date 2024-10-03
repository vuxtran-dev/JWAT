package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class UserController {
	
    @Autowired
    private JwtUtil jwtUtil; 
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	

	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody RegisterRequestBody registerRequestBody) {
	    if (userRepository.existsByUsername(registerRequestBody.getUsername())) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already taken");
	    }

	    if (userRepository.existsByEmail(registerRequestBody.getEmail())) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already in use");
	    }

	    // Mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu
	    String encodedPassword = passwordEncoder.encode(registerRequestBody.getPassword());

	    // Tạo đối tượng người dùng mới và lưu vào cơ sở dữ liệu
	    User newUser = new User();
	    newUser.setUsername(registerRequestBody.getUsername());
	    newUser.setEmail(registerRequestBody.getEmail());
	    newUser.setPassword(encodedPassword); // Lưu mật khẩu đã mã hóa

	    userRepository.save(newUser);

	    return ResponseEntity.ok("User registered successfully");
	}

	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequestBody loginRequest) {
	    String username = loginRequest.getUsername();
	    String password = loginRequest.getPassword();

	    User user = userService.findByUsername(username);
	    if (user != null && passwordEncoder.matches(password, user.getPassword())) {
	    	String token = jwtUtil.generateToken(username);
	        // Tạo đối tượng response với thông tin người dùng
	    	ResponseUser userResponse = new ResponseUser(user.getUsername(), user.getRole(), token);
	        return ResponseEntity.ok(userResponse);
	    }
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
	}
}
