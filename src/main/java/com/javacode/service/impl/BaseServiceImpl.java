package com.javacode.service.impl;

import com.javacode.dao.JobDAO;

public class BaseServiceImpl {
	protected JobDAO jobDAO;

	public JobDAO getJobDAO() {
		return jobDAO;
	}

	public void setJobDAO(JobDAO jobDAO) {
		this.jobDAO = jobDAO;
	}
}