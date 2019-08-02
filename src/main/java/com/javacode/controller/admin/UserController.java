package com.javacode.controller.admin;

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

import com.javacode.controller.BaseController;
import com.javacode.entities.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController extends BaseController {

	@GetMapping({ "/users" })
	public String index(Model model) {
		loadAttributes(model);
		return "views/admin/user/index";
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") int id, Model model,  final RedirectAttributes redirectAttributes) {
		User user = userService.findById(id);
		if (user == null) {
			addRedirectMessageWarning(redirectAttributes,  "user.search.fail");
			return "redirect:/users";
		}
		model.addAttribute("user", user);
		return "views/admin/user/user";
	}

	@RequestMapping(value = "/users/{id}/delete", method = RequestMethod.GET)
	public String deleteUser(@PathVariable("id") Integer id, final RedirectAttributes redirectAttributes) {
		log.info("delete user");
		if (userService.deleteUser(id)) {
			addRedirectMessageSuccess(redirectAttributes, "user.delete.success");
		} else {
			addRedirectMessageFail(redirectAttributes, "user.delete.fail");
		}
		return "redirect:/users";
	}

	@RequestMapping(value = "/users/add", method = RequestMethod.GET)
	public String newUser(Model model) {
		User user = new User();
		model.addAttribute("userForm", user);
		model.addAttribute("status", "add");
		return "views/admin/user/user-form";
	}

	@RequestMapping(value = "/users/{id}/edit", method = RequestMethod.GET)
	public String editUser(@PathVariable("id") int id, Model model,  final RedirectAttributes redirectAttributes) {
		User user = userService.findById(id);
		if (user == null) {
			addRedirectMessageWarning(redirectAttributes,  "user.search.fail");
			return "redirect:/users";
		}
		model.addAttribute("userForm", user);
		model.addAttribute("status", "edit");
		return "views/admin/user/user-form";
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public String submitAddOrUpdateUser(@Valid @ModelAttribute("userForm") User user, BindingResult bindingResult,
			@RequestParam("status") String status, @RequestParam("email") String email, Model model,
			final RedirectAttributes redirectAttributes) {
		log.info("submit add/update user");
		if (bindingResult.hasErrors()) {
			model.addAttribute("status", status);
			if (status.equals("add")) {
				addModelMessageFail(model, "user.create.fail");
			}
			if (status.equals("edit")) {
				addModelMessageFail(model, "user.edit.fail");
			}
			return "views/admin/user/user-form";
		}
		try {
			userService.saveOrUpdate(user);
		} catch (Exception e) {
			model.addAttribute("status", status);
			addModelMessageFail(model, "user.email.unique");
			return "views/admin/user/user-form";
		}

		model.addAttribute("user", userService.findById(user.getId()));
		if (status.equals("add")) {
			addRedirectMessageSuccess(redirectAttributes, "user.create.success");
		}
		if (status.equals("edit")) {
			addRedirectMessageSuccess(redirectAttributes, "user.edit.success");
		}
		return "redirect:/users/" + user.getId();
	}

	@RequestMapping(value = "/searchUsers", method = RequestMethod.GET)
	public String search(@RequestParam("name") String name, @RequestParam("email") String email, Model model) {
		log.info("search user");
		List<User> users = userService.searchUsers(name, email);
		model.addAttribute("users", users);
		return "views/admin/user/index";
	}

}
