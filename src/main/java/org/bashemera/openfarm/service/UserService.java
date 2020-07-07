package org.bashemera.openfarm.service;

import java.util.Optional;

import org.bashemera.openfarm.model.User;
import org.bashemera.openfarm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;

	public User findByEmail(String email) {
		
		User user = userRepository.findByEmail(email);
		
		if (user != null) {
			
			return user;
		}
		
		return null;
	}

	public User findById(String id) {
		
		Optional<User> optionalEntity = userRepository.findById(id);
		
		if (optionalEntity.isPresent()) {
			
			return optionalEntity.get();
		}
		
		return null;
	}
}
