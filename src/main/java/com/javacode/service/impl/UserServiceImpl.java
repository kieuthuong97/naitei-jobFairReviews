package com.javacode.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import com.javacode.dao.impl.UserDAOImpl;
import com.javacode.entities.User;
import com.javacode.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserServiceImpl extends BaseServiceImpl implements UserService {
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Override
	public User findById(Serializable key) {
		try {
			return getUserDAO().findById(key);
		} catch (Exception e) {
			log.error("failed", e);
			return null;
		}
	}
	
	@Override
	public User findByEmail(String email) {
		try {
			return getUserDAO().findByEmail(email);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public User saveOrUpdate(User entity) {
		try {
			return getUserDAO().saveOrUpdate(entity);
		} catch (Exception e) {
			log.error("failed!", e);
			throw e;
		}
	}

	@Override
	public boolean delete(User entity) {
		try {
			getUserDAO().delete(entity);
			return true;
		} catch (Exception e) {
			log.error("failed", e);
			throw e;
		}
	}

	@Override
	public List<User> findAll() {
		try {
			return getUserDAO().findAll();
		} catch (Exception e) {
			log.error("failed", e);
			throw e;
		}
	}

	@Override
	public boolean deleteUser(Integer id) {
		try {
			User User = getUserDAO().findById(id);
			return delete(User);
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public List<User> searchUsers(String name, String email) {
		try {
			return getUserDAO().searchUsers(name, email);
		} catch (Exception e) {
			return null;
		}
	}

}
