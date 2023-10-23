package com.cfs.biz.controller;

import org.springframework.web.bind.annotation.GetMapping;


import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	
	@GetMapping("/router/ping")
	public String showStatus() {
		System.out.println("Request recieved on server");
		return "Service is up and running!!!!!";
	}

}
