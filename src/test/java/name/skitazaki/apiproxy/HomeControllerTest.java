package name.skitazaki.apiproxy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import name.skitazaki.apiproxy.model.Configuration;

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
		List<Configuration> servers = c.servers();
		assertNotNull(servers);
		assertEquals(2, servers.size());
		Configuration c1 = servers.get(0);
		assertEquals("solr", c1.getName());
		assertEquals("http://localhost:8983/solr", c1.getServer());
		Configuration c2 = servers.get(1);
		assertEquals("python", c2.getName());
		assertEquals("http://localhost:8000", c2.getServer());
	}

	@Test
	public void proxy() {
		HomeController c = new HomeController();
		Configuration proxy = c.proxy(null, null);
		assertNotNull(proxy);
		assertEquals("solr", proxy.getName());
		assertEquals("http://localhost:8983/solr/{core}", proxy.getServer());
	}
}
