package com.javacode.service;

import java.util.List;

import com.javacode.entities.Comment;
import com.javacode.entities.Job;
import com.javacode.entities.User;

public interface CommentService extends BaseService<Integer, Comment> {

	boolean deleteComment(Integer id);

	List<Comment> findAll();
	
	Comment saveReply(Comment reply,Integer id, Comment oldComment,int idJob, User user);
	
	Comment saveComment(Comment comment,int id_job, User user);
}
