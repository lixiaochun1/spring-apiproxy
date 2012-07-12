package name.skitazaki.apiproxy.service;

import static org.junit.Assert.assertNull;

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

}
