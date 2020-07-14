package org.bashemera.openfarm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.bashemera.openfarm.model.Config;
import org.bashemera.openfarm.model.User;
import org.bashemera.openfarm.service.ConfigService;
import org.bashemera.openfarm.service.LoggerService;
import org.bashemera.openfarm.service.security.OpenFarmUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	@Autowired
	private OpenFarmUserDetailsService userService;
	
	@Autowired
	LoggerService loggerService;
	
	@Autowired
	private ConfigService configService;
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout, HttpServletRequest request) {
		
		/*
		 * Config config = configService.getConfig();
		 * 
		 * if (config == null) {
		 * 
		 * return "redirect:/install"; }
		 */
		
		model.addAttribute("title", "Please sign in");
		
		if (error != null) {
			loggerService.warn("Failed login attempt from: " + request.getRemoteAddr(), LoginController.class);
			model.addAttribute("errorMsg", "Your username and password are invalid.");
		}

        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");

		
	    return "login";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView signup() {
	    ModelAndView modelAndView = new ModelAndView();
	    User user = new User();
	    modelAndView.addObject("user", user);
	    modelAndView.setViewName("signup");
	    return modelAndView;
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
	    ModelAndView modelAndView = new ModelAndView();
	    User userExists = userService.findUserByEmail(user.getEmail());
	    if (userExists != null) {
	        bindingResult
	                .rejectValue("email", "error.user",
	                        "There is already a user registered with the username provided");
	    }
	    if (bindingResult.hasErrors()) {
	        modelAndView.setViewName("signup");
	    } else {
	        userService.saveUser(user);
	        modelAndView.addObject("successMessage", "User has been registered successfully");
	        modelAndView.addObject("user", new User());
	        modelAndView.setViewName("login");

	    }
	    return modelAndView;
	}
}
