package org.bashemera.openfarm.service;

import java.util.List;
import java.util.Optional;

import org.bashemera.openfarm.model.Animal;
import org.bashemera.openfarm.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalManager implements AnimalService {
	
	@Autowired
	private AnimalRepository animalRepository;
	
	@Override
	public List<Animal> getAllAnimals() {
		
		return animalRepository.findAll();
	}
	
	/*
	 * public List<Animal> getAllAnimalsByOwner(String ownerId){
	 * 
	 * 
	 * return animalRepository.findByOwner(ownerId); }
	 */
	
	@Override
	public Animal save(Animal animal) {
		
		return animalRepository.save(animal);
	}
	
	@Override
	public int deleteAnimal(String tagId) {
		Animal animal = animalRepository.findByTagId(tagId);
		
		if (animal != null) {
			
			animalRepository.delete(animal);
			return 1;
		}
		
		return -1;
	}
	
	@Override
	public Animal getAnimalById(String id) {
		
		Optional<Animal> optionalEntity =  animalRepository.findById(id);
		
		if (optionalEntity.isPresent()) {
			
			return optionalEntity.get();
		}
		return null;
	}
}
