package org.bashemera.openfarm.controller;

import java.security.Principal;

import org.bashemera.openfarm.model.Farm;
import org.bashemera.openfarm.model.User;
import org.bashemera.openfarm.service.FarmService;
import org.bashemera.openfarm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccountController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FarmService farmService;

	@RequestMapping(value = "/account/create", method = RequestMethod.GET)
	public String createAccount() {
		
		
		return "account/create";
	}
	
	@RequestMapping(value = "/account/create", method = RequestMethod.POST)
	public String saveAccount() {
		
		
		return "account/create_result";
	}
	
	@RequestMapping(value = "/account/view", method = RequestMethod.GET)
	public String viewAccount(Principal principal, Model model) {
		User currentLoggedInUser = userService.findByEmail(principal.getName());
		
		Farm farm = farmService.findByEmployees(currentLoggedInUser.getId());
		
		model.addAttribute("farm", farm);
		model.addAttribute("title", "Account details");
		
		return "account/view";
	}
}
