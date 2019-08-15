package com.javacode.controller.admin;

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

import com.javacode.controller.BaseController;
import com.javacode.entities.Job;
import com.javacode.service.JobService;

@Controller("AdminJobController")
public class JobController extends BaseController {

	private static final Logger logger = Logger.getLogger(JobController.class);

	@GetMapping({ "/jobs" })
	public String index(Model model) {
		loadAttributes(model);
		return "views/admin/jobs/index";
	}

	@RequestMapping(value = "/jobs/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") int id, Model model, final RedirectAttributes redirectAttributes) {
		logger.info("detail job");
		Job job = jobService.findById(id);

		if (job == null) {
			addRedirectMessageWarning(redirectAttributes, "job.search.fail");
			return "redirect:/";
		}
		model.addAttribute("job", job);
		return "views/admin/jobs/job";
	}

	@RequestMapping(value = "/jobs/{id}/delete", method = RequestMethod.GET)
	public String deleteJob(@PathVariable("id") Integer id, final RedirectAttributes redirectAttributes) {
		logger.info("delete job");
		if (jobService.deleteJob(id)) {
			addRedirectMessageSuccess(redirectAttributes, "job.delete.success");
		} else {
			addRedirectMessageFail(redirectAttributes, "job.delete.fail");
		}
		return "redirect:/jobs";

	}

	@RequestMapping(value = "/jobs/add", method = RequestMethod.GET)
	public String newJob(Model model) {
		Job job = new Job();
		loadAttributes(model);
		model.addAttribute("jobForm", job);
		model.addAttribute("status", "add");
		return "views/admin/jobs/job-form";
	}

	@RequestMapping(value = "/jobs/{id}/edit", method = RequestMethod.GET)
	public String editJob(@PathVariable("id") int id, Model model, final RedirectAttributes redirectAttributes) {
		Job job = jobService.findById(id);
		loadAttributes(model);
		if(job == null) {
			addRedirectMessageWarning(redirectAttributes, "job.search.fail");
			return "redirect:/jobs";
		}
		model.addAttribute("jobForm", job);
		model.addAttribute("status", "edit");
		return "views/admin/jobs/job-form";

	}

	@RequestMapping(value = "/jobs", method = RequestMethod.POST)
	public String submitAddOrUpdateJob(@Valid @ModelAttribute("jobForm") Job job, BindingResult bindingResult,
			Model model, @RequestParam("status") String status, final RedirectAttributes redirectAttributes) {
		logger.info("add/update job");
		if (bindingResult.hasErrors()) {
			model.addAttribute("status", status);
			if (status.equals("edit")) {
				addModelMessageFail(model, "job.edit.fail");
			}
			if (status.equals("add")) {
				addModelMessageFail(model, "job.create.fail");
			}
			return "views/admin/jobs/job-form";
		}

		jobService.saveOrUpdate(job);
		model.addAttribute("job", jobService.findById(job.getId()));
		if (status.equals("edit")) {
			addRedirectMessageSuccess(redirectAttributes, "job.edit.success");
		} else {
			addRedirectMessageSuccess(redirectAttributes, "job.create.success");
		}
		return "redirect:/jobs/" + job.getId();
	}
}
