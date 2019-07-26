package com.javacode.controller;

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

import com.javacode.entities.Job;
import com.javacode.service.JobService;

@Controller
public class JobController extends BaseController {

	private static final Logger logger = Logger.getLogger(JobController.class);

	@GetMapping({ "/" })
	public String index(Model model) {
		model.addAttribute("jobs", jobService.findAll());
		return "views/jobs/index";
	}
	@RequestMapping(value = "/jobs/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") int id, Model model) {
		logger.info("detail job");
		Job job = jobService.findById(id);
		if (job == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "Job not found");
		}
		model.addAttribute("job", job);
		return "views/jobs/job";
	}

	@RequestMapping(value = "/jobs/{id}/delete", method = RequestMethod.GET)
	public String deleteJob(@PathVariable("id") Integer id, final RedirectAttributes redirectAttributes) {
		logger.info("delete job");
		if (jobService.deleteJob(id)) {
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg",
					messageSource.getMessage("job.delete.success", null, Locale.US));
		} else {
			redirectAttributes.addFlashAttribute("css", "error");
			redirectAttributes.addFlashAttribute("msg", messageSource.getMessage("job.delete.fail", null, Locale.US));
		}

		return "redirect:/";

	}

	@RequestMapping(value = "/jobs/add", method = RequestMethod.GET)
	public String newStudent(Model model) {
		Job job = new Job();

		model.addAttribute("jobForm", job);
		model.addAttribute("status", "add");

		return "views/jobs/job-form";

	}

	@RequestMapping(value = "/jobs/{id}/edit", method = RequestMethod.GET)
	public String editJob(@PathVariable("id") int id, Model model) {

		Job job = jobService.findById(id);
		model.addAttribute("jobForm", job);
		model.addAttribute("status", "edit");

		return "views/jobs/job-form";

	}

	@RequestMapping(value = "/jobs", method = RequestMethod.POST)
	public String submitAddOrUpdateJob(@Valid @ModelAttribute("jobForm") Job job, BindingResult bindingResult,
			Model model, @RequestParam("status") String status, final RedirectAttributes redirectAttributes) {
		logger.info("add/update student");

		if (bindingResult.hasErrors()) {
			model.addAttribute("css", "error");
			model.addAttribute("status", status);
			if (status.equals("edit")) {
				model.addAttribute("msg", messageSource.getMessage("job.edit.fail", null, Locale.US));
			}
			if (status.equals("add")) {
				model.addAttribute("msg", messageSource.getMessage("job.create.fail", null, Locale.US));
			}
			return "views/jobs/job-form";
		}

		jobService.saveOrUpdate(job);
		model.addAttribute("job", job);
		model.addAttribute("css", "success");
		if (status.equals("edit")) {
			redirectAttributes.addFlashAttribute("msg", messageSource.getMessage("job.edit.success", null, Locale.US));
		}
		if (status.equals("add")) {
			redirectAttributes.addFlashAttribute("msg",
					messageSource.getMessage("job.create.success", null, Locale.US));
		}
		return "redirect:jobs/" + job.getId() + "/edit";
	}
}
