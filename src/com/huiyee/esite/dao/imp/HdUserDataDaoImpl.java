package com.huiyee.esite.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dao.IHdUserDataDao;

public class HdUserDataDaoImpl extends AbstractDao implements IHdUserDataDao {

	@Override
	public int addUserData(long uid, int utype, long hdid, long featureid) {
		Object[] params = { uid, utype, hdid, featureid };
		String sql = "INSERT INTO esite.es_interact_user_data(uid,utype,hdid,hdday,featureid,num) VALUES(?,?,?,now(),?,1) ON DUPLICATE KEY UPDATE num=num+1";
		return getJdbcTemplate().update(sql, params);
	}

	@Override
	public int findUserDayNum(long uid, int utype, long hdid, long featureid) {
		Object[] params = { uid, utype, hdid, featureid };
		String sql = "select * from esite.es_interact_user_data where uid=? and utype=? and hdid=? and hdday=DATE_FORMAT(NOW(),'%Y-%m-%d') and featureid=?";
		List<Integer> list = getJdbcTemplate().query(sql, params, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				return rs.getInt("num");
			}

		});
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return 0;
	}

	@Override
	public int findUserTotal(long uid, int utype, long hdid, long featureid) {
		Object[] params2 = { uid, utype, hdid, featureid };
		String sql2 = "select sum(num) from esite.es_interact_user_data where uid=? and utype=? and hdid=? and featureid=?";
		return getJdbcTemplate().queryForInt(sql2, params2);
	}
}
