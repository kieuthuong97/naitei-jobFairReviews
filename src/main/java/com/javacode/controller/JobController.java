package com.javacode.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.javacode.service.JobService;

@Controller
public class JobController {

	private static final Logger logger = Logger.getLogger(JobController.class);

	@Autowired
	private JobService jobService;

	@GetMapping({ "/" })
	public String index(Model model) {
		model.addAttribute("jobs", jobService.findAll());
		return "views/user/index";
	}
}
