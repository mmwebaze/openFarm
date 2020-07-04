package org.bashemera.openfarm.controller.management;

import java.util.List;

import javax.validation.Valid;

import org.bashemera.openfarm.model.Animal;
import org.bashemera.openfarm.model.AnimalType;
import org.bashemera.openfarm.service.AnimalService;
import org.bashemera.openfarm.service.AnimalTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AnimalController {
	
	//@Autowired
	//private AnimalTypeRepository animalTypeRepository;
	
	@Autowired
	private AnimalTypeService animalTypeService;
	
	@Autowired
	private AnimalService animalService;
	
	@RequestMapping(value = "/management/animal/add", method = RequestMethod.GET)
	public String createAnimalForm(Model model) {
		
		Animal animal = new Animal();
		model.addAttribute("animal", animal);
		
		List<AnimalType> animalTypes = this.getAnimalTypes();
		
		model.addAttribute("animalTypes", animalTypes);
		
		model.addAttribute("title", "Add animal");
		
		return "management/animal/add";
	}
	
	@RequestMapping(value = "/management/animal/add", method = RequestMethod.POST)
    public String saveAnimalSubmission(@Valid @ModelAttribute("animal") Animal animal, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			
			model.addAttribute("animal", animal);
			
			List<AnimalType> animalTypes = this.getAnimalTypes();
			
			model.addAttribute("animalTypes", animalTypes);
			model.addAttribute("title", "Animal saved");
			
			return "management/animal/add";
		}
		
		//TODO use getId() instead. But first we have to return the value from the selected 
		String id = animal.getAnimalType().getName();
		//Optional<AnimalType> optionalEntity = animalTypeRepository.findById(id);
		AnimalType animalType = animalTypeService.getAnimalById(id);
		
		animal.setAnimalType(animalType);
		animalService.save(animal);

        System.out.println(animal);

        return "management/animal/added_result";
    }
	
	@RequestMapping(value = "/management/animal/list", method = RequestMethod.GET)
	public String manageAnimals(Model model) {
		model.addAttribute("title", "Animals");
		
		model.addAttribute("animals", animalService.getAllAnimals());
		
		return "management/animal/list";
	}
	
	@RequestMapping(value = "/management/animal/list", method = RequestMethod.POST)
	public String searchAnimals() {
		
		
		
		return "management/animal/list";
	}
	
	private List<AnimalType> getAnimalTypes(){
		
		return animalTypeService.getAnimalTypes();
	}
	
}
