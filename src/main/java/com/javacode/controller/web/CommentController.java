package com.javacode.controller.web;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javacode.controller.BaseController;
import com.javacode.entities.Comment;
import com.javacode.entities.Job;
import com.javacode.service.impl.UserAuth;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("blog-single/{id_job}")
public class CommentController extends BaseController {

	@RequestMapping(value = { "" }, method = RequestMethod.GET)
	public String newComment(@PathVariable("id_job") int idJob, Model model, HttpSession session) {
		Comment comment = new Comment();
		model.addAttribute("cmtForm", comment);
		model.addAttribute("job", jobService.findById(idJob));
		return "views/web/blog-single";
	}

	@RequestMapping(value = { "/{id}/reply" }, method = RequestMethod.GET)
	public String newReply(@PathVariable("id_job") int idJob, Model model, @PathVariable("id") int id) {
		Comment comment = new Comment();
		model.addAttribute("cmtForm", comment);
		model.addAttribute("job", jobService.findById(idJob));

		Comment newReply = new Comment();
		model.addAttribute("replyForm", newReply);

		log.info("submit reply/update reply" + newReply.getId());
		Comment oldComment = commentService.findById(id);
		model.addAttribute("comment", oldComment);

		model.addAttribute("replies", oldComment.getReplies());

		return "views/web/blog-single";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String submitAddOrUpdateComment(@ModelAttribute("cmtForm") Comment comment,
			@PathVariable("id_job") int idJob, Model model, Authentication authentication) {
		model.addAttribute("job", jobService.findById(idJob));
		log.info("submit comment/update comment");
		if (authentication != null) {
			UserAuth userAuth = (UserAuth) authentication.getPrincipal();
			model.addAttribute("comment", commentService.saveComment(comment, idJob, userAuth.getUser()));
			return "views/web/blog-single";
		} else {
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/{id}/reply", method = RequestMethod.POST)
	public String submitAddOrUpdateReply(@ModelAttribute("replyForm") Comment reply, @PathVariable("id") Integer id,
			@PathVariable("id_job") int idJob, Model model, Authentication authentication) {
		log.info("submit reply/update reply" + reply.getId());
		model.addAttribute("job", jobService.findById(idJob));
		if (authentication != null) {
			UserAuth userAuth = (UserAuth) authentication.getPrincipal();

			Comment oldComment = commentService.findById(id);
			model.addAttribute("replies", model.addAttribute("comment",
					commentService.saveReply(reply, id, oldComment, idJob, userAuth.getUser())));

			Comment comment = new Comment();
			model.addAttribute("cmtForm", comment);

			Comment newReply = new Comment();
			model.addAttribute("comment", oldComment);
			model.addAttribute("replyForm", newReply);

			return "views/web/blog-single";
		} else {
			return "redirect:/login";
		}
	}

}