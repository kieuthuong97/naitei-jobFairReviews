package com.javacode.controller.web;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javacode.controller.BaseController;
import com.javacode.entities.Job;
import com.javacode.entities.User;

@Controller("WebJobController")
public class JobController extends BaseController {

	private static final Logger logger = Logger.getLogger(JobController.class);

	@RequestMapping(value = "/blog", method = RequestMethod.GET)
	public String show(Model model, final RedirectAttributes redirectAttributes) {
		logger.info("detail job");
		
		List<Job> jobs = jobService.findAll();

		if (jobs == null) {
			addRedirectMessageWarning(redirectAttributes, "job.search.fail");
			return "redirect:/";
		}
		model.addAttribute("jobs", jobs);
		return "views/web/blog";
	}
	

}
