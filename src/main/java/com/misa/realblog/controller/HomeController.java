package com.misa.realblog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.misa.realblog.dao.UserAlreadyExistException;
import com.misa.realblog.dao.UserRepository;
import com.misa.realblog.dao.UserRepositoryImpl;
import com.misa.realblog.entity.User;
import com.misa.realblog.registration.UserDTO;

@Controller
public class HomeController {
	@Autowired
	private UserRepositoryImpl ur;
		
	@GetMapping("/")
	public String homePage(Model model) {
		List<User> users = ur.findAll();
//		User user = ur.findByLastnameOrFirstname("john@luv2code.com", "john");
//		System.out.println(user.getUserName() + user.getEmail());
		model.addAttribute("users", users);
		return "index";
	}
	
	@GetMapping("/registration")
	public String showRegistrationPage(WebRequest wr, Model model) {
		final UserDTO userDTO = new UserDTO();
		model.addAttribute("user", userDTO);
		return "registration2";
	}
	
	@PostMapping("/registration")
	public ModelAndView registerUserAccount(
			@ModelAttribute("user") @Valid UserDTO userDTO, 
			HttpServletRequest request, Errors errors) {
		System.out.println("user name is " + userDTO.getUserName());
		
		try {
			final User registeredUser = ur.registerNewUserAccount(userDTO);
		} catch (final UserAlreadyExistException e) {
			ModelAndView mav = new ModelAndView("registration", "user", userDTO);
			mav.addObject("message", "An account for that username/email already exists");
			return mav;
		}
		return new ModelAndView("success", "user", userDTO);
	}
}
