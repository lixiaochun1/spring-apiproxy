package name.skitazaki.apiproxy.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import name.skitazaki.apiproxy.model.ServerInfo;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = "classpath:test-context.xml")
public class JdbcServerInfoDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	private ServerInfoDao serverInfoDao;

	@Autowired
	public void setServerInfoDao(ServerInfoDao serverInfoDao) {
		this.serverInfoDao = serverInfoDao;
	}

	@Before
	public void setUp() throws Exception {
		super.deleteFromTables(new String[] { "server_info" });
		super.executeSqlScript("file:db/load_data.sql", true);
	}

	@Test
	public void testGetServerInfoList() {
		List<ServerInfo> servers = serverInfoDao.getServers();
		assertEquals("wrong number of products?", 3, servers.size());
	}

	@Test
	public void testSaveServerInfo() {
		List<ServerInfo> servers = serverInfoDao.getServers();
		for (ServerInfo p : servers) {
			p.setUrl("http://localhost");
			serverInfoDao.saveServer(p);
		}

		List<ServerInfo> updatedServers = serverInfoDao.getServers();
		for (ServerInfo p : updatedServers) {
			assertEquals("wrong server url?", "http://localhost", p.getUrl());
		}
	}
}
