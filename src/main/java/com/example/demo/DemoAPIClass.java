package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

//annotation
@RestController
public class DemoAPIClass {
	@GetMapping("/callPrint")
	public String printInfo() {
		return "thank you";
	} 
}
   