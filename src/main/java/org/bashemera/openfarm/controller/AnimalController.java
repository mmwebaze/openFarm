package org.bashemera.openfarm.controller;

import java.util.List;
import java.util.Optional;

import org.bashemera.openfarm.model.Animal;
import org.bashemera.openfarm.model.AnimalType;
import org.bashemera.openfarm.repository.AnimalRepository;
import org.bashemera.openfarm.repository.AnimalTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AnimalController {
	
	@Autowired
	private AnimalTypeRepository animalTypeRepository;
	@Autowired
	private AnimalRepository animalRepository;
	
	@RequestMapping(value = "/animal", method = RequestMethod.GET)
	public String createAnimalForm(Model model) {
		
		Animal animal = new Animal();
		model.addAttribute("animal", animal);
		
		List<AnimalType> animalTypes = animalTypeRepository.findAll();
		
		model.addAttribute("animalTypes", animalTypes);
		
		return "add_animal";
	}
	
	@RequestMapping(value = "/animal", method = RequestMethod.POST)
    public String saveAnimalSubmission(@ModelAttribute("animal") Animal animal) {
		
		//TODO use getId() instead. But first we have to return the value from the selected 
		String id = animal.getAnimalType().getName();
		Optional<AnimalType> optionalEntity = animalTypeRepository.findById(id);
		AnimalType animalType = optionalEntity.get();
		
		animal.setAnimalType(animalType);
		animalRepository.save(animal);

        System.out.println(animal);

        return "added_animal_result";
    }
	
}
