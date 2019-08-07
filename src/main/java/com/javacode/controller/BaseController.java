package com.javacode.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javacode.service.CommentService;
import com.javacode.service.CompanyService;
import com.javacode.service.JobService;
import com.javacode.service.UserService;

public class BaseController {
	@Autowired
	protected MessageSource messageSource;

	@Autowired
	protected JobService jobService;

	@Autowired
	protected UserService userService;
	
	@Autowired
	protected CommentService commentService;

	@Autowired
	protected CompanyService companyService;

	protected void loadAttributes(Model model) {
		model.addAttribute("jobs", jobService.findAll());
		model.addAttribute("users", userService.findAll());
		model.addAttribute("companies", companyService.findAll());
		model.addAttribute("comments", commentService.findAll());
	}

	// Add message and css in Model 
	// Success
	protected void addModelMessageSuccess(Model model, String nameMsg) {
		model.addAttribute("css", "success");
		model.addAttribute("msg", messageSource.getMessage(nameMsg, null, Locale.US));
	}

	// Error
	protected void addModelMessageFail(Model model, String nameMsg) {
		model.addAttribute("css", "error");
		model.addAttribute("msg", messageSource.getMessage(nameMsg, null, Locale.US));
	}

	// Danger
	protected void addModelMessageWarning(Model model, String nameMsg) {
		model.addAttribute("css", "warning");
		model.addAttribute("msg", messageSource.getMessage(nameMsg, null, Locale.US));
	}

	// Add message and css in Redirect
	// Success
	protected void addRedirectMessageSuccess(final RedirectAttributes redirectAttributes, String nameMsg) {
		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", messageSource.getMessage(nameMsg, null, Locale.US));
	}

	// Fail
	protected void addRedirectMessageFail(final RedirectAttributes redirectAttributes, String nameMsg) {
		redirectAttributes.addFlashAttribute("css", "error");
		redirectAttributes.addFlashAttribute("msg", messageSource.getMessage(nameMsg, null, Locale.US));
	}

	// Danger
	protected void addRedirectMessageWarning(RedirectAttributes redirectAttributes, String nameMsg) {
		redirectAttributes.addFlashAttribute("css", "warning");
		redirectAttributes.addFlashAttribute("msg", messageSource.getMessage(nameMsg, null, Locale.US));
	}

}
