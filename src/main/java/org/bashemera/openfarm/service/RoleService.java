package org.bashemera.openfarm.service;

import java.util.List;

import org.bashemera.openfarm.model.Role;
import org.bashemera.openfarm.model.User;

public interface RoleService {
	public boolean hasRole(User user, String roleName);
	public Role findByName(String role);
	public List<Role> bulkSave(List<Role> roles);
}
