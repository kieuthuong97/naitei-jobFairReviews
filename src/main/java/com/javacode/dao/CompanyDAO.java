package com.javacode.dao;

import java.util.List;
import com.javacode.entities.Company;

public interface CompanyDAO extends BaseDAO<Integer, Company> {

	List<Company> findAll();

}
