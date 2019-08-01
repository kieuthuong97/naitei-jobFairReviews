package com.javacode.controller.web;

import java.util.Locale;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javacode.controller.BaseController;
import com.javacode.entities.Job;
import com.javacode.service.JobService;

@Controller
public class HomeController extends BaseController {

	private static final Logger logger = Logger.getLogger(HomeController.class);

	@GetMapping({ "/home" })
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
	
	@GetMapping({ "/blog-single" })
	public String blogSingle(Model model) {
		loadAttributes(model);
		return "views/web/blog-single";
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
