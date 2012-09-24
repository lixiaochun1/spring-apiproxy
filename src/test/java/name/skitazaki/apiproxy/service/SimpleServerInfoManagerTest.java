package name.skitazaki.apiproxy.service;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.List;

import name.skitazaki.apiproxy.model.ServerInfo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SimpleServerInfoManagerTest {

	@Autowired
	private ServerInfoManager manager;

	@Autowired
	private SessionFactory sessionFactory;

	@Before
	public void setUp() throws Exception {
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
	public void nullReturnsNull() {
		ServerInfo info = manager.getConfiguration(null);
		assertThat(info, nullValue());
	}

	@Test
	public void emptyReturnsNull() {
		ServerInfo info = manager.getConfiguration("");
		assertThat(info, nullValue());
	}

	@Test
	public void invalidReturnsNull() {
		ServerInfo info = manager.getConfiguration("unknown");
		assertThat(info, nullValue());
	}

	@Test
	public void one() {
		ServerInfo info = manager.getConfiguration("solr");
		assertThat(info, notNullValue());
		assertThat(info.getName(), is("solr"));
		assertThat(info.getUrl(), is("http://localhost:8983/solr"));
	}

	@Test
	public void all() {
		List<ServerInfo> list = manager.getConfigurations();
		assertThat(list, notNullValue());
		assertThat(list.size(), is(2));
		assertThat(list.get(0).getName(), is("solr"));
		assertThat(list.get(0).getUrl(), is("http://localhost:8983/solr"));
		assertThat(list.get(1).getName(), is("python"));
		assertThat(list.get(1).getUrl(), is("http://localhost:8000"));
	}
}
