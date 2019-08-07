
package com.javacode.dao.impl;

import java.util.List;

import com.javacode.dao.CommentDAO;
import com.javacode.dao.GenericDAO;
import com.javacode.entities.Comment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommentDAOImpl extends GenericDAO<Integer, Comment> implements CommentDAO {
	public CommentDAOImpl() {
		super(Comment.class);
	}

	@SuppressWarnings("unchecked")
	public List<Comment> findAll() {
		return getSession().createQuery("from User").getResultList();
	}
}