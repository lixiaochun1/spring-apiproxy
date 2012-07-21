package name.skitazaki.apiproxy.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import name.skitazaki.apiproxy.model.ServerInfo;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

public class JdbcServerInfoDao extends NamedParameterJdbcDaoSupport implements
		ServerInfoDao {

	public List<ServerInfo> getServers() {
		List<ServerInfo> s = getJdbcTemplate().query("SELECT * FROM products",
				new ServerInfoMapper());
		if (s != null) {
			logger.info("Fetched " + s.size() + " item(s).");
		}
		return s;
	}

	public void saveServer(ServerInfo s) {
		logger.info("Save server info: " + s.getName());
		int updated = getNamedParameterJdbcTemplate()
				.update("UPDATE products SET description = :description WHERE id = :id",
						new MapSqlParameterSource().addValue("description",
								s.getName()).addValue("id", s.getId()));
		logger.info("Rows affected: " + updated);
	}

	private static class ServerInfoMapper implements RowMapper<ServerInfo> {

		public ServerInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			ServerInfo s = new ServerInfo();
			s.setId(rs.getInt("id"));
			// XXX: Modify table schema.
			s.setName("");
			s.setUrl("");
			return s;
		}
	}
}
