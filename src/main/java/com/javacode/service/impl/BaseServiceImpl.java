package com.javacode.service.impl;

import com.javacode.dao.JobDAO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseServiceImpl {
	protected JobDAO jobDAO;

}