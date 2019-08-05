package com.javacode.dao;

import java.util.List;

import com.javacode.entities.Comment;

public interface CommentDAO extends BaseDAO<Integer, Comment> {
	
	List<Comment> findAll();
}
