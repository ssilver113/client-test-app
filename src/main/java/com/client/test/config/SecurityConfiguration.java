package com.client.test.config;

import com.client.test.service.CompanyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests().antMatchers("/h2-console/**").permitAll()
				.and()
				.authorizeRequests().anyRequest().authenticated()
				.and()
				.formLogin()
				.and()
				.httpBasic();

		http.csrf().disable();
		http.headers().frameOptions().disable();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new CompanyUserDetailsService();
	}

	@Autowired
	public void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService());
	}

	/* Note: In reality we would use some kind of password encoding */
	@SuppressWarnings("deprecation")
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
}