package com.javacode.service.impl;

import com.javacode.dao.CommentDAO;
import com.javacode.dao.CompanyDAO;
import com.javacode.dao.JobDAO;
import com.javacode.dao.UserDAO;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class BaseServiceImpl {
	protected JobDAO jobDAO;
	protected CompanyDAO companyDAO;
	protected UserDAO userDAO;
	protected CommentDAO commentDAO;
}