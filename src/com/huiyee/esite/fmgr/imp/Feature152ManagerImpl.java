
package com.huiyee.esite.fmgr.imp;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.huiyee.esite.dto.Feature151Dto;
import com.huiyee.esite.dto.Feature152Dto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.showdto.Show118Dto;
import com.huiyee.esite.dto.showdto.Show152Dto;
import com.huiyee.esite.fdao.IHd152Dao;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.BBSForum;
import com.huiyee.esite.model.HD151Model;
import com.huiyee.interact.appointment.model.AppointmentModel;
import com.huiyee.interact.appointment.model.OrderMappingModel;
import com.huiyee.interact.cb.model.InteractCb;

public class Feature152ManagerImpl extends AbstractFeatureManager
{

	private IHd152Dao hd152Dao;

	public void setHd152Dao(IHd152Dao hd152Dao)
	{
		this.hd152Dao = hd152Dao;
	}

	@Override
	public long add(long pageid, long featureid, String featurename)
	{
		long fid = hd152Dao.saveFeatureInteract(pageid);
		return getPageFeatureDao().addPageFeature(pageid, featureid, fid, featurename, "N");
	}

	@Override
	public IDto config(long fid, Account account)
	{
		Feature152Dto dto = new Feature152Dto();
		long ownerid = account.getOwner().getId();
		InteractCb cb = hd152Dao.findCbByFid(fid);
		dto.setCb(cb);
		List<InteractCb> list = hd152Dao.findCblist(ownerid);
		dto.setList(list);
		return dto;
	}

	@Override
	public IDto config(long fid)
	{
		Show152Dto dto = new Show152Dto();
		dto.setFid(fid);
		InteractCb cb = hd152Dao.findCbByFid(fid);
		if (cb != null)
		{
			dto.setCb(cb);
			AppointmentModel apt = hd152Dao.findAptByCbid(cb.getId());
			if (apt != null)
			{
				List<OrderMappingModel> list = hd152Dao.findInteractAptMappingById(apt.getId());
				if (list.size() > 0)
				{
					apt.setList(list);
				}
				dto.setApp(apt);
			}
		}
		return dto;
	}

	@Override
	public String configSub(long featureid, IDto dto, Account account)
	{
		String result = "N";
		Feature152Dto d = (Feature152Dto) dto;
		long ownerid = account.getOwner().getId();
		int res = hd152Dao.updateFeatureCbid(d.getCbid(), d.getFid());
		if (res == 1)
		{
			result = "Y";
		}
		return result;
	}
}
