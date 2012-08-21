package name.skitazaki.apiproxy.repository;

import java.util.List;

import name.skitazaki.apiproxy.model.ServerInfo;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateServerInfoDao implements ServerInfoDao {

	@Autowired
	private SessionFactory sessionFactory;

	public List<ServerInfo> getServers() {
		return sessionFactory.getCurrentSession()
				.createQuery("from ServerInfo").list();
	}

	public ServerInfo getServer(String name) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("from ServerInfo where name = :name");
		query.setString("name", name);
		Object result = query.uniqueResult();
		if (result == null) {
			// XXX: LOG ME
			return null;
		}
		return (ServerInfo) result;
	}

	public void saveServer(ServerInfo s) {
	}
}
