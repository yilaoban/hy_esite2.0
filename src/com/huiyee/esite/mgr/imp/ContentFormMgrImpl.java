
package com.huiyee.esite.mgr.imp;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.bdmap.dao.IBDLBSDao;
import com.huiyee.bdmap.dto.BDLBSDto;
import com.huiyee.bdmap.dto.BDLBSRs;
import com.huiyee.bdmap.dto.BDPoint;
import com.huiyee.esite.dao.IContentFormDao;
import com.huiyee.esite.mgr.IContentFormMgr;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.ContentForm;
import com.huiyee.esite.model.ContentFormMapping;
import com.huiyee.esite.model.ContentFormRecord;
import com.huiyee.esite.util.ToolUtils;

public class ContentFormMgrImpl implements IContentFormMgr
{

	private IContentFormDao contentFormDao;
	private IBDLBSDao bdlbsDao;

	public void setContentFormDao(IContentFormDao contentFormDao)
	{
		this.contentFormDao = contentFormDao;
	}

	@Override
	public ContentForm findContentFormByCcid(long ccid)
	{
		return contentFormDao.findContentFormByCcid(ccid);
	}

	@Override
	public ContentForm findContentFormById(long formid)
	{
		ContentForm cf = contentFormDao.findContentFormById(formid);
		if (cf != null)
		{
			List<ContentFormMapping> list = contentFormDao.findMappingById(formid);
			List<ContentFormMapping> list1 = new ArrayList<ContentFormMapping>();
			List<ContentFormMapping> list2 = new ArrayList<ContentFormMapping>();
			for (ContentFormMapping mapping : list)
			{
				Pattern p = Pattern.compile("f(1?[0-9]|20)");
				Matcher matcher = p.matcher(mapping.getMapping());
				if (matcher.matches())
				{
					list2.add(mapping);
				} else
				{
					list1.add(mapping);
				}
			}
			cf.setList(list1);// 固定字段
			cf.setFlist(list2);// 不固定字段
		}
		return cf;
	}

	@Override
	public int updateContentFormSub(long owner, ContentForm contentform, List<ContentFormMapping> newList)
	{
		int rs = contentFormDao.updateMappingClean(contentform.getId());
		List<ContentFormMapping> list = new ArrayList<ContentFormMapping>();
		list.addAll(contentform.getList());

		List<ContentFormMapping> flist = contentform.getFlist();
		flist = flist == null ? new ArrayList<ContentFormMapping>() : flist;
		for (ContentFormMapping contentFormMapping : flist)
		{
			if (contentFormMapping != null)
			{
				list.add(contentFormMapping);
			}
		}

		if (newList != null)
		{
			List<String> fxlist = new ArrayList<String>();
			for (int i = 1; i <= 20; i++)
			{
				fxlist.add("f" + i);
			}
			for (ContentFormMapping cf : flist)
			{
				if (cf != null && fxlist.contains(cf.getMapping()))
				{
					fxlist.remove(cf.getMapping());
				}
			}
			for (int i = 0; i < newList.size(); i++)
			{
				ContentFormMapping cf = newList.get(i);
				if (cf != null)
				{
					cf.setMapping(fxlist.remove(0));
					list.add(cf);
				}
			}
		}
		return contentFormDao.addFormMapping(contentform.getId(), list);
	}

	@Override
	public List<ContentFormMapping> findMappingById(long formid)
	{
		return contentFormDao.findMappingOrderByRow(formid);
	}

	@Override
	public int addContentFormSub(ContentFormRecord record, Account account)
	{
		List<ContentFormMapping> colum = contentFormDao.findMappingById(record.getFormid());
		long id = contentFormDao.addContentFormSub(record);
		for (ContentFormMapping mapping : colum)
		{
			if ("P".equals(mapping.getStype()))
			{
				// 地址 要往百度存
				String value = record.getMapingValue(mapping.getMapping());
				if (StringUtils.isNotEmpty(value))
				{
					BDPoint bdp = new Gson().fromJson(value, BDPoint.class);
					BDLBSRs rs = bdlbsDao.saveLBS(bdp, account.getOwner().getEntity(), id + "");
					if ("0".equals(rs.getStatus()))
					{
						contentFormDao.updateLbsid(rs.getId(), id);
					}
				}
			}
		}
		return 0;
	}

	@Override
	public List<ContentFormRecord> findRecordByFormid(long formid)
	{
		return contentFormDao.findRecordByFormid(formid);
	}

	@Override
	public int deletFormRecord(long recordid)
	{
		ContentFormRecord old = contentFormDao.findRecordById(recordid);
		if(old.getLbsid()!=0){
			bdlbsDao.deleteLBS(old.getLbsid());
		}
		return contentFormDao.deletFormRecord(recordid);
	}

	@Override
	public ContentFormRecord findRecordById(long recordid)
	{
		return contentFormDao.findRecordById(recordid);
	}

	public void setBdlbsDao(IBDLBSDao bdlbsDao)
	{
		this.bdlbsDao = bdlbsDao;
	}

	@Override
	public int updateFormRecord(ContentFormRecord record, Account account)
	{
		// 当新record里没有bdjson时就认为没有修改bdjson
		ContentFormRecord old = contentFormDao.findRecordById(record.getId());
		List<ContentFormMapping> colum = contentFormDao.findMappingById(record.getFormid());
		for (ContentFormMapping mapping : colum)
		{
			if ("P".equals(mapping.getStype()))
			{
				// 地址 要往百度存
				String newValue = record.getMapingValue(mapping.getMapping());
				String oldValue = old.getMapingValue(mapping.getMapping());
				if (StringUtils.isNotEmpty(newValue))
				{
					BDPoint bdp = new Gson().fromJson(newValue, BDPoint.class);
					if (bdp.getLocation() != null)
					{
						BDLBSRs rs = null;
						if (old.getLbsid() != 0)
						{
							rs = bdlbsDao.updateLBS(old.getLbsid(), bdp, account.getOwner().getEntity());
						} else
						{
							rs = bdlbsDao.saveLBS(bdp, account.getOwner().getEntity(), record.getId() + "");
						}
						if (rs != null && "0".equals(rs.getStatus()))
						{
							contentFormDao.updateLbsid(rs.getId(), record.getId());
						}
					}
				} else
				{
					record.setMapingValue(mapping.getMapping(), oldValue);
				}
			}
		}
		return contentFormDao.updateFormRecord(record);
	}

	@Override
	public List<Map<String, String>> findRecordByPoint(String x, String y, String tags, int pagesize)
	{
		
		List<Map<String, String>> rs=new ArrayList<Map<String, String>>();
		List<BDLBSDto> ls = bdlbsDao.findLBS(x, y, tags, 30000, pagesize);
		if (ls != null && ls.size() > 0)
		{
			for (BDLBSDto bs : ls)
			{
				try
				{
					ContentFormRecord cr = contentFormDao.findRecordById(Long.valueOf(bs.getHykey()));
					List<ContentFormMapping> colum = contentFormDao.findMappingById(cr.getFormid());
					Map<String, String> obj=this.composeContentForm(cr, colum);
					if (cr != null)
						rs.add(obj);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}

		return rs;
	}
	
	
	@Override
	public Map<String, String> findRecordId(long recordid)
	{
		ContentFormRecord cr = contentFormDao.findRecordById(recordid);
		List<ContentFormMapping> colum = contentFormDao.findMappingById(cr.getFormid());
		Map<String, String> obj=this.composeContentForm(cr, colum);
		return obj;
	}
	
	private Map<String, String>  composeContentForm(ContentFormRecord r, List<ContentFormMapping> colum){
		Map<String, String>	map = new HashMap<String, String>();
		map.put("id", r.getId()+"");
		map.put("formid", r.getFormid()+"");
		for (ContentFormMapping mapp : colum)
		{
			try
			{
				String name = ToolUtils.toUpperCaseFirstOne(mapp.getMapping());
				Method m = r.getClass().getMethod("get" + name);
				Object value = m.invoke(r); // 调用getter方法获取属性值
				if (value != null && String.valueOf(value).trim().length() > 0)
				{
					map.put(mapp.getName(), value + "");
				} else
				{
					map.put(mapp.getName(), "");
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return map;
	}
}
