package com.javacode.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javacode.entities.Job;
import com.javacode.service.JobService;

@Controller
public class JobController {

	private static final Logger logger = Logger.getLogger(JobController.class);

	@Autowired
	private JobService jobService;

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
			redirectAttributes.addFlashAttribute("msg", "Job is deleted!");
		} else {
			redirectAttributes.addFlashAttribute("css", "error");
			redirectAttributes.addFlashAttribute("msg", "Delete job failed!!!!");
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
	public String submitAddOrUpdateJob(@ModelAttribute("jobForm") Job job, BindingResult bindingResult, Model model) {
		logger.info("add/update student");
		jobService.saveOrUpdate(job);
		model.addAttribute("job", job);

		return "views/jobs/job";
	}
}
