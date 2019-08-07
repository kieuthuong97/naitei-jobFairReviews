package com.javacode.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javacode.controller.admin.CompanyController;
import com.javacode.entities.User;

@Controller
public class LoginController extends BaseController {
	private static final Logger logger = Logger.getLogger(CompanyController.class);
	
	@RequestMapping(value = "/login")
	public String login(Model model, Authentication authentication) {
		if (authentication == null) {
			return "views/login/login";
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
		User user = new User();
		model.addAttribute("userRegister", user);
		return "views/login/register";
	}

	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	public String submitAddOrUpdateUser(@Valid @ModelAttribute("userRegister") User user,
			@RequestParam("email") String email, @RequestParam("password") String password, Model model,
			final RedirectAttributes redirectAttributes) {

		User existedUser = userService.findByEmail(email);
	
		if (existedUser != null) {
			addModelMessageFail(model, "user.email.unique");
			return "views/login/register";
		}
		user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt(4)));
		logger.info(user.getPassword());
		logger.info(user.getEmail());

		user.setRole(0);
		logger.info(user.getRole());

		try {
			userService.saveOrUpdate(user);
			return "views/login/login";
		} catch (Exception e) {
			return "views/login/register";
		}

	}
}

