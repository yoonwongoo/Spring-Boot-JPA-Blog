package com.yoon.blog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.yoon.blog.config.auth.PrincipalDetail;

@Controller
public class BoardController {

	@GetMapping("/")
	public String index() {
		return "index";
	}

}
