package com.misa.realblog.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.misa.realblog.entity.User;
import com.misa.realblog.entity.UserDetails;
import com.misa.realblog.registration.UserDTO;

@Repository
public class UserDAOImpl implements UserDAO {
	
	private EntityManager entityManager;
	private BCryptPasswordEncoder passEncoder;
	
	@Autowired
	public UserDAOImpl(EntityManager entityManager, BCryptPasswordEncoder passEncoder) {
		this.entityManager = entityManager;
		this.passEncoder = passEncoder;
	}

	@Override
	public User saveOrUpdate(UserDTO userDTO) {
		System.out.println(userDTO);
		Session session = entityManager.unwrap(Session.class);
		User user2 = findByEmail(userDTO.getEmail());
		if(StringUtils.isEmpty(user2)) {
			User user = new User();
			user.setUserDetails(new UserDetails("None", "None", 150));
			user.setEmail(userDTO.getEmail());
			user.setUserName(userDTO.getUserName());
			String pass = passEncoder.encode(userDTO.getPassword());
			user.setPassword(pass);
					
			System.out.println(user);
			session.saveOrUpdate(user);
			
			return user;
		}else {
			throw new UserAlreadyExistException("Already exist");
		}
	}

	@Override
	public List<User> findAll() {
		Session session = entityManager.unwrap(Session.class);
		Query<User> query = session.createQuery("from User", User.class);
		List<User> users = query.getResultList();
		
		return users;
	}

	@Override
	public User findById(int id) {
		Session session = entityManager.unwrap(Session.class);
		User user = session.get(User.class, id);
		return user;
	}

	@Override
	public User findByEmail(String email) {
		Session session = entityManager.unwrap(Session.class);
		Query<User> query = session.createQuery("from User where email=:email", User.class);
		query.setParameter("email", email);
		User user = new User();
		try {
			user = query.getSingleResult();
		} catch (Exception e) {
			user = null;
		}

		return user;
	}

	@Override
	public void deleteById(int id) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("delete from User where id=:userId");
		query.setParameter("userId", id);
		query.executeUpdate();
	}

}
