package org.bashemera.openfarm.controller;

import org.bashemera.openfarm.service.SetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InstallController {
	
	@Autowired
	private SetupService setupService;

	@RequestMapping(value = "/install", method = RequestMethod.GET)
	public String install(Model model) {
		
		if (!setupService.isSetup()) {
		
			model.addAttribute("title", "Installing");
			return "system/install";
		}
		
		System.out.println("Already installed");
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/install", method = RequestMethod.POST)
	public String installComplete(@RequestParam(value = "installMessage", required = true) String installMessage, Model model) {
		
		
		if (installMessage.equalsIgnoreCase("openfarm/install")) {
			System.out.println(installMessage);
			
			return "system/complete_install";
		}
		
		model.addAttribute("errorInstall", "Incorrect install message passed");
		
		return "system/install";
	}
}
