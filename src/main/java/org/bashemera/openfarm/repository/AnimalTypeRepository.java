package org.bashemera.openfarm.repository;

import org.bashemera.openfarm.model.AnimalType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "animaltype", path = "animalTypes")
public interface AnimalTypeRepository extends MongoRepository<AnimalType, String> {

}
