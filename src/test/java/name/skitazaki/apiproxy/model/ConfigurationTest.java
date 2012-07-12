package name.skitazaki.apiproxy.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class ConfigurationTest {

	private Configuration configuration;

	@Before
	public void setUp() throws Exception {
		configuration = new Configuration();
	}

	@Test
	public void testSetAndGetName() {
		String name = "solr";
		assertNull(configuration.getName());
		configuration.setName(name);
		assertEquals(name, configuration.getName());
	}

	@Test
	public void testSetAndGetUrl() {
		String url = "http://localhost:8983/solr";
		assertNull(configuration.getUrl());
		configuration.setUrl(url);
		assertEquals(url, configuration.getUrl());
	}
}
