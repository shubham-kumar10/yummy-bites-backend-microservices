package com.cognizant.menuitemservice.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cognizant.menuitemservice.exception.UserAlreadyExistsException;
import com.cognizant.menuitemservice.model.AppUser;
import com.cognizant.menuitemservice.model.Role;
import com.cognizant.menuitemservice.model.USER;
import com.cognizant.menuitemservice.repository.RoleRepository;
import com.cognizant.menuitemservice.repository.UserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppUserDetailsService.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		USER user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("USER not found!");
		} else {
			LOGGER.info("user is:" + user);
			AppUser appUser = new AppUser(user);
			LOGGER.info("userDetails is: " + appUser);
			return appUser;
		}

	}

	public AppUserDetailsService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public void signup(USER newUser) throws UserAlreadyExistsException {
		LOGGER.info("NEW USER IS: " + newUser);
		USER user = userRepository.findByUsername(newUser.getUsername());
		if (user != null) {
			throw new UserAlreadyExistsException();
		} else {
			Role role = roleRepository.findById(1).get();
			LOGGER.info("NEW ROLE IS: " + role);
			List<Role> roles = new ArrayList<Role>();
			roles.add(role);
			newUser.setRoles(roles);
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = bCryptPasswordEncoder.encode(newUser.getPassword());
			newUser.setPassword(encodedPassword);
			userRepository.save(newUser);
		}

	}

}
