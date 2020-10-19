package com.misa.realblog.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.misa.realblog.dao.PostDAO;
import com.misa.realblog.entity.Post;
import com.misa.realblog.entity.User;

@Service
public class PostServiceImpl implements PostService {

	private PostDAO postDAO;
	
	@Autowired
	public PostServiceImpl(@Qualifier("postDAOImpl") PostDAO postDAO) {
		this.postDAO = postDAO;
	}

	@Override
	@Transactional
	public Post saveOrUpdate(Post post) {
		return postDAO.saveOrUpdate(post);
	}

	@Override
	@Transactional
	public List<Post> findAll() {
		return postDAO.findAll();
	}

	@Override
	@Transactional
	public Post findById(int id) {
		return postDAO.findById(id);
	}

	@Override
	@Transactional
	public Post findByTitle(String title) {
		return postDAO.findByTitle(title);
	}

	@Override
	@Transactional
	public List<Post> findByUser(User user) {
		return postDAO.findByUser(user);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		postDAO.deleteById(id);
	}

}
