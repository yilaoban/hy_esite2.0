package com.huiyee.esite.fmgr.imp;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.huiyee.esite.dao.ITemplateDao;
import com.huiyee.esite.dto.Feature102Dto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.fdao.IProListProductDao;
import com.huiyee.esite.fdao.IProlistDao;
import com.huiyee.esite.fdao.IProlistListDao;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.BlockContext;
import com.huiyee.esite.model.CategoryTree;
import com.huiyee.esite.model.Content;
import com.huiyee.esite.model.ContentNew;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.esite.model.PageRelation;
import com.huiyee.esite.model.Prolist;
import com.huiyee.esite.model.ProlistProduct;

public class ProListProductManagerImpl extends AbstractFeatureManager {
	private ITemplateDao templateDao;
	
	public void setTemplateDao(ITemplateDao templateDao)
	{
		this.templateDao = templateDao;
	}

	private IProListProductDao prolistproductDao;
	private IProlistDao prolistDao;
	private IProlistListDao prolistListDao;

	public void setProlistDao(IProlistDao prolistDao) {
		this.prolistDao = prolistDao;
	}

	public void setProlistListDao(IProlistListDao prolistListDao) {
		this.prolistListDao = prolistListDao;
	}

	public void setProlistproductDao(IProListProductDao prolistproductDao) {
		this.prolistproductDao = prolistproductDao;
	}

	@Override
	public long add(long pageid, long featureid,String featurename) {
		long fid = prolistListDao.addProlistlist(pageid);
		return getPageFeatureDao().addPageFeature(pageid, featureid, fid,featurename,"N");
	}

	@Override
	public IDto config(long fid) {
		Feature102Dto dto = new Feature102Dto();
		List<Prolist> pl = prolistDao.findProlistByfid(fid);
		List<List<ProlistProduct>> product = new ArrayList<List<ProlistProduct>>();
		for (Prolist p : pl) {
			List<ProlistProduct> list = prolistproductDao.findProduct(p.getId());
			product.add(list);
		}
		dto.setProlist(pl);
		dto.setProduct(product);
		return dto;
	}

	public IDto config(long fid, Account account) {
		Feature102Dto dto = new Feature102Dto();
		List<Prolist> pl = prolistDao.findProlistByfid(fid);
		List<List<ProlistProduct>> product = new ArrayList<List<ProlistProduct>>();
		for (Prolist p : pl) {
			List<ProlistProduct> list = prolistproductDao.findProduct(p.getId());
			product.add(list);
		}
		dto.setProlist(pl);
		dto.setProduct(product);
		dto.setFid(fid);
		return dto;
	}

	@Override
	public String configSub(long featureid, IDto dto, Account account) {
		Feature102Dto subDto = (Feature102Dto) dto;
		List<String> relationStr = subDto.getRelationStr();
		List<Prolist> proList = subDto.getProlist();
		List<Prolist> old = prolistDao.findProlistByfid(subDto.getFid());
		List<List<ProlistProduct>> list = getProduct(relationStr);
		for (Prolist oldprolist : old) {
			// 删除原来的记录-假删除
			prolistproductDao.deleteByprolistId(oldprolist.getId());
			prolistDao.deleteById(oldprolist.getId());
		}
		for (int i = 0; i < proList.size(); i++) {
			if (proList.get(i) != null) {
				Prolist p = proList.get(i);
				long newprolistid = 0;
				if (p.getId() > 0) {
					newprolistid = p.getId();
					prolistDao.updateProlistTitle(p.getTitle(), p.getId(),p.getContent(),p.getIdx());
				} else {
					newprolistid = prolistDao.addProlist(proList.get(i).getTitle(), subDto.getFid(), proList.get(i).getContent(),proList.get(i).getIdx());
				}
				if (i < list.size() && list.get(i) != null) {
					for (ProlistProduct pp : list.get(i)) {
						if(pp.getIdx()<0){
							int index=prolistproductDao.findMaxIdx(newprolistid);
							pp.setIdx(index+1);
						}
						prolistproductDao.addProlistProduct(newprolistid, pp.getProductid(), pp.getZantotal(),pp.getIdx());
					}
				}
			}
		}
		return "Y";
	}

	private List<List<ProlistProduct>> getProduct(List<String> relationStr) {
		List<List<ProlistProduct>> list = new ArrayList<List<ProlistProduct>>();
		if (relationStr != null) {
			for (String str : relationStr) {
				if (StringUtils.isEmpty(str) || str.trim().length() == 0) {
					list.add(null);
				} else {
					String[] arr = str.split(",");
					List<ProlistProduct> small = new ArrayList<ProlistProduct>();
					for (String pp : arr) {
						String[] str1 = pp.split("~");
						ProlistProduct product = new ProlistProduct();
						product.setProlistid(Long.valueOf(str1[0].trim()));
						product.setProductid(Long.valueOf(str1[1].trim()));
						product.setZantotal(Integer.valueOf(str1[2].trim()));
						product.setIdx(Integer.valueOf(str1[3].trim()));
						small.add(product);
					}
					list.add(small);
				}
			}
		}
		return list;
	}

	@Override
	public IDto configNew(long fid, Account account, long relationid) {
		Feature102Dto dto = new Feature102Dto();
		dto.setFid(fid);
		List<CategoryTree> categoryTreeList = prolistproductDao.findTreeByType("T", account.getOwner().getId());
		dto.setCategoryTreeList(categoryTreeList);
		PageBlockRelation pbr = prolistproductDao.findPageBlockRelationByRelationid(relationid);
		if(pbr != null){
			JSONObject jb = JSONObject.fromObject(pbr.getJson());
			String hd = jb.get("obj").toString();
			jb = JSONObject.fromObject(hd);
			Object obj = jb.get("ccid");
			if(obj != null){
				long ccid = jb.getLong("ccid");
				dto.setCcid(ccid);
			}
		}
		return dto;
	}
	
	@Override
	public String configSubNew(long featureid, IDto dto, Account account) {
		Feature102Dto d = (Feature102Dto) dto;
		Content content = new Content();
		content.setCcid(d.getCcid());
		content.setFeatureid(featureid);
		content.setHyType("T");
		/**
		 * 获取详情页的pageid
		 */
		long relationid=d.getRelationid();
		List<BlockContext> list = templateDao.findesBlockContext(relationid);
		long contextid = 0;
		if (list != null && list.size() > 0) {
			contextid = list.get(0).getId();
		}
		Page page = templateDao.findPageByRelationidAndContextid(relationid, contextid);
		if(page != null){
			content.setPageid(page.getId());//详情页id
		}
		
		long ownerid = account.getOwner().getId();
		content.setOwnerid(ownerid);
		Gson gson = new Gson();
		String str1 = gson.toJson(content);
		PageBlockRelation pbr = prolistproductDao.findPageBlockRelationByRelationid(d.getRelationid());
		String str = pbr.getJson();
		JSONObject jo = JSONObject.fromObject(str);
		jo.element("obj", str1);
		prolistproductDao.updatePageBlockRelationByRelationid(d.getRelationid(),jo.toString());
		return "Y";
	}
}
