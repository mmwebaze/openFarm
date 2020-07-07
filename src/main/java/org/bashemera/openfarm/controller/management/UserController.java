package org.bashemera.openfarm.controller.management;

import java.security.Principal;

import org.bashemera.openfarm.model.User;
import org.bashemera.openfarm.service.RoleService;
import org.bashemera.openfarm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
	
	/*
	 * @Autowired private UserRepository userRepository;
	 */
	@Autowired
	private UserService userService;
	//to be replaced by user service
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value = {"/user/{id}", "/user" }, method = RequestMethod.GET)
	public String profile(Principal principal, Model model, @PathVariable(name = "id", required = false) String id) {
		
		User currentLoggedInUser = userService.findByEmail(principal.getName());
		
		if (StringUtils.isEmpty(id)) {
	        id = "default";
	        System.out.println("Id "+id);
	        System.out.println(principal.getName());
	        model.addAttribute("user", currentLoggedInUser);
			model.addAttribute("roles", currentLoggedInUser.getRoles());
			
			return "management/user/profile";
	    }
		
		User user = userService.findById(id);
		
		if (user == null) {
			
			model.addAttribute("user", currentLoggedInUser);
			model.addAttribute("roles", currentLoggedInUser.getRoles());
			
			return "management/user/profile";
		}
		
		//start checking 
		if (roleService.hasRole(currentLoggedInUser, "ADMIN")){
			System.out.println("###################################");
			//User user = optionalEntity.get();
			model.addAttribute("user", user);
			model.addAttribute("roles", user.getRoles());
			
			return "management/user/profile";
		}
		//System.out.println(optionalEntity.isPresent());
		System.out.println("++++++++++++++++++++++++++++++++");
		//User user = optionalEntity.get();
		
		
		System.out.println("Id "+id);
		System.out.println(principal.getName());
		
		return "system/access_denied";
	}
	
	@RequestMapping(value = "/management/user/create", method = RequestMethod.GET)
	public String createUser(Model model) {
		
		//check for admin and user permissions before proceeding
		
		/*
		 * if (!bindingResult.hasErrors()) { //save profile
		 * 
		 * return "management/user/create"; }
		 */
		
		return "management/user/create";
	}
}
