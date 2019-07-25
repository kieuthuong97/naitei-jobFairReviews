package com.javacode.dao;

import java.util.List;

import com.javacode.entities.User;

public interface UserDAO extends BaseDAO<Integer, User> {

	List<User> findAll();

	List<User> searchUsers(String name, String email);

}
