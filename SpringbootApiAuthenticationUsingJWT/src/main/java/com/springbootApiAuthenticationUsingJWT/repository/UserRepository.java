package com.springbootApiAuthenticationUsingJWT.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springbootApiAuthenticationUsingJWT.model.UserDoa;

@Repository
public interface UserRepository extends CrudRepository<UserDoa,Integer> {

	UserDoa findByUserName(String username);
	
}
