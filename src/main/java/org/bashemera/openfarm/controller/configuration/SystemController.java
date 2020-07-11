package org.bashemera.openfarm.controller.configuration;

import java.security.Principal;

import javax.validation.Valid;

import org.bashemera.openfarm.model.Config;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SystemController {
	
	@RequestMapping(value = "/system/access_denied", method = RequestMethod.GET)
	public String farmConfiguration() {
		
		
		return "system/access_denied";
	}
	
	/*@RequestMapping(value = "/admin/config/user", method = RequestMethod.POST)
    public String saveFarmConfiguration(@Valid @ModelAttribute("config") Config config, BindingResult bindingResult, Model model) {
		
		if (!bindingResult.hasErrors()) {
			//save configurations
			
			return "configuration/farm_config";
		}
		
		return "configuration/farm_config";
		
	}*/
	
	@RequestMapping(value = "/admin/system/config", method = RequestMethod.GET)
	public String systemConfiguration(Principal principal, Model model) {
		
		//check user has admin role
		
		return "system/view";
	}
	
	@RequestMapping(value = "/admin/system/config", method = RequestMethod.POST)
    public String saveSystemConfiguration(@Valid @ModelAttribute("config") Config config, BindingResult bindingResult, Model model) {
		
		//check user has admin role and redirect to 403 page
		
		if (!bindingResult.hasErrors()) {
			//save configurations
			
			return "system/edit";
		}
		
		return "configuration/system_config";
		
	}
}
