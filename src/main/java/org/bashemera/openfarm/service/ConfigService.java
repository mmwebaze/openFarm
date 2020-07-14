package org.bashemera.openfarm.service;

import org.bashemera.openfarm.model.Config;

public interface ConfigService {
	public Config getConfig();
	public Config save(Config config);
}
