package com.javacode.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.javacode.controller.BaseController;

@Controller
public class UserPeofileController extends BaseController {

	@GetMapping({ "/profile" })
	public String index(Model model) {
		loadAttributes(model);
		return "views/admin/jobs/index";
	}

}
