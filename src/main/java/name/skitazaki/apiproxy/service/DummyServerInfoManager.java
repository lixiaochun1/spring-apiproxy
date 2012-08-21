package name.skitazaki.apiproxy.service;

import java.util.List;

import name.skitazaki.apiproxy.model.ServerInfo;

public class DummyServerInfoManager implements ServerInfoManager {

	private List<ServerInfo> configurations;

	public List<ServerInfo> getConfigurations() {
		return configurations;
	}

	public void setConfigurations(List<ServerInfo> configurations) {
		this.configurations = configurations;
	}

	public ServerInfo getConfiguration(String name) {
		if (name == null || name.length() == 0) {
			return null;
		}
		for (ServerInfo s : configurations) {
			if (name.equals(s.getName())) {
				return s;
			}
		}
		return null;
	}
}
