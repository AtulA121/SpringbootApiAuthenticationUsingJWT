package com.springbootApiAuthenticationUsingJWT.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springbootApiAuthenticationUsingJWT.model.DAOUser;
import com.springbootApiAuthenticationUsingJWT.model.UserDoa;
import com.springbootApiAuthenticationUsingJWT.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDoa userObjj=userRepository.findByUserName(username);
		
		System.out.println("----- : "+username+" \n"+userObjj);
		if(userObjj==null)
		{
			throw new UsernameNotFoundException("User not found...");
		}
	
		return new User(userObjj.getUserName(),userObjj.getUserPassword(),
					new ArrayList<>());
	}
	
	public UserDoa save(DAOUser userObj)
	{
		UserDoa newUser=new UserDoa();
		newUser.setUserName(userObj.getUserName());
		newUser.setUserPassword(bcryptEncoder.encode(userObj.getUserPassword()));
		return userRepository.save(newUser);
	}
	
	public List<UserDoa> getAllUsers()
	{
		List<UserDoa> list=(List<UserDoa>) userRepository.findAll();
		return list;
	}
	
}