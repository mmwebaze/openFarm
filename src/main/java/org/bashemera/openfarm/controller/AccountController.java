package org.bashemera.openfarm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccountController {

	@RequestMapping(value = "/account/create", method = RequestMethod.GET)
	public String createAccount() {
		
		
		return "account/create";
	}
}
