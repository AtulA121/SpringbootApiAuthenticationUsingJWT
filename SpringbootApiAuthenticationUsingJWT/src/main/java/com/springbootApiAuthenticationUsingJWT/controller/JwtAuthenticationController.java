package com.springbootApiAuthenticationUsingJWT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springbootApiAuthenticationUsingJWT.config.JwtUtil;
import com.springbootApiAuthenticationUsingJWT.model.DAOUser;
import com.springbootApiAuthenticationUsingJWT.model.JwtRequest;
import com.springbootApiAuthenticationUsingJWT.model.JwtResponse;
import com.springbootApiAuthenticationUsingJWT.service.JwtUserDetailsService;

@RestController
@CrossOrigin("*")
public class JwtAuthenticationController {

	@Value("${jwt.a121}")
	String aim;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value="/home",method=RequestMethod.GET)
	public ResponseEntity<?> home()
	{
		System.out.println("/ get request..."+aim);
		System.out.println(userDetailsService.getAllUsers());
		return ResponseEntity.ok(userDetailsService.getAllUsers());
	}
	
	@RequestMapping(value="/home",method=RequestMethod.POST)
	public ResponseEntity<?> homePost()
	{
		System.out.println("/ post request..."+aim);
		return ResponseEntity.ok(userDetailsService.getAllUsers());
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		System.out.println("+++++ token is : "+token);
		
		return ResponseEntity.ok(new JwtResponse(token));
	}

	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody DAOUser doaUser) throws Exception
	{
		System.out.println("/register request..."+aim);
		
		return ResponseEntity.ok(userDetailsService.save(doaUser));
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}