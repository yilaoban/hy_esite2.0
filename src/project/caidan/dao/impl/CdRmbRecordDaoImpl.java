
package project.caidan.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.AbstractDao;

import project.caidan.dao.ICdRmbRecordDao;
import project.caidan.model.CdRmbRecord;

public class CdRmbRecordDaoImpl extends AbstractDao implements ICdRmbRecordDao
{

	@Override
	public List<CdRmbRecord> findList(long wxuid, int start, int size)
	{
		return getJdbcTemplate().query("select * from caidan.cd_rmb_record where wxuid=? order by id desc limit ?,?", new Object[]{wxuid,start,size}, new RecordRowMapper());
	}
	
	class RecordRowMapper implements RowMapper{
		public Object mapRow(ResultSet rs, int arg1) throws SQLException
		{
			CdRmbRecord r=new CdRmbRecord();
			r.setId(rs.getLong("id"));
			r.setTotal(rs.getInt("total"));
			r.setNum(rs.getInt("num"));
			r.setWxuid(rs.getLong("wxuid"));
			r.setHydesc(rs.getString("hydesc"));
			r.setSource(rs.getString("source"));
			r.setCreatetime(rs.getTimestamp("createtime"));
			return r;
		}
	}

	@Override
	public int findRmbRecordByWxuid(long wxuid)
	{
		String sql = "select sum(num) from caidan.cd_rmb_record where wxuid = ? and num > 0 and date(createtime) = curdate()";
		return getJdbcTemplate().queryForInt(sql, new Object[]{wxuid});
	}
}
