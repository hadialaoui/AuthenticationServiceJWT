package com.hadialaoui.authenticationServiceJWT.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestAuthorizationController {

	@GetMapping("/dashboardAdmins")
	public String getDashboardAdmins() {
		return "get Dashboar dAdmin";
	}
	
	@GetMapping("/PageUsers")
	public String getPageUsers() {
		return "get Page Users";
	}
}
