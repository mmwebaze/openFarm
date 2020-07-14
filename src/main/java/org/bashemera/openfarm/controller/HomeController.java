package org.bashemera.openfarm.controller;


import org.bashemera.openfarm.model.Config;
import org.bashemera.openfarm.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {
	
	@Autowired
	private ConfigService configService;
	
	@RequestMapping(value = "/",  method = RequestMethod.GET)
	public String index(Model model) {
		
		model.addAttribute("title", "Bashemera");
		
		/*
		 * Config config = configService.getConfig();
		 * 
		 * if (config == null) {
		 * 
		 * return "redirect:/install"; }
		 */
		
		return "index";
	}

}
