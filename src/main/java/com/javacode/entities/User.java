package com.javacode.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor

public class User {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	@NotEmpty(message = "{user.name.notEmpty}")
	private String name;

	@NotEmpty(message = "{user.email.notEmpty}")
	@Email(message = "{user.email.email}")
	@Column(unique = true, name = "email")
	private String email;

	@Column(name = "password")
	@Size(message = "{pass.size}", min = 5, max = 30)
	private String password;
	
	@Column(name = "role")
	private int role;

	@OneToOne(mappedBy = "user")
	private Profile profile;

	@OneToMany(mappedBy = "user") // we need to duplicate the physical information
	private List<Comment> comments;

	@OneToMany(mappedBy = "user") // we need to duplicate the physical information
	private List<RegJob> regjobs;

	@OneToMany(mappedBy = "user") // ok
	private List<RatingJob> ratingjobs;
	
	public String getRoleString() {
		if (this.role == 1) {
			return "ADMIN";
		} else {
			return "USER";
		}
	}
	
	@Transient
	public List<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(this.getRoleString()));
		return authorities;
	}

}
