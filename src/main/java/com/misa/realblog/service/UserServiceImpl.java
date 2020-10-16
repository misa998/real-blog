package com.misa.realblog.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.misa.realblog.dao.UserDAO;
import com.misa.realblog.entity.User;
import com.misa.realblog.registration.UserDTO;

@Service
public class UserServiceImpl implements UserService {
	
	private UserDAO userDAO;
	
	@Autowired
	public UserServiceImpl(@Qualifier("userDAOImpl") UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Transactional
	@Override
	public User saveOrUpdate(UserDTO userDTO) {
		return userDAO.saveOrUpdate(userDTO);
	}
	
	@Transactional
	@Override
	public List<User> findAll() {
		return userDAO.findAll();
	}
	
	@Transactional
	@Override
	public User findById(int id) {
		return userDAO.findById(id);
	}
	
	@Transactional
	@Override
	public User findByEmail(String email) {
		return userDAO.findByEmail(email);
	}
	
	@Transactional
	@Override
	public void deleteById(int id) {
		userDAO.deleteById(id);
	}

}
