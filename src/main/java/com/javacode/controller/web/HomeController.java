package com.javacode.controller.web;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.javacode.controller.BaseController;

@Controller
public class HomeController extends BaseController {

	private static final Logger logger = Logger.getLogger(HomeController.class);

	@GetMapping({ "/" })
	public String index(Model model) {
		loadAttributes(model);
		return "views/web/index";
	}

	@GetMapping({ "/job-listings" })
	public String listings(Model model) {
		loadAttributes(model);
		return "views/web/job-listings";
	}

	@GetMapping({ "/job-single" })
	public String jobSingle(Model model) {
		loadAttributes(model);
		return "views/web/job-single";
	}

	@GetMapping({ "/about" })
	public String about(Model model) {
		loadAttributes(model);
		return "views/web/about";
	}

	@GetMapping({ "/blog" })
	public String blog(Model model) {
		loadAttributes(model);
		return "views/web/blog";
	}

	@GetMapping({ "/contact" })
	public String contact(Model model) {
		loadAttributes(model);
		return "views/web/contact";
	}

	@GetMapping({ "/services" })
	public String services(Model model) {
		loadAttributes(model);
		return "views/web/services";
	}
}
