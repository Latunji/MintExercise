package com.project.exercise.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController implements ErrorController {
	private static final String PATH = "/error";
	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return PATH;
	}
	
	@RequestMapping("/error")
	public String error() {
		return "404 - No Mapping Found For Specified EndPoint";
	}
	

}
