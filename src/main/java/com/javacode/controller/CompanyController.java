package com.javacode.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javacode.entities.Company;
import com.javacode.entities.Job;


@Controller
public class CompanyController extends BaseController {
	private static final Logger logger = Logger.getLogger(CompanyController.class);
	
	@RequestMapping(value = "/companies")
	public ModelAndView index() {
		logger.info("company page");
		ModelAndView model = new ModelAndView("views/companies/company-index");
		model.addObject("company", new Company());
		model.addObject("companies", companyService.findAll());
		return model;
	}
	
	@RequestMapping(value = "/companies/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") int id, Model model) {
		logger.info("detail company");
		Company company = companyService.findById(id);
		if (company == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", messageSource.getMessage("company.notFound", null, Locale.US));
		}
		model.addAttribute("company", company);
		return "views/companies/company-detail";
	}
	
	@RequestMapping(value = "/companies/add", method = RequestMethod.GET)
	public String newCompany(Model model) {
		Company company = new Company();

		model.addAttribute("companyForm", company);
		model.addAttribute("status", "add");

		return "views/companies/company-add-form";
	}
	
	@RequestMapping(value = "/companies/{id}/edit", method = RequestMethod.GET)
	public String editCompany(@PathVariable("id") int id, Model model) {

		Company company = companyService.findById(id);
		model.addAttribute("companyForm", company);
		model.addAttribute("status", "edit");

		return "views/companies/company-add-form";
	}
	
	@RequestMapping(value = "/companies", method = RequestMethod.POST)
	public String submitAddOrUpdateCompany(@Valid @ModelAttribute("companyForm") Company company, BindingResult bindingResult,
			Model model, @RequestParam("status") String status, final RedirectAttributes redirectAttributes) {
		logger.info("add/update company");

		if (bindingResult.hasErrors()) {
			model.addAttribute("css", "error");
			model.addAttribute("status", status);
			if (status.equals("edit")) {
				model.addAttribute("msg", messageSource.getMessage("company.edit.fail", null, Locale.US));
			}
			if (status.equals("add")) {
				model.addAttribute("msg", messageSource.getMessage("company.create.fail", null, Locale.US));
			}
			return "views/companies/company-add-form";
		}
		model.addAttribute("status", status);
		companyService.saveOrUpdate(company);
		model.addAttribute("company", company);
		model.addAttribute("css", "success");
		if (status.equals("edit")) {
			redirectAttributes.addFlashAttribute("msg", messageSource.getMessage("company.edit.success", null, Locale.US));
		}
		if (status.equals("add")) {
			redirectAttributes.addFlashAttribute("msg", messageSource.getMessage("company.create.success", null, Locale.US));
		}
		return "redirect:companies/" + company.getId() + "/edit";
	
	}
	
	@RequestMapping(value = "/companies/{id}/delete", method = RequestMethod.GET)
	public String deleteStudent(@PathVariable("id") Integer id, final RedirectAttributes redirectAttributes) {
		logger.info("delete company");
		if (companyService.deleteCompany(id)) {
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", messageSource.getMessage("company.delete.success", null, Locale.US));
		} else {
			redirectAttributes.addFlashAttribute("css", "error");
			redirectAttributes.addFlashAttribute("msg", messageSource.getMessage("company.delete.fail", null, Locale.US));
		}

		return "redirect:/companies";
	}

}