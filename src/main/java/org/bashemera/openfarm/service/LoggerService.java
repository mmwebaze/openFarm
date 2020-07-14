package org.bashemera.openfarm.service;

public interface LoggerService {
	public void warn(String message, Class<?> clazz);

	public void info(String message, Class<?> clazz);
}
