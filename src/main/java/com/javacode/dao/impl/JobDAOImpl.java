package com.javacode.dao.impl;

import java.util.List;

import com.javacode.dao.GenericDAO;
import com.javacode.dao.JobDAO;
import com.javacode.entities.Job;

public class JobDAOImpl extends GenericDAO<Integer, Job> implements JobDAO {
	public JobDAOImpl() {
		super(Job.class);
	}

	@SuppressWarnings("unchecked")
	public List<Job> findAll() {
		return getSession().createQuery("from Job").getResultList();
	}
}
