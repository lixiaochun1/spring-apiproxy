package name.skitazaki.apiproxy.service;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.util.List;

import name.skitazaki.apiproxy.model.ServerInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-context.xml")
@TransactionConfiguration
@Transactional
public class SimpleServerInfoManagerTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private ServerInfoManager manager;

	@Before
	public void setUp() throws Exception {
		super.executeSqlScript("file:db/load_data.sql", true);
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
		assertThat(info.getId(), is(1));
		assertThat(info.getName(), is("solr"));
		assertThat(info.getUrl(), is("http://localhost:8983/solr"));
	}

	@Test
	public void all() {
		List<ServerInfo> list = manager.getConfigurations();
		assertThat(list, notNullValue());
		assertThat(list.size(), is(3));
		assertThat(list.get(0).getId(), is(1));
		assertThat(list.get(0).getName(), is("solr"));
		assertThat(list.get(0).getUrl(), is("http://localhost:8983/solr"));
		assertThat(list.get(1).getId(), is(2));
		assertThat(list.get(1).getName(), is("python"));
		assertThat(list.get(1).getUrl(), is("http://localhost:8000"));
		assertThat(list.get(2).getId(), is(3));
		assertThat(list.get(2).getName(), is("nodejs"));
		assertThat(list.get(2).getUrl(), is("http://localhost:4000"));
	}
}
