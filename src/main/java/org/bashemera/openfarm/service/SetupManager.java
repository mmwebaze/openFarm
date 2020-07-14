package org.bashemera.openfarm.service;

import java.util.List;

import org.bashemera.openfarm.exception.SetupFailureException;
import org.bashemera.openfarm.model.Config;
import org.bashemera.openfarm.model.Role;
import org.bashemera.openfarm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetupManager implements SetupService {
	
	@Autowired
	private ConfigService configService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;

	@Override
	public boolean isSetup() {
		Config config = configService.getConfig();
		
		if (config == null) {
			
			return false;
		}
		
		return true;
		
	}
	
	@Override
	public void setupDefaultRoles(List<Role> roles) throws SetupFailureException {
		
		List<Role> savedRoles = roleService.bulkSave(roles);
		
		if (savedRoles.size() == 0) {
			throw new SetupFailureException("Failed to save default roles");
		}
	}
	
	@Override
	public void setupDefaultUser(User user) throws SetupFailureException {
		
		User savedUser = userService.save(user);
		
		if (savedUser == null) {
			throw new SetupFailureException("Failed to create default user ADMIN");
		}
	}
}
