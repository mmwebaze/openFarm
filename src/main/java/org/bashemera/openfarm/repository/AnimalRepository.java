package org.bashemera.openfarm.repository;

import java.util.List;

import org.bashemera.openfarm.model.Animal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "animal", path = "animals")
public interface AnimalRepository extends MongoRepository<Animal, String> {
	
	public Animal findByTagId(@Param("tagId") String tagId);
	public List<Animal> findByName(@Param("name") String name);
	//public List<Animal> findByOwner(@Param("ownerId") String ownerId);
}
