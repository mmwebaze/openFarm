package org.bashemera.openfarm.service;

import java.util.List;

import org.bashemera.openfarm.model.Animal;
import org.bashemera.openfarm.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {
	
	@Autowired
	private AnimalRepository animalRepository;
	
	public List<Animal> getAllAnimals() {
		
		return animalRepository.findAll();
	}
	
	public Animal save(Animal animal) {
		
		return animalRepository.save(animal);
	}
	
	public int deleteAnimal(String tagId) {
		Animal animal = animalRepository.findByTagId(tagId);
		
		if (animal != null) {
			
			animalRepository.delete(animal);
			return 1;
		}
		
		return -1;
	}
}
