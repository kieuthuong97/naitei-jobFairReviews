package com.javacode.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import com.javacode.dao.CompanyDAO;
import com.javacode.dao.GenericDAO;
import com.javacode.entities.Company;

public class CompanyDAOImpl extends GenericDAO<Integer, Company> implements CompanyDAO {
	public CompanyDAOImpl() {
		super(Company.class);
	}

	public CompanyDAOImpl(SessionFactory sessionfactory) {
		setSessionFactory(sessionfactory);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Company> findAll() {
		return getSession().createQuery("from Company").getResultList();
	}

}
