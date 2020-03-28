package com.abhi.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/managers")
	public String managers() {
		return "Hello manager !";
	}

	@GetMapping("/developers")
	public String employees() {
		return "Hello developer!";
	}
}
