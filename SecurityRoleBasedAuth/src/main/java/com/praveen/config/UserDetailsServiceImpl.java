package com.praveen.config;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.praveen.model.User;
import com.praveen.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		}
		UserDetailsImpl userDetails = new UserDetailsImpl(user);

		List<SimpleGrantedAuthority> authorities = user.getRole().getPrivilleges().stream()
				.map(priv -> new SimpleGrantedAuthority(priv.getName())).collect(Collectors.toList());
		authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName()));
		userDetails.setAuthorities(authorities);
		return userDetails;
	}

}