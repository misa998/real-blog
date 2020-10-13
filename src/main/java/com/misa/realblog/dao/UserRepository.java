package com.misa.realblog.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.misa.realblog.entity.User;
import com.misa.realblog.registration.UserDTO;

@RepositoryRestResource(path="users") 
public interface UserRepository extends JpaRepository<User, Integer> {
	
	// native query can be used by defining @Query(value = "SELECT * ...", nativeQuery = true)
	
	/**
	* <S> S save(User user);
	* Optional<User> findById(int id);
	* void deleteById(int id);
	* List<User> findAll();
	**/
	
	@Query("select username from #{#entityName} username where username.email = ?1")
	User findByEmailAddress(String email);
	
	@Query("select id from #{#entityName} id where id.userName = :userName or id.email = :email")
	User findByLastnameOrFirstname(@Param("email") String email,
	                                 @Param("userName") String username);
	
//	User save(User user);
	
	User registerNewUserAccount(UserDTO userDto);
	 
	
//	@Query("SELECT f FROM Users f WHERE LOWER f.username = :username or f.email = :email")
//	User findByUsername(@Param("username") String username,
//			@Param("email") String email);
}
