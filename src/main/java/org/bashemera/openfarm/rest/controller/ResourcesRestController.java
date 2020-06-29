package org.bashemera.openfarm.rest.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class ResourcesRestController {

	@RequestMapping("/resources")
	public String index() {
		
		return "List of rest endpoints";
	}
}
