package name.skitazaki.apiproxy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import name.skitazaki.apiproxy.model.ServerInfo;
import name.skitazaki.apiproxy.repository.ServerInfoDao;

public class SimpleServerInfoManager implements ServerInfoManager {

	private List<ServerInfo> configurations;

	@Autowired
	private ServerInfoDao serverInfoDao;

	public List<ServerInfo> getConfigurations() {
		if (serverInfoDao == null) {
			return configurations;
		}
		return serverInfoDao.getServers();
	}

	public void setConfigurations(List<ServerInfo> configurations) {
		this.configurations = configurations;
	}

	public void setServerInfoDao(ServerInfoDao serverInfoDao) {
		this.serverInfoDao = serverInfoDao;
	}
}
