package com.javacode.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController extends BaseController {

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

			redirectAttributes.addFlashAttribute("msg",
					messageSource.getMessage("user.delete.success", null, Locale.US));

		} else {
			redirectAttributes.addFlashAttribute("css", "error");
			redirectAttributes.addFlashAttribute("msg", messageSource.getMessage("user.delete.fail", null, Locale.US));

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

			@RequestParam("status") String status, @RequestParam("email") String email, Model model,
			final RedirectAttributes redirectAttributes) {
		log.info("submit add/update user");
		if (bindingResult.hasErrors()) {
			model.addAttribute("css", "error");
			model.addAttribute("status", status);
			if (status.equals("add")) {
				model.addAttribute("msg", messageSource.getMessage("user.add.error", null, Locale.US));
			}
			if (status.equals("edit")) {
				model.addAttribute("msg", messageSource.getMessage("user.edit.error", null, Locale.US));
			}
			return "views/user/user-form";
		}

		userService.saveOrUpdate(user);
		model.addAttribute("user", user);
		if (status.equals("add")) {
			redirectAttributes.addFlashAttribute("msg", messageSource.getMessage("user.add.success", null, Locale.US));
		}
		if (status.equals("edit")) {
			redirectAttributes.addFlashAttribute("msg", messageSource.getMessage("user.edit.success", null, Locale.US));

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
