package com.javacode.controller.admin;

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

import com.javacode.controller.BaseController;
import com.javacode.entities.Company;

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
	public String show(@PathVariable("id") int id, Model model, final RedirectAttributes redirectAttributes) {
		logger.info("detail company");
		Company company = companyService.findById(id);
		if (company == null) {
			addRedirectMessageWarning(redirectAttributes, "company.notFound");
			return "redirect:/companies";
		}
		model.addAttribute("company", company);
		return "views/companies/company-detail";
	}

	@RequestMapping(value = "/companies/add", method = RequestMethod.GET)
	public String newCompany(Model model) {
		model.addAttribute("companyForm", new Company());
		model.addAttribute("status", "add");

		return "views/companies/company-add-form";
	}

	@RequestMapping(value = "/companies/{id}/edit", method = RequestMethod.GET)
	public String editCompany(@PathVariable("id") int id, Model model, final RedirectAttributes redirectAttributes) {
		Company company = companyService.findById(id);
		if (company != null) {
			model.addAttribute("companyForm", company);
			model.addAttribute("status", "edit");

			return "views/companies/company-add-form";
		}
		addRedirectMessageWarning(redirectAttributes, "company.notFound");
		return "redirect:/companies";
	}

	@RequestMapping(value = "/companies", method = RequestMethod.POST)
	public String submitAddOrUpdateCompany(@Valid @ModelAttribute("companyForm") Company company,
			BindingResult bindingResult, Model model, @RequestParam("status") String status,
			final RedirectAttributes redirectAttributes) {
		logger.info("add/update company");

		if (bindingResult.hasErrors()) {
			model.addAttribute("status", status);
			if (status.equals("edit")) {
				addModelMessageFail(model, "company.edit.fail");
			}
			if (status.equals("add")) {
				addModelMessageFail(model, "company.create.fail");
			}
			return "views/companies/company-add-form";
		}
		
		companyService.saveOrUpdate(company);
		model.addAttribute("company", companyService.findById(company.getId()));
		if (status.equals("edit")) {
			addRedirectMessageSuccess(redirectAttributes, "company.edit.success");
		}
		if (status.equals("add")) {
			addRedirectMessageSuccess(redirectAttributes, "company.create.success");
		}
		return "redirect:/companies/" + company.getId();

	}

	@RequestMapping(value = "/companies/{id}/delete", method = RequestMethod.GET)
	public String deleteStudent(@PathVariable("id") Integer id, final RedirectAttributes redirectAttributes) {
		logger.info("delete company");
		if (companyService.deleteCompany(id)) {
			addRedirectMessageSuccess(redirectAttributes, "company.delete.success");
		} else {
			addRedirectMessageFail(redirectAttributes, "company.delete.fail");
		}

		return "redirect:/companies";
	}

}