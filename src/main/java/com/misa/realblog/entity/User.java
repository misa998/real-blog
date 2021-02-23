package com.misa.realblog.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="username")
	private String userName;
	@Column(name="password")
	private String password;
	@Column(name="email")
	private String email;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_details_id")
	private UserDetails userDetails;
	
	@OneToMany(mappedBy="user", cascade= {CascadeType.DETACH, 
			CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH,})
	private List<Post> posts;
	
	@Enumerated(EnumType.STRING)
	@OneToMany(mappedBy="username", cascade= CascadeType.ALL)
	private Set<Authorities> authorities;

	public void addAuthorities(Authorities auth) {
		if(auth == null) {
			authorities = new HashSet<>();
		}
		if(auth != null) {
			auth.setUsername(this);
			authorities.add(auth);
		}
	}
	
	public Set<Authorities> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(Set<Authorities> authorities) {
		this.authorities = authorities;
	}
	
	public UserDetails getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void addPosts(Post post) {
		if(post == null) {
			posts = new ArrayList<>();
		}
		posts.add(post);
		post.setUser(this);
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", email=" + email
				+ ", userDetails=" + userDetails + ", posts=" + posts + "]";
	}
	
	public User(int id, String userName, String password, String email, UserDetails userDetails, List<Post> posts) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.userDetails = userDetails;
		this.posts = posts;
	}
	
	public User() {
		addAuthorities(null);
	}
	
	
}
