package com.huiyee.esite.fdao;

import java.util.List;
import java.util.Map;

import com.huiyee.esite.model.CategoryTree;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.esite.model.ProlistProduct;

public interface IProListProductDao {

	public List<ProlistProduct> findProduct(long key);

	public void deleteByprolistId(long id);

	public void addProlistProduct(long newprolistid, long productid, int zantotal, int idx);
	
	public long addProductZan(long productid,long uid,String ip,String terminal,String source,long pageid);
	
	public long findProductZan(long productid,long uid);
	
	public int addZan(long fppid);

	public int findMaxIdx(long newprolistid);
	
	public List<CategoryTree> findTreeByType(String type, long ownerid);
	
	public PageBlockRelation findPageBlockRelationByRelationid(long relationid);
	
	 public int updatePageBlockRelationByRelationid(long relationid,String json);
}
