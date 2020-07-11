package org.bashemera.openfarm.controller.management;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.bashemera.openfarm.model.Animal;
import org.bashemera.openfarm.model.AnimalType;
import org.bashemera.openfarm.service.AnimalManager;
import org.bashemera.openfarm.service.AnimalTypeManager;
import org.bashemera.openfarm.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AnimalController {
	
	//@Autowired
	//private AnimalTypeRepository animalTypeRepository;
	@Autowired
	private UserManager userService;
	
	@Autowired
	private AnimalTypeManager animalTypeService;
	
	@Autowired
	private AnimalManager animalService;
	
	@RequestMapping(value = "/management/animal/create", method = RequestMethod.GET)
	public String createAnimalForm(Model model) {
		
		Animal animal = new Animal();
		
		model.addAttribute("animal", animal);
		
		List<AnimalType> animalTypes = this.getAnimalTypes();
		
		model.addAttribute("animalTypes", animalTypes);
		
		model.addAttribute("title", "Add animal");
		
		return "management/animal/create";
	}
	
	@RequestMapping(value = "/management/animal/create", method = RequestMethod.POST)
    public String saveAnimalSubmission(@Valid @ModelAttribute("animal") Animal animal, BindingResult bindingResult, Principal principal, Model model) {
		
		if (bindingResult.hasErrors()) {
			
			model.addAttribute("animal", animal);
			
			List<AnimalType> animalTypes = this.getAnimalTypes();
			
			model.addAttribute("animalTypes", animalTypes);
			model.addAttribute("title", "Animal saved");
			
			return "management/animal/create";
		}
		
		//User currentLoggedInUser = userService.findByEmail(principal.getName());
		//animal.setOwner(currentLoggedInUser);
		
		//TODO use getId() instead. But first we have to return the value from the selected 
		String id = animal.getAnimalType().getName();
		//Optional<AnimalType> optionalEntity = animalTypeRepository.findById(id);
		AnimalType animalType = animalTypeService.getAnimalById(id);
		
		animal.setAnimalType(animalType);
		animalService.save(animal);

        System.out.println(animal);

        return "management/animal/cZ_result";
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
	
	@RequestMapping(value = "/management/animal/view/{id}", method = RequestMethod.GET)
	public String animal(Principal principal, Model model, @PathVariable(name = "id", required = true) String id) {
		
		if (StringUtils.isEmpty(id)) {
			
			return "system/error_403";
		}
		Animal animal = animalService.getAnimalById(id);
		
		if (animal == null) {
			
			return "system/error_403";
		}
		
		//model.addAttribute("animalTypes", animalTypes);
		
		
		model.addAttribute("title", "view animal");
		model.addAttribute("id", id);
		model.addAttribute("animal", animal);
		
		return "management/animal/view";
	}
	
	private List<AnimalType> getAnimalTypes(){
		
		return animalTypeService.getAnimalTypes();
	}
	
}
