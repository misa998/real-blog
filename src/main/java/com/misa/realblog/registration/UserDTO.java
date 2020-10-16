package com.misa.realblog.registration;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@FieldMatch.List({
    @FieldMatch(first = "password", second = "matchingPasswords", message = "The password fields must match")
})
@PasswordMatches
// data transfer object
public class UserDTO {
	
//	@Size(min = 1, message = "{Size.userDto.userName}")
	@NotEmpty
	private String userName;
	
	@NotEmpty
//	@Size(min = 1)
	private String password;
	@NotEmpty
	private String matchingPasswords;
	
//	@NotEmpty
	@ValidEmail
//	@Email
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
	@Override
	public String toString() {
		return "UserDTO [userName=" + userName + ", password=" + password + ", matchingPasswords=" + matchingPasswords
				+ ", email=" + email + "]";
	}
}