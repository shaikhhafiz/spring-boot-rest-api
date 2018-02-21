package rest.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import rest.spring.service.Users;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Users users;

	// Authentication : User --> Roles
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/**
		 * Inmemory authentication
		 * auth.inMemoryAuthentication().withUser("hafiz").password("12345").roles("USER").and().withUser("shaikh")
		 * .password("12345").roles("USER", "ADMIN");
		 */
		auth.userDetailsService(users);
	}

	// Authorization : Role -> Access 
	protected void configure(HttpSecurity http)
	  throws Exception {
	  http.httpBasic().and().authorizeRequests().
	  antMatchers("/employess/**","/managers/**").hasRole("USER").
	  and().csrf().disable().headers().frameOptions().disable();
	}
	 
}
