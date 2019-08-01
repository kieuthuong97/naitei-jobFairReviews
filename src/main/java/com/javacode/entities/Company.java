package com.javacode.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "companies")
@Setter
@Getter
@NoArgsConstructor

public class Company {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "{company.name.notNull}")
	@Column(name = "companyName")
	private String name;

	@NotEmpty(message = "{company.address.notNull}")
	@Column(name = "address")
	private String address;

	@NotEmpty(message = "{company.description.notNull}")
	@Column(name = "companyDescription")
	private String description;

	@OneToMany(mappedBy = "company") // we need to duplicate the physical information
	private List<Job> jobs;

}
