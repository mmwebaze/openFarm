package org.bashemera.openfarm.service;

import org.bashemera.openfarm.model.Farm;

public interface FarmService {
	public Farm save(Farm farm);
	public int deleteFarm(String id);
	public Farm getFarmById(String id);
	public Farm findByEmployees(String id);
}
