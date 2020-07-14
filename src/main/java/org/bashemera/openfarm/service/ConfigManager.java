package org.bashemera.openfarm.service;

import java.util.List;

import org.bashemera.openfarm.model.Config;
import org.bashemera.openfarm.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigManager implements ConfigService {
	
	@Autowired
	ConfigRepository configRepository;

	@Override
	public Config getConfig() {
		
		List<Config> configs = configRepository.findAll();
		
		if (configs.size() == 0)
			return null;
		else
			return configRepository.findAll().get(0);
	}
	
	@Override
	public Config save(Config config) {
		
		return configRepository.save(config);
	}

}
