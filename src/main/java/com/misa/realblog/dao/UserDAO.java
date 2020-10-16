package com.misa.realblog.dao;

import java.util.List;

import com.misa.realblog.entity.User;
import com.misa.realblog.registration.UserDTO;

public interface UserDAO {

	User saveOrUpdate(UserDTO userDTO);

	List<User> findAll();

	User findById(int id);

	User findByEmail(String email);

	void deleteById(int id);

}
