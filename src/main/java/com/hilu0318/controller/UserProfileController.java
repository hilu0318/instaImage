package com.hilu0318.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProfileController {
	
	@GetMapping("/test")
	public String getTest() {
		return "hihihi";
	}
}
