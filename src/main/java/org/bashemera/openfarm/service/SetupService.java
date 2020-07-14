package org.bashemera.openfarm.service;

import java.util.List;

import org.bashemera.openfarm.exception.SetupFailureException;
import org.bashemera.openfarm.model.Role;
import org.bashemera.openfarm.model.User;

public interface SetupService {
	public boolean isSetup();
	public void setupDefaultRoles(List<Role> roles) throws SetupFailureException;
	public void setupDefaultUser(User user) throws SetupFailureException;
}
