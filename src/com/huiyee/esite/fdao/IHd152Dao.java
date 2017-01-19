package com.huiyee.esite.fdao;

import java.util.List;

import com.huiyee.interact.appointment.model.AppointmentModel;
import com.huiyee.interact.appointment.model.OrderMappingModel;
import com.huiyee.interact.cb.model.InteractCb;


public interface IHd152Dao {

	public long saveFeatureInteract(long pageid);

	public InteractCb findCbByFid(long fid);

	public List<InteractCb> findCblist(long ownerid);

	public AppointmentModel findAptByCbid(long id);

	public List<OrderMappingModel> findInteractAptMappingById(long id);

	public int updateFeatureCbid(long cbid, long fid);
	
}
