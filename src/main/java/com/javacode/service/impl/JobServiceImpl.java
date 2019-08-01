package com.javacode.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;

import com.javacode.entities.Job;
import com.javacode.service.JobService;

public class JobServiceImpl extends BaseServiceImpl implements JobService {
	private static final Logger logger = Logger.getLogger(JobServiceImpl.class);

	@Override
	public Job saveOrUpdate(Job entity) {
		try {
			return getJobDAO().saveOrUpdate(entity);
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public Job findById(Serializable key) {
		try {
			return getJobDAO().findById(key);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean delete(Job entity) {
		try {
			getJobDAO().delete(entity);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Job> findAll() {
		try {
			return getJobDAO().findAll();
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}
	
	@Override
	public boolean deleteJob(Integer id) {
		try {
			Job job = getJobDAO().findById(id);
			return delete(job);
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}
}
