package org.bashemera.openfarm.service;

import java.util.List;

import org.bashemera.openfarm.model.Role;
import org.bashemera.openfarm.model.User;
import org.bashemera.openfarm.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleManager implements RoleService {
	
	@Autowired
	RoleRepository roleRepository;
	
	/** Consider changing this to a filter or an interceptor*/
	@Override
	public boolean hasRole(User user, String roleName) {
		Role role = user.getRole();
		
		if (role.getName().compareToIgnoreCase(roleName) == 0) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public Role findByName(String role) {
		
		
		return roleRepository.findByName(role.toUpperCase());
	}
	
	public List<Role> bulkSave(List<Role> roles) {
		
		return roleRepository.saveAll(roles);
	}
}
