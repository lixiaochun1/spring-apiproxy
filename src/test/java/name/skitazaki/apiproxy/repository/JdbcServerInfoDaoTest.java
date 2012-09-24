package name.skitazaki.apiproxy.repository;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.List;

import name.skitazaki.apiproxy.model.ServerInfo;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = "classpath:test-context.xml")
public class JdbcServerInfoDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Qualifier("jdbcDao")
	private ServerInfoDao serverInfoDao;

	@Autowired
	public void setServerInfoDao(ServerInfoDao serverInfoDao) {
		this.serverInfoDao = serverInfoDao;
	}

	@Before
	public void setUp() throws Exception {
		super.deleteFromTables("server_info");
		super.executeSqlScript("file:db/load_data.sql", true);
	}

	@Test
	public void getServers() {
		List<ServerInfo> servers = serverInfoDao.getServers();
		assertEquals("wrong number of products?", 3, servers.size());
	}

	@Test
	public void getServer() {
		ServerInfo server1 = serverInfoDao.getServer("solr");
		assertThat(server1.getName(), is("solr"));
		assertThat(server1.getUrl(), is("http://localhost:8983/solr"));

		ServerInfo server2 = serverInfoDao.getServer("python");
		assertThat(server2.getName(), is("python"));
		assertThat(server2.getUrl(), is("http://localhost:8000"));
	}

	@Test
	public void saveServer() {
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
