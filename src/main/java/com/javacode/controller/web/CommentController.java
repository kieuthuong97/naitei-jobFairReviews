package com.javacode.controller.web;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.javacode.controller.BaseController;
import com.javacode.entities.Comment;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("blog-single")
public class CommentController extends BaseController {

	@RequestMapping(value = { "" }, method = RequestMethod.GET)
	public String newComment(Model model, HttpSession session) {
		Comment comment = new Comment();
		model.addAttribute("cmtForm", comment);
		return "views/web/blog-single";
	}

	@RequestMapping(value = { "/{id}/reply" }, method = RequestMethod.GET)
	public String newReply(Model model, @PathVariable("id") int id) {
		Comment comment = new Comment();
		model.addAttribute("cmtForm", comment);

		Comment newReply = new Comment();
		model.addAttribute("replyForm", newReply);

		log.info("submit reply/update reply" + newReply.getId());
		Comment oldComment = commentService.findById(id);
		model.addAttribute("comment", oldComment);

		model.addAttribute("replies", oldComment.getReplies());

		return "views/web/blog-single";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String submitAddOrUpdateComment(@ModelAttribute("cmtForm") Comment comment, Model model) {
		log.info("submit comment/update comment");
		model.addAttribute("comment", commentService.saveComment(comment));
		return "views/web/blog-single";
	}

	@RequestMapping(value = "/{id}/reply", method = RequestMethod.POST)
	public String submitAddOrUpdateReply(@ModelAttribute("replyForm") Comment reply, @PathVariable("id") Integer id,
			Model model) {
		log.info("submit reply/update reply" + reply.getId());
		Comment oldComment = commentService.findById(id);
		model.addAttribute("replies", model.addAttribute("comment", commentService.saveReply(reply, id, oldComment)));

		Comment comment = new Comment();
		model.addAttribute("cmtForm", comment);

		Comment newReply = new Comment();
		model.addAttribute("comment", oldComment);
		model.addAttribute("replyForm", newReply);

		return "views/web/blog-single";

	}
}