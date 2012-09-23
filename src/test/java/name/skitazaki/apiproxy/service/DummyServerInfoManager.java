package name.skitazaki.apiproxy.service;

import java.util.ArrayList;
import java.util.List;

import name.skitazaki.apiproxy.model.ServerInfo;

public class DummyServerInfoManager implements ServerInfoManager {

	List<ServerInfo> configs = new ArrayList<ServerInfo>();
	{
		ServerInfo c1 = new ServerInfo();
		c1.setName("solr");
		c1.setUrl("http://localhost:8983/solr");
		ServerInfo c2 = new ServerInfo();
		c2.setName("python");
		c2.setUrl("http://localhost:8000");
		configs.add(c1);
		configs.add(c2);
	}

	public List<ServerInfo> getConfigurations() {
		return configs;
	}

	public ServerInfo getConfiguration(String name) {
		for (ServerInfo c : configs) {
			if (name.equals(c.getName())) {
				return c;
			}
		}
		return null;
	}

}
