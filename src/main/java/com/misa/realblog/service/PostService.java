package com.misa.realblog.service;

import java.util.List;

import com.misa.realblog.entity.Post;
import com.misa.realblog.entity.User;

public interface PostService {
	
	Post saveOrUpdate(Post post);
	List<Post> findAll();
	Post findById(int id);
	Post findByTitle(String title);
	List<Post> findByUser(User user);
	void deleteById(int id);

}
