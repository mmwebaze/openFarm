package org.bashemera.openfarm.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.bashemera.openfarm.exception.AccountCreationException;
import org.bashemera.openfarm.form.AccountForm;
import org.bashemera.openfarm.model.Farm;
import org.bashemera.openfarm.model.Role;
import org.bashemera.openfarm.model.User;
import org.bashemera.openfarm.service.AccountService;
import org.bashemera.openfarm.service.FarmService;
import org.bashemera.openfarm.service.RoleService;
import org.bashemera.openfarm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccountController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FarmService farmService;
	
	//@Autowired
	//private RoleService roleService;
	
	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/account/create", method = RequestMethod.GET)
	public String createAccount(Model model) {
		
		AccountForm account = new AccountForm();
		model.addAttribute("title", "Create account");
		model.addAttribute("account", account);
		
		
		return "account/create";
	}
	
	@RequestMapping(value = "/account/create", method = RequestMethod.POST)
	public String saveAccount(@Valid @ModelAttribute("account") AccountForm account, BindingResult bindingResult,  Model model) {
		
		model.addAttribute("title", "Account details");
		
		System.out.println("Posting new account");
		
		  if (bindingResult.hasErrors()) {
		  
			  model.addAttribute("title", "Account details");
			  return "account/create"; 
		  }
		  
		  try {
			accountService.createAccount(account);
			return "account/create_result";
		} catch (AccountCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return "account/create_error";
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
