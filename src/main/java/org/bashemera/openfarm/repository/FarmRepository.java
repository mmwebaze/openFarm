package org.bashemera.openfarm.repository;

import org.bashemera.openfarm.model.Farm;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "farm", path = "farms")
public interface FarmRepository extends MongoRepository<Farm, String> {
	
	public Farm findByName(String name);
	public Farm findByEmployees(String id);
}
