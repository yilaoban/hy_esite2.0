package com.huiyee.esite.fmgr.imp;

import java.util.ArrayList;
import java.util.List;

import com.huiyee.esite.dto.ExportDto;
import com.huiyee.esite.dto.Feature142Dto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.showdto.Show142Dto;
import com.huiyee.esite.fdao.IHd142Dao;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.SecurityCodeModel;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.Base62Util;
import com.huiyee.esite.util.DateUtil;
import com.huiyee.esite.util.HyConfig;

public class Feature142ManagerImpl extends AbstractFeatureManager
{

	private IHd142Dao hd142Dao;

	@Override
	public long add(long pageid, long featureid, String featurename)
	{
		long fid = hd142Dao.saveSecurityCodeCheck(pageid);
		return getPageFeatureDao().addPageFeature(pageid, featureid, fid, featurename, "Y");
	}

	@Override
	public IDto config(long fid, Account account)
	{
		Feature142Dto dto = new Feature142Dto();
		List<SecurityCodeModel> list = hd142Dao.findSecurityCodeCheckedList(fid);
		dto.setFid(fid);
		dto.setList(list);
		return dto;
	}

	@Override
	public IDto config(long fid)
	{
		Show142Dto d = new Show142Dto();
		return d;
	}

	public void setHd142Dao(IHd142Dao hd142Dao)
	{
		this.hd142Dao = hd142Dao;
	}

	@Override
	public IDto featureUserRecord(VisitUser visit,long fid)
	{
		Show142Dto d = new Show142Dto();
		if (visit != null)
		{
		 d.setRecord(hd142Dao.findSecurityCodeRecord(visit.getPageid(), visit.getUid(), visit.getCd()));
		}
		return d;
	}

	@Override
	public List<String> export(long featureid, long sitegroupid, long ownerid, ExportDto dto)
	{

		List<String> list = new ArrayList<String>();
		if (dto == null || dto.getPageId() == 0)
		{
			return null;
		}
		else
		{
			list.add("–Ú∫≈,Œ¢–≈ID,∑¿Œ±¬Î1,∑¿Œ±¬Î2,∑¿Œ±¬Î3,∑¿Œ±¬Î4, ÷ª˙,µÿ÷∑,Ã·Ωª ±º‰");
			List<SecurityCodeModel> record = new ArrayList<SecurityCodeModel>();
			record = hd142Dao.findSecurityCodeRecord(dto.getPageId(), dto.getStartTime(), dto.getEndTime());
			if (record != null && record.size() > 0)
			{
				int i = 1;
				for (SecurityCodeModel r : record)
				{
					StringBuilder sb = new StringBuilder();
					sb.append(i + ",");
					sb.append(r.toString());
					list.add(sb.toString());
					i++;
				}
			}
			return list;
		}
	}

}
