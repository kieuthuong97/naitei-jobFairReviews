package com.javacode.service;

import java.util.List;

import com.javacode.entities.*;

public interface JobService extends BaseService<Integer, Job> {

	List<Job> findAll();
	
	boolean deleteJob (Integer id);
}
