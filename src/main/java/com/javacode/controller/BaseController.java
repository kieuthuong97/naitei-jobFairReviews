package com.javacode.controller;

import org.codehaus.groovy.tools.shell.util.MessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class BaseController {
	@Autowired
	@Lazy
	protected MessageSource messageSource;
}
