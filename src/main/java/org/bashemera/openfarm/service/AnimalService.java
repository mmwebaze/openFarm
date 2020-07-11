package org.bashemera.openfarm.service;

import java.util.List;

import org.bashemera.openfarm.model.Animal;

public interface AnimalService {
	public List<Animal> getAllAnimals();
	public Animal save(Animal animal);
	public int deleteAnimal(String tagId);
	public Animal getAnimalById(String id);
}
