package com.misa.realblog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.misa.realblog.entity.User;

@RepositoryRestResource(path="users") 
public interface UserRepository extends JpaRepository<User, Integer> {

}
