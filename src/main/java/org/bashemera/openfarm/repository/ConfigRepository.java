package org.bashemera.openfarm.repository;

import org.bashemera.openfarm.model.Config;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigRepository extends MongoRepository<Config, String> {

}
