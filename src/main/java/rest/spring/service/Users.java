package rest.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import rest.spring.entity.User;
import rest.spring.repository.UserRepository;

@Service
public class Users implements UserDetailsService {
	
	@Autowired
	private UserRepository repo;

	@Autowired
	public Users(UserRepository repo) {
		this.repo = repo;
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = repo.findByName(username);
		if (user == null) {
			return null;
		}
		List<GrantedAuthority> auth = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		if (username.equals("admin")) {
			auth = AuthorityUtils
					.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
		}
		String password = user.getPassword();
		return new org.springframework.security.core.userdetails.User(username, password,
				auth);
	}

}

