package com.misa.realblog.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.misa.realblog.entity.Post;
import com.misa.realblog.entity.User;

@Repository
public class PostDAOImpl implements PostDAO {
	
	private EntityManager em;
	
	@Autowired
	public PostDAOImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public Post saveOrUpdate(Post post) {
		Session session = em.unwrap(Session.class);
		
		session.saveOrUpdate(post);
		return post;
	}

	@Override
	public List<Post> findAll() {
		Session session = em.unwrap(Session.class);
		
		Query<Post> query = session.createQuery("from Post", Post.class);
		List<Post> posts = query.getResultList();
		return posts;
	}

	@Override
	public Post findById(int id) {
		Session session = em.unwrap(Session.class);
		
		Post post = session.get(Post.class, id);
		return post;
		
	}

	@Override
	public Post findByTitle(String title) {
		Session session = em.unwrap(Session.class);
		
//		Query<Post> query = session.createQuery("from Post where title:=titleString", Post.class);
		Query<Post> query = session.createQuery("SELECT u FROM Post u WHERE  u.title = :title", Post.class);
		query.setParameter("title", title);
		Post post = query.getSingleResult();

		return post;
	}

	@Override
	public List<Post> findByUser(User user) {
		Session session = em.unwrap(Session.class);
		
		Query<Post> query = session.createQuery("SELECT u FROM Post u WHERE  u.user = :user", Post.class);
		query.setParameter("user", user);
		List<Post> posts = query.getResultList();
		return posts;
	}

	@Override
	public void deleteById(int id) {
		Session session = em.unwrap(Session.class);
		Query query = session.createQuery("delete from Post where id:=id");
		query.setParameter("id", id);
		query.executeUpdate();
	}

}
