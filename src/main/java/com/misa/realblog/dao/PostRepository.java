package com.misa.realblog.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.misa.realblog.entity.Post;

@RepositoryRestResource(path="posts")
public interface PostRepository extends JpaRepository<Post, Integer> {

}
