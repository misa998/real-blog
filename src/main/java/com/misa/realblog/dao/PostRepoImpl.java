package com.misa.realblog.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.misa.realblog.entity.Post;
import com.misa.realblog.entity.User;

public abstract class PostRepoImpl implements PostRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public Post findByTitle(String title){
		Session session = em.unwrap(Session.class);
		Query<Post> query = session.createQuery("from Post where title=:title", Post.class);
		query.setParameter("title", title);
		Post post = null;
		try {
			post = query.getSingleResult();
		} catch (Exception e) {
			post = null;
		}

		return post;
	}

}