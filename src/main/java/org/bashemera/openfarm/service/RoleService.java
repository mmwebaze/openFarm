package org.bashemera.openfarm.service;

import java.util.Iterator;
import java.util.Set;

import org.bashemera.openfarm.model.Role;
import org.bashemera.openfarm.model.User;
import org.bashemera.openfarm.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
	
	@Autowired
	RoleRepository roleRepository;
	
	public boolean hasRole(User user, String roleName) {
		Set<Role> roles = user.getRoles();
		
		Iterator<Role> roleIterator = roles.iterator();
		
		while(roleIterator.hasNext()){ 
			Role role = roleIterator.next();
			
			if (role.getName().compareToIgnoreCase(roleName) == 0) {
				return true;
			}
		}
		return false;
	} 
}
