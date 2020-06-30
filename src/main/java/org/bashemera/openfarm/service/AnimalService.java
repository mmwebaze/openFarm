package org.bashemera.openfarm.service;

import java.util.List;

import org.bashemera.openfarm.model.Cow;
import org.bashemera.openfarm.repository.CowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {
	
	@Autowired
	private CowRepository repository;
	
	public List<Cow> getAllCows() {
		
		return repository.findAll();
	}
	
	public Cow addCow(String tagId, String name) {
		//Cow cow = new Cow();
		
		//return repository.save(cow);
		
		return null;
	}
	
	public int deleteCow(String tagId) {
		Cow cow = repository.findByTagId(tagId);
		
		if (cow != null) {
			
			repository.delete(cow);
			return 1;
		}
		
		return -1;
	}
}
