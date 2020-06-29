package org.bashemera.openfarm.repository;

import java.util.List;

import org.bashemera.openfarm.model.Cow;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "cow", path = "cows")
public interface CowRepository extends MongoRepository<Cow, String> {
	
	public Cow findByTagId(@Param("tagId") String tagId);
	public List<Cow> findByName(@Param("name") String name);
}
