package com.javacode.dao.impl;

import java.util.List;

import com.javacode.dao.GenericDAO;
import com.javacode.dao.UserDAO;
import com.javacode.entities.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserDAOImpl extends GenericDAO<Integer, User> implements UserDAO {
	public UserDAOImpl() {
		super(User.class);
	}

	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		return getSession().createQuery("from User").getResultList();
	}

	@Override
	public User findByEmail(String email) {
		logger.info("email: " + email);
		return (User) getSession().createQuery("FROM User where email = :email").setParameter("email", email).getResultList().stream().findFirst().orElse(null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> searchUsers(String name, String email) {
		logger.info("name: " + name);
		logger.info("email: " + email);
		String sql = "FROM User WHERE email LIKE :name and name LIKE :email";
		return getSession().createQuery(sql).setParameter("name", '%' + name + '%')
				.setParameter("email", '%' + email + '%').getResultList();
	}

}