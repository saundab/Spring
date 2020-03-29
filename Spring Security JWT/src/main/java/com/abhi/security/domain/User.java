package com.abhi.security.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="USERS")
@Data
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="USER_NAME")
	private String userName;
	private String password;
	private boolean active;
	
	//comma separated roles like ROLE_ADMIN,ROLE_MANAGERS,ROLE_DEVELOPERS
	private String roles;
	
}
