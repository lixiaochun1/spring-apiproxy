package name.skitazaki.apiproxy.service;

import java.util.List;

import name.skitazaki.apiproxy.model.Configuration;

public class SimpleConfigurationManager implements ConfigurationManager {

	private List<Configuration> configurations;

	public List<Configuration> getConfigurations() {
		return configurations;
	}

	public void setConfigurations(List<Configuration> configurations) {
		this.configurations = configurations;
	}
}
