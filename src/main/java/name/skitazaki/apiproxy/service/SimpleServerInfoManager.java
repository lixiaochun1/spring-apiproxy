package name.skitazaki.apiproxy.service;

import java.util.List;

import name.skitazaki.apiproxy.model.ServerInfo;

public class SimpleServerInfoManager implements ServerInfoManager {

	private List<ServerInfo> configurations;

	public List<ServerInfo> getConfigurations() {
		return configurations;
	}

	public void setConfigurations(List<ServerInfo> configurations) {
		this.configurations = configurations;
	}
}
