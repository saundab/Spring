package com.abhi.security.service;

import java.util.Collections;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.ldap.LdapAuthenticationProviderConfigurer.PasswordCompareConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	/* #1 In Memory H2 database authentication provider */
	
	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("user")
			.password("user")
			.roles("USER")
			.and()
			.withUser("admin")
			.password("admin")
			.roles("USER","ADMIN");
	}*/
	
	
	/* #2 JDBC authentication provider  */
	/*@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("SELECT username, password, enabled from users where username = ?")
			.authoritiesByUsernameQuery("SELECT username, authority from authorities where username = ?");
	}*/
	
	/*JPA authentication provider */
	/*	
 	@Autowired
	MyUserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	*/

	//LDAP authentication provide */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		/*
		 * working code
		 */
		auth.ldapAuthentication()
			.userSearchBase("ou=people")
			.userSearchFilter("uid={0}")
			
			.groupSearchBase("ou=groups")
			.groupSearchFilter("(uniqueMember={0})")
			.groupRoleAttribute("cn")
	    .contextSource()
	        .url("ldap://localhost:389/dc=example,dc=org")
	        .managerDn("cn=admin,dc=example,dc=org")
	        .managerPassword("admin")
	    .and()
			.passwordCompare()
			.passwordAttribute("userPassword");
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        .authorizeRequests()
            .antMatchers("/managers").hasRole("MANAGERS")
            .antMatchers("/developers").hasRole("DEVELOPERS")
            .anyRequest().fullyAuthenticated()
        .and()
            .formLogin();
	}
	
	
	/* for passwords like $2a$10$D */
	/*@Bean
	public PasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder;
	}*/
	
}
