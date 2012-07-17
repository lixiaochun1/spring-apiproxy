package name.skitazaki.apiproxy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import name.skitazaki.apiproxy.model.ServerInfo;
import name.skitazaki.apiproxy.service.SimpleServerInfoManager;

import org.junit.Test;

public class HomeControllerTest {

	@Test
	public void home() {
		HomeController c = new HomeController();
		String home = c.home(null, null);
		assertEquals("home", home);
	}

	@Test
	public void servers() {
		HomeController c = new HomeController();
		SimpleServerInfoManager manager = new SimpleServerInfoManager();
		ServerInfo c1 = new ServerInfo();
		c1.setName("solr");
		c1.setUrl("http://localhost:8983/solr");
		ServerInfo c2 = new ServerInfo();
		c2.setName("python");
		c2.setUrl("http://localhost:8000");
		manager.setConfigurations(Arrays.asList(c1, c2));
		c.setConfigurationManager(manager);
		List<ServerInfo> servers = c.servers();
		assertNotNull(servers);
		assertEquals(2, servers.size());
		ServerInfo r1 = servers.get(0);
		assertEquals("solr", r1.getName());
		assertEquals("http://localhost:8983/solr", r1.getUrl());
		ServerInfo r2 = servers.get(1);
		assertEquals("python", r2.getName());
		assertEquals("http://localhost:8000", r2.getUrl());
	}

	@Test
	public void proxy() {
		HomeController c = new HomeController();
		ServerInfo proxy = c.proxy(null, null);
		assertNotNull(proxy);
		assertEquals("solr", proxy.getName());
		assertEquals("http://localhost:8983/solr/{core}", proxy.getUrl());
	}
}
