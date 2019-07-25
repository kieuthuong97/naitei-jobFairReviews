package com.javacode.service;

import java.util.List;

import com.javacode.entities.User;

public interface UserService extends BaseService<Integer, User> {

	boolean deleteUser(Integer id);

	List<User> findAll();

	List<User> searchUsers(String name, String email);

}
