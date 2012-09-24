package name.skitazaki.apiproxy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import name.skitazaki.apiproxy.model.ServerInfo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class HomeControllerTest {

	@Autowired
	private HomeController ctrl;

	@Autowired
	private SessionFactory sessionFactory;

	@Before
	public void createSampleData() {
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		session.createQuery("DELETE ServerInfo").executeUpdate();
		ServerInfo c1 = new ServerInfo();
		c1.setName("solr");
		c1.setUrl("http://localhost:8983/solr");
		ServerInfo c2 = new ServerInfo();
		c2.setName("python");
		c2.setUrl("http://localhost:8000");
		session.persist(c1);
		session.persist(c2);
		session.getTransaction().commit();
	}

	@Test
	public void home() {
		String home = ctrl.home(null, null);
		assertEquals("home", home);
	}

	@Test
	public void servers() {
		List<ServerInfo> servers = ctrl.servers();
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
		// XXX: Mock HTTP request.
		String ret = ctrl.proxy("solr", null);
		assertNull(ret);
	}

	@Test(expected = ResourceNotFoundException.class)
	public void proxyNotFound() {
		ctrl.proxy("not_found", null);
	}
}
