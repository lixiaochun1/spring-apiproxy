package name.skitazaki.apiproxy.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.List;

import name.skitazaki.apiproxy.model.Configuration;

import org.junit.Before;
import org.junit.Test;

public class ConfigurationManagerTest {

	private ConfigurationManager manager;

	@Before
	public void setUp() throws Exception {
		manager = new SimpleConfigurationManager();
	}

	@Test
	public void testGetNoConfigurations() {
		assertNull(manager.getConfigurations());
	}

	@Test
	public void testGetConfigurationsWithTwoData() {
		Configuration c1 = new Configuration();
		c1.setName("solr");
		c1.setUrl("http://localhost:8983/solr");
		Configuration c2 = new Configuration();
		c2.setName("python");
		c2.setUrl("http://localhost:8000");
		((SimpleConfigurationManager) manager).setConfigurations(Arrays.asList(
				c1, c2));
		List<Configuration> configs = manager.getConfigurations();
		assertNotNull(configs);
		assertEquals(2, configs.size());
		Configuration r1 = configs.get(0);
		assertEquals("solr", r1.getName());
		assertEquals("http://localhost:8983/solr", r1.getUrl());
		Configuration r2 = configs.get(1);
		assertEquals("python", r2.getName());
		assertEquals("http://localhost:8000", r2.getUrl());
	}

}
