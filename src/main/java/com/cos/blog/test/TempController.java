package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempController {
	
	@GetMapping("/temp/home")
	public String TempHome() {
		System.out.println("tempHome()");
		return "home.html";
	}

}
