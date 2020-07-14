package org.bashemera.openfarm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoggerManager implements LoggerService {
	
	@Autowired
	TimeService timeService;

	@Override
	public void warn(String message, Class<?> clazz) {
		
		Logger LOG = LoggerFactory.getLogger(clazz);
		LOG.warn("@ "+timeService.currentTime() + " "+message);
	}

	@Override
	public void info(String message, Class<?> clazz) {
		Logger LOG = LoggerFactory.getLogger(clazz);
		LOG.info("@ "+timeService.currentTime() + " "+message);
	}

}
