package com.javacode.service;

import java.util.List;

import com.javacode.entities.*;

public interface CompanyService extends BaseService<Integer, Company> {
	boolean deleteCompany(Integer id);
	
	List<Company> findAll();
}
