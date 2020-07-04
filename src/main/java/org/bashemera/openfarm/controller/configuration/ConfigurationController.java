package org.bashemera.openfarm.controller.configuration;

import javax.validation.Valid;

import org.bashemera.openfarm.model.Config;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ConfigurationController {
	
	@RequestMapping(value = "/admin/config/user", method = RequestMethod.GET)
	public String userConfiguration() {
		
		
		return "configuration/config";
	}
	
	@RequestMapping(value = "/admin/config/user", method = RequestMethod.POST)
    public String saveUserConfiguration(@Valid @ModelAttribute("config") Config config, BindingResult bindingResult, Model model) {
		
		if (!bindingResult.hasErrors()) {
			//save configurations
			
			return "configuration/user_config";
		}
		
		return "configuration/user_config";
		
	}
	
	@RequestMapping(value = "/admin/system/config", method = RequestMethod.GET)
	public String systemConfiguration() {
		
		//check user has admin role
		
		return "configuration/config";
	}
	
	@RequestMapping(value = "/admin/system/config", method = RequestMethod.POST)
    public String saveSystemConfiguration(@Valid @ModelAttribute("config") Config config, BindingResult bindingResult, Model model) {
		
		//check user has admin role and redirect to 403 page
		
		if (!bindingResult.hasErrors()) {
			//save configurations
			
			return "configuration/system_config";
		}
		
		return "configuration/system_config";
		
	}
}
