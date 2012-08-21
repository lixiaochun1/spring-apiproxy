package name.skitazaki.apiproxy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import name.skitazaki.apiproxy.model.ServerInfo;
import name.skitazaki.apiproxy.service.ServerInfoManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class HomeControllerTest {

	@Autowired
	@Qualifier("testManager")
	ServerInfoManager serverInfoManager;

	@Test
	public void home() {
		HomeController c = new HomeController();
		String home = c.home(null, null);
		assertEquals("home", home);
	}

	@Test
	public void servers() {
		HomeController c = new HomeController();
		c.setConfigurationManager(serverInfoManager);
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
		c.setConfigurationManager(serverInfoManager);
		// XXX: Mock HTTP request.
		ServerInfo proxy = c.proxy("solr", null);
		assertNotNull(proxy);
		assertEquals("solr", proxy.getName());
		assertEquals("http://localhost:8983/solr", proxy.getUrl());
	}
}
