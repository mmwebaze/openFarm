package org.bashemera.openfarm.repository;

import org.bashemera.openfarm.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "user", path = "users", exported=true)
//@CrossOrigin(origins = "http://localhost:4200")
public interface UserRepository extends MongoRepository<User, String>{
	
	User findByEmail(String email);
	//List<User> findAll();
}
