package org.bashemera.openfarm.service;

import org.bashemera.openfarm.model.User;

public interface UserService {
	public User save(User user);
	public User findByEmail(String email);
	public User findById(String id);
	public User findByEmailAndEnabled(String email, boolean isEnabled);
}
