package com.abhi.security.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/")
	public String index() {

		UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder
				.getContext().getAuthentication();

		LdapUserDetailsImpl principal = (LdapUserDetailsImpl) authentication.getPrincipal();

		return "Spring Security + Spring LDAP Authentication Configuration Example";
	}

	@GetMapping("/managers")
	public String managers() {
		return "Hello manager !";
	}

	@GetMapping("/developers")
	public String employees() {
		return "Hello developer!";
	}
}
