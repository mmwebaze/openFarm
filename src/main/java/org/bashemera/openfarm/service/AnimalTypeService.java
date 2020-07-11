package org.bashemera.openfarm.service;

import java.util.List;

import org.bashemera.openfarm.model.AnimalType;

public interface AnimalTypeService {
	public AnimalType getAnimalById(String id);
	public List<AnimalType> getAnimalTypes();
}
