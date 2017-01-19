package com.huiyee.esite.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.huiyee.esite.dao.AbstractDao;
import com.huiyee.esite.dao.IEmotionsDao;
import com.huiyee.esite.model.Emotions;
import com.huiyee.esite.model.EmotionsCategory;

public class EmotionsDaoImpl extends AbstractDao implements IEmotionsDao {

	private static final String FIND_EMOTIONS_CATEGORY="select * from es_emotions_category order by id asc";
	@Override
	public List<EmotionsCategory> findEmotionsCategory() {
		return getJdbcTemplate().query(FIND_EMOTIONS_CATEGORY, new EmotionsCategoryRowmapper());
	}
	class EmotionsCategoryRowmapper implements RowMapper{
		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			EmotionsCategory ec=new EmotionsCategory();
			ec.setId(rs.getLong("id"));
			ec.setName(rs.getString("name"));
			return ec;
		}
		
	}
	
	private static final String FIND_EMOTIONS_BY_CATEGORY="select e.id,e.iconurl,e.value,e.categoryid,ec.name from es_emotions e join es_emotions_category ec on e.categoryid=ec.id where categoryid = ?";
	@Override
	public List<Emotions> findEmotionsByCategory(long category) {
		Object[] params={category};
		return getJdbcTemplate().query(FIND_EMOTIONS_BY_CATEGORY,params, new EmotionsRowmapper());
	}
	class EmotionsRowmapper implements RowMapper{

		@Override
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Emotions emotions=new Emotions();
			emotions.setId(rs.getLong("id"));
			emotions.setCategoryId(rs.getLong("categoryid"));
			emotions.setIconurl(rs.getString("iconurl"));
			emotions.setValue(rs.getString("value"));
			emotions.setCategoryName(rs.getString("name"));
			return emotions;
		}
		
	}
	private static final String FIND_EMOTIONS_BY_VALUE="select *  from es_emotions e join es_emotions_category ec on e.categoryid=ec.id where value= ? ";
	public Emotions findEmotionsByValue(String imgtext) {
		List<Emotions> es=getJdbcTemplate().query(FIND_EMOTIONS_BY_VALUE, new Object[]{imgtext},new EmotionsRowmapper());
		if(es.size()>0){
			return es.get(0);
		}
   		return null;
	}
}
