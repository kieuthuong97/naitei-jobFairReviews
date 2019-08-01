package com.javacode.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;

import com.javacode.entities.Company;
import com.javacode.service.CompanyService;

public class CompanyServiceImpl extends BaseServiceImpl implements CompanyService {
	private static final Logger logger = Logger.getLogger(CompanyServiceImpl.class);

	@Override
	public Company saveOrUpdate(Company entity) {
		try {
			return getCompanyDAO().saveOrUpdate(entity);
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}
	
	@Override
	public boolean deleteCompany(Integer id) {
		try {
			Company Company = getCompanyDAO().findById(id);
			return delete(Company);
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public List<Company> findAll() {
		try {
			return getCompanyDAO().findAll();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Company findById(Serializable key) {
		try {
			return getCompanyDAO().findById(key);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean delete(Company entity) {
		try {
			getCompanyDAO().delete(entity);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

}
