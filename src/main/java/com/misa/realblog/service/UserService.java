package com.misa.realblog.service;

import java.util.List;

import com.misa.realblog.entity.User;
import com.misa.realblog.registration.UserDTO;

public interface UserService {
	
	User saveOrUpdate(UserDTO userDTO);
	List<User> findAll();
	User findById(int id);
	User findByEmail(String email);
	void deleteById(int id);
}