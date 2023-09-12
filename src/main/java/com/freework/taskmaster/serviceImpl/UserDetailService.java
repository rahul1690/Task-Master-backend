package com.freework.taskmaster.serviceImpl;

import static com.freework.taskmaster.service.ResponseMessages.INVALID_USERNAME;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.freework.taskmaster.exceptions.UserNotFoundException;
import com.freework.taskmaster.repository.UserRepository;

@Service
public class UserDetailService implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetailsImpl userDetailsImpl = new UserDetailsImpl(userRepository.findByUsername(username).orElseThrow(
				()-> new UserNotFoundException(INVALID_USERNAME)));
		return userDetailsImpl;
	}
}
