package com.SaltOverflow.SpringUserWebsite.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.SaltOverflow.SpringUserWebsite.model.User;
import com.SaltOverflow.SpringUserWebsite.service.UserService;
import com.SaltOverflow.SpringUserWebsite.validator.UserValidator;

@Controller
public class UserController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserService userService;
	@Autowired
	UserValidator userValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(userValidator);
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home(Model model) {
		logger.debug("home()");
		return "redirect:/users";
	}
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public String listUsers(Model model) {
		logger.debug("listUsers()");
		
		model.addAttribute("users", userService.getAllUsers());
		return "users/list";
	}
	
	@RequestMapping(value="/users/{id}", method=RequestMethod.GET)
	public String showUser(@PathVariable("id") int id, Model model) {
		logger.debug("showUser() id={}", id);
		
		User user = userService.getUser(id);
		if (user == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "User not found");
			user = new User();
		}
		model.addAttribute("user", user);
		return "users/user";
	}
	
	@RequestMapping(value="/users/edit", method=RequestMethod.GET)
	public String editUser(Model model) {
		logger.debug("editUser()");
		
		User user = new User();
		model.addAttribute("user", user);
		return "users/edituser";
	}
	
	@RequestMapping(value="/users/edit/{id}", method=RequestMethod.GET)
	public String editUser(@PathVariable("id") int id, Model model) {
		logger.debug("editUser() id={}", id);
		
		User user = userService.getUser(id);
		if (user == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "User not found");
			user = new User();
		}
		model.addAttribute("user", user);
		return "users/edituser";
	}
	
	@RequestMapping(value="/users/save", method=RequestMethod.POST)
	public String saveOrUpdateUser(@ModelAttribute("user") @Validated User user, BindingResult bindingResult, Model model, final RedirectAttributes redirectAttributes) {
		logger.debug("saveOrUpdateUser() : {}", user);
		
		if (bindingResult.hasErrors()) {
			return "users/edituser";
		} else {
			redirectAttributes.addFlashAttribute("css", "success");
			if (user.isNew()) {
				redirectAttributes.addFlashAttribute("msg", "User has been added");
			} else {
				redirectAttributes.addFlashAttribute("msg", "User has been updated");
			}
		}
		
		userService.saveOrUpdateUser(user);
		return "redirect:/users/" + user.getId();
	}
	
	@RequestMapping(value="/users/delete/{id}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String deleteUser(@PathVariable("id") int id) {
		logger.debug("deleteUser() id={}", id);
		
		if (userService.deleteUser(id)) {
			return "{\"success\":"+1+"}";
		} else {
			return "{\"success\":"+0+",\"msg\":\"No user deleted: no user found\"}";
		}
	}
}
