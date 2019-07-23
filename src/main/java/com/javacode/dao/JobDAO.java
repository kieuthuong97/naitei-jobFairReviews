package com.javacode.dao;

import java.util.List;

import com.javacode.entities.Job;

public interface JobDAO extends BaseDAO<Integer, Job> {

	List<Job> findAll();

}
