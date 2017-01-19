package com.huiyee.interact.servicerpj.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.interact.servicerpj.model.ServicerPjPage;

@SuppressWarnings("unchecked")
public class ServicerPjPageDaoImpl extends AbstractDao implements IServicerPjPageDao {

	@Override
	public ServicerPjPage findServicerPjPage(long owner, int type) {
		String sql = "select * from esite.es_yu_yue_servicer_pj_page where owner=? and type=?";
		Object[] p = { owner, type };
		List<ServicerPjPage> list = getJdbcTemplate().query(sql, p, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				ServicerPjPage p = new ServicerPjPage();
				p.setId(rs.getLong("id"));
				p.setOwner(rs.getLong("owner"));
				p.setPjtid(rs.getLong("pjtid"));
				p.setPjxid(rs.getLong("pjxid"));
				p.setPjrid(rs.getLong("pjrid"));
				p.setPjsid(rs.getLong("pjsid"));
				p.setPjcid(rs.getLong("pjcid"));
				return p;
			}

		});
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
