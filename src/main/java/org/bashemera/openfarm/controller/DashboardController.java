package org.bashemera.openfarm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DashboardController {
	
	@RequestMapping(value = "/dashboard",  method = RequestMethod.GET)
	public String displayDashboard(Model model) {
		
		model.addAttribute("title", "Dashboard");
		
		return "dashboard";
	}
}
