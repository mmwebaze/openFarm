package org.bashemera.openfarm.service;

import java.util.List;
import java.util.Optional;

import org.bashemera.openfarm.model.AnimalType;
import org.bashemera.openfarm.repository.AnimalTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalTypeManager implements AnimalTypeService {
	
	@Autowired
	private AnimalTypeRepository animalTypeRepository;
	
	@Override
	public AnimalType getAnimalById(String id) {
		Optional<AnimalType> optionalEntity = animalTypeRepository.findById(id);
		
		if (optionalEntity.isPresent()) {
			
			return optionalEntity.get();
		}
		return null;
	}
	
	@Override
	public List<AnimalType> getAnimalTypes(){
		
		return animalTypeRepository.findAll();
	}
}
