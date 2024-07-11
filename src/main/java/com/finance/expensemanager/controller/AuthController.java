package com.finance.expensemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.finance.expensemanager.auth.JwtUtil;
import com.finance.expensemanager.model.dto.AuthRequest;
import com.finance.expensemanager.model.dto.AuthResponse;
import com.finance.expensemanager.model.entity.User;
import com.finance.expensemanager.repository.UserRepository;
import com.finance.expensemanager.service.UserService;

@CrossOrigin(origins= {"*"}, maxAge = 4800)
@RestController
public class AuthController {
	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/register")
	public ResponseEntity<AuthResponse> registerUser(@RequestBody User request) {
		request.setPassword(passwordEncoder.encode(request.getPassword()));
		User user = userService.registerUser(request);
		final String jwt = jwtUtil.generateToken(user);
		AuthResponse response = new AuthResponse();
		response.setJwt(jwt);
		response.setUserId(user.getId());
		response.setUsername(user.getUsername());
		return ResponseEntity.ok(response);
	}

	@PostMapping("/authenticate")
	public ResponseEntity<AuthResponse> createAuthenticationToken(@RequestBody AuthRequest request)
			throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					request.getUsername(), request.getPassword()));
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Incorrect username or password", e);
		}

		User user = userRepository.findByUsername(request.getUsername());
		final String jwt = jwtUtil.generateToken(user);
		AuthResponse response = new AuthResponse();
		response.setJwt(jwt);
		response.setUserId(user.getId());
		response.setUsername(user.getUsername());
		return ResponseEntity.ok(response);
	}
}
