package org.bashemera.openfarm.service;

import java.util.List;
import java.util.Optional;

import org.bashemera.openfarm.model.AnimalType;
import org.bashemera.openfarm.repository.AnimalTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalTypeService {
	
	@Autowired
	private AnimalTypeRepository animalTypeRepository;
	
	public AnimalType getAnimalById(String id) {
		Optional<AnimalType> optionalEntity = animalTypeRepository.findById(id);
		
		if (optionalEntity.isPresent()) {
			
			return optionalEntity.get();
		}
		return null;
	}
	
	public List<AnimalType> getAnimalTypes(){
		
		return animalTypeRepository.findAll();
	}
}
