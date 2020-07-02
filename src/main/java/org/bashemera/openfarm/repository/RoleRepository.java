package org.bashemera.openfarm.repository;

import org.bashemera.openfarm.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "role", path = "roles")
public interface RoleRepository extends MongoRepository<Role, String>{
	
	Role findByRole(String role);
}
