package com.javacode.service.impl;

import java.io.Serializable;
import java.util.List;

import com.javacode.entities.Comment;
import com.javacode.entities.Job;
import com.javacode.entities.User;
import com.javacode.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommentServiceImpl extends BaseServiceImpl implements CommentService {
	@Override
	public Comment findById(Serializable key) {
		try {
			return getCommentDAO().findById(key);
		} catch (Exception e) {
			log.error("failed", e);
			return null;
		}
	}

	@Override
	public Comment saveOrUpdate(Comment entity) {
		try {
			return getCommentDAO().saveOrUpdate(entity);
		} catch (Exception e) {
			log.error("failed!", e);
			throw e;
		}
	}

	@Override
	public boolean delete(Comment entity) {
		try {
			getCommentDAO().delete(entity);
			return true;
		} catch (Exception e) {
			log.error("failed", e);
			throw e;
		}
	}

	@Override
	public boolean deleteComment(Integer id) {
		try {
			Comment comment = getCommentDAO().findById(id);
			return delete(comment);
		} catch (Exception e) {
			log.error("failed", e);
			throw e;
		}
	}

	@Override
	public List<Comment> findAll() {
		try {
			return getCommentDAO().findAll();
		} catch (Exception e) {
			log.error("failed", e);
			throw e;
		}
	}
	
	@Override
	public Comment saveComment(Comment comment, int idJob, User user) {
		try {
			Job job = getJobDAO().findById(idJob);
			comment.setJob(job);
			comment.setUser(user);
			return getCommentDAO().saveOrUpdate(comment);
		} catch (Exception e) {
			log.error("failed!", e);
			throw e;
		}
	}
	
	@Override
	public Comment saveReply(Comment reply, Integer id, Comment oldComment,int idJob, User user) {
		try {
			Job job = getJobDAO().findById(idJob);
			reply.setJob(job);
			reply.setUser(user);
			reply.setComment(oldComment);
			return getCommentDAO().saveOrUpdate(reply);
		} catch (Exception e) {
			log.error("failed!", e);
			throw e;
		}
		
	}

}
