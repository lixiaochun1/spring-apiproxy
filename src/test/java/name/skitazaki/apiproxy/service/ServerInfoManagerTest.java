package name.skitazaki.apiproxy.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.List;

import name.skitazaki.apiproxy.model.ServerInfo;

import org.junit.Before;
import org.junit.Test;

public class ServerInfoManagerTest {

	private ServerInfoManager manager;

	@Before
	public void setUp() throws Exception {
		manager = new DummyServerInfoManager();
	}

	@Test
	public void testGetNoConfigurations() {
		assertNull(manager.getConfigurations());
	}

	@Test
	public void testGetConfigurationsWithTwoData() {
		ServerInfo c1 = new ServerInfo();
		c1.setName("solr");
		c1.setUrl("http://localhost:8983/solr");
		ServerInfo c2 = new ServerInfo();
		c2.setName("python");
		c2.setUrl("http://localhost:8000");
		((DummyServerInfoManager) manager).setConfigurations(Arrays.asList(c1,
				c2));
		List<ServerInfo> configs = manager.getConfigurations();
		assertNotNull(configs);
		assertEquals(2, configs.size());
		ServerInfo r1 = configs.get(0);
		assertEquals("solr", r1.getName());
		assertEquals("http://localhost:8983/solr", r1.getUrl());
		ServerInfo r2 = configs.get(1);
		assertEquals("python", r2.getName());
		assertEquals("http://localhost:8000", r2.getUrl());
	}

}
