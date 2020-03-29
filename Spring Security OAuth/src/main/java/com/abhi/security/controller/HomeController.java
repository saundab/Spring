package com.abhi.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.security.dto.AuthenticationRequest;
import com.abhi.security.dto.AuthenticationResponse;
import com.abhi.security.service.MyUserDetailsService;
import com.abhi.security.util.JwtUtil;

@RestController
public class HomeController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private MyUserDetailsService userService;
	
	@GetMapping("/managers")
	public String managers() {
		return "Hello manager !";
	}

	@GetMapping("/developers")
	public String developers() {
		return "Hello developer!";
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) throws Exception{
		AuthenticationResponse resp =null;
		
		//uses the userName service configured in securityConfiguration
		Authentication auth =  authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
		
		String jwt = JwtUtil.generateToken((UserDetails)auth.getPrincipal());
		resp = new AuthenticationResponse(jwt);
		
		
		return ResponseEntity.ok(resp);
	}
}
