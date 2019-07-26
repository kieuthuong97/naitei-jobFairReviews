package com.javacode.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.javacode.entities.User;
import com.javacode.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController extends BaseController{

	@GetMapping({ "/users" })
	public String index(Model model) {
		model.addAttribute("users", userService.findAll());
		return "views/user/index";
	}

	@RequestMapping(value = "/users/{id}/delete", method = RequestMethod.GET)
	public String deleteUser(@PathVariable("id") Integer id, final RedirectAttributes redirectAttributes) {
		log.info("delete user");
		if (userService.deleteUser(id)) {
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Delete user");
			
		} else {
			redirectAttributes.addFlashAttribute("css", "error");
			redirectAttributes.addFlashAttribute("msg", "Delete fail");
		}
		return "redirect:/users";
	}

	@RequestMapping(value = "/users/add", method = RequestMethod.GET)
	public String newUser(Model model) {
		User user = new User();
		model.addAttribute("userForm", user);
		model.addAttribute("status", "add");
		return "views/user/user-form";
	}

	@RequestMapping(value = "/users/{id}/edit", method = RequestMethod.GET)
	public String editUser(@PathVariable("id") int id, Model model) {
		User user = userService.findById(id);
		model.addAttribute("userForm", user);
		model.addAttribute("status", "edit");
		return "views/user/user-form";
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public String submitAddOrUpdateUser(@Valid @ModelAttribute("userForm") User user, BindingResult bindingResult,
			@RequestParam("status") String status, Model model, final RedirectAttributes redirectAttributes) {
		log.info("submit add/update user");
		if (bindingResult.hasErrors()) {
			return "views/user/user-form";
		}
		userService.saveOrUpdate(user);
		model.addAttribute("user", user);
		if (status.equals("add")) {
			redirectAttributes.addFlashAttribute("msg", "User is added!");
		}
		if (status.equals("edit")) {
			redirectAttributes.addFlashAttribute("msg", "User is updated!");
		}
		return "redirect:/users";
	}

	@RequestMapping(value = "/searchUsers", method = RequestMethod.GET)
	public String search(@RequestParam("name") String name, @RequestParam("email") String email, Model model) {
		log.info("search user");
		List<User> users = userService.searchUsers(name, email);
		model.addAttribute("users", users);
		return "views/user/index";
	}

}
