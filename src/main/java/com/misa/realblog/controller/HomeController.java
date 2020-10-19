package com.misa.realblog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.misa.realblog.dao.UserAlreadyExistException;
import com.misa.realblog.entity.Post;
import com.misa.realblog.entity.User;
import com.misa.realblog.registration.UserDTO;
import com.misa.realblog.service.PostService;
import com.misa.realblog.service.UserService;

@Controller
public class HomeController {	
	private UserService userService;
	private PostService postService;
	
	@Autowired
	public HomeController(UserService userService, PostService postService) {
		this.userService = userService;
		this.postService = postService;
	}
		
	@GetMapping("/allUsers")
	public String homePage(Model model) {
		List<User> users = userService.findAll();
		
		model.addAttribute("users", users);
		return "index";
	}
	
	@GetMapping("/allPosts")
	public String postsPage(Model model) {
		List<Post> posts = postService.findAll();
		
		model.addAttribute("posts", posts);
		return "indexPosts";
	}


	@GetMapping("/registration")
	public String showRegistrationPage(WebRequest wr, Model model) {
		final UserDTO userDTO = new UserDTO();
		model.addAttribute("user", userDTO);
		return "registration";
	}
	

	@PostMapping("/registration")
	public String showSuccess(@ModelAttribute("user") @Valid UserDTO user, BindingResult br) {
		try {
			User registeredUser = userService.saveOrUpdate(user);
		} catch (final UserAlreadyExistException e) {
//			ModelAndView mav = new ModelAndView("registration", "user", userDTO);
//			mav.addObject("message", "An account for that username/email already exists");
//			return mav;
			br.rejectValue("email", null, "There is already an account registered with that email");
		       
		}
		
		if (br.hasErrors()){
            return "registration";
        }
		
		return "redirect:registration?success";
	}
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		return "login";
	}
}
