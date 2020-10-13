package com.misa.realblog.registration;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@PasswordMatches
// data transfer object
public class UserDTO {
	
	@NotNull
	@Size(min = 1, message = "{Size.userDto.userName}")
	private String userName;
	
	@NotNull
	@Size(min = 1)
	private String password;
	private String matchingPasswords;
	
	@NotNull
	@Size(min = 1, message = "{Size.userDto.email}")
	@ValidEmail
	private String email;
	
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
	public String getMatchingPasswords() {
		return matchingPasswords;
	}
	public void setMatchingPasswords(String matchingPasswords) {
		this.matchingPasswords = matchingPasswords;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
