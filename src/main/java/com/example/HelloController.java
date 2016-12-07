package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	

	@RequestMapping("/demo/rest")
	public String index() {
		return giveGreeting();
	}

	public String giveGreeting() {
		return "Sample spring task application";
	}

}
