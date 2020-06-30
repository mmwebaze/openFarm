package org.bashemera.openfarm.repository;

import org.bashemera.openfarm.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String>{
	
	Role findByRole(String role);
}
