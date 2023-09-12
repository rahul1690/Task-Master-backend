package com.freework.taskmaster.serviceImpl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.freework.taskmaster.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Service
public class UserDetailsImpl implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserEntity userEntity;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities;
		authorities = Arrays.stream(userEntity.getRole().getRoleName().split(","))
				.map(SimpleGrantedAuthority :: new)
				.collect(Collectors.toList());
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return userEntity.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userEntity.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
