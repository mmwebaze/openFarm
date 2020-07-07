package org.bashemera.openfarm.service;

import java.util.Optional;

import org.bashemera.openfarm.model.Farm;
import org.bashemera.openfarm.repository.FarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FarmService {
	
	@Autowired
	private FarmRepository farmRepository;
	
	
	public Farm save(Farm farm) {
		
		return farmRepository.save(farm);
	}
	public int deleteFarm(String id) {
		Optional<Farm> optionalEntity =  farmRepository.findById(id);
		
		if (optionalEntity.isPresent()) {
			
			farmRepository.delete(optionalEntity.get());
			return 1;
		}
		
		return -1;
	}

	public Farm getFarmById(String id) {
		
		Optional<Farm> optionalEntity =  farmRepository.findById(id);
		
		if (optionalEntity.isPresent()) {
			
			return optionalEntity.get();
		}
		return null;
	}
	
	public Farm findByEmployees(String id) {
		
		Farm farm = farmRepository.findByEmployees(id);
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		
		System.out.println(farm);
		
		return farm;
	}
}
