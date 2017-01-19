package com.huiyee.esite.fdao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.fdao.IHd141Dao;
import com.huiyee.esite.model.HdModel;

public class Hd141DaoImpl extends AbstractDao implements IHd141Dao
{

	@Override
	public HdModel findHdModelName(long sitegroupid, long entityid)
	{
		String sql="select j.title title from es_site_group sg join es_site s on sg.id = s.sitegroupid join es_page p on p.siteid=s.id join es_feature_interact_journal_share_record r on r.pageid=p.id join es_interact_journal j on j.id=r.contentid where sg.id=? and r.id=?";
		List<HdModel> list=getJdbcTemplate().query(sql, new Object[]{sitegroupid,entityid}, new HdModelRowmapper());
		if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
	}
	class HdModelRowmapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			HdModel hm = new HdModel();
			hm.setName(rs.getString("title"));
			return hm;
		}
	}

	
}
