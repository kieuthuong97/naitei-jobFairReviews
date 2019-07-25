package com.javacode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.javacode.service.JobService;
import com.javacode.service.UserService;

public class BaseController {
	@Autowired
	protected MessageSource messageSource;
	
	@Autowired
	protected JobService jobService;
	
	@Autowired
	protected UserService userService;

}
