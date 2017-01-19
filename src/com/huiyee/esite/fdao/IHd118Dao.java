package com.huiyee.esite.fdao;

import java.util.List;

import com.huiyee.esite.model.Feature118InteractApt;
import com.huiyee.esite.model.Module;
import com.huiyee.esite.model.PageBlockRelation;
import com.huiyee.interact.appointment.model.AppointmentModel;
import com.huiyee.interact.appointment.model.OrderMappingModel;

public interface IHd118Dao {
	
	 public long saveFeatureInteract(final long pageid);
	 
	 public Module findAptidByFid(long fid);
	 
	 public  List<Feature118InteractApt> findApponitmentsByOwner(long ownerid);
	 
	 public int updateupdateFeatureIneractAptName(long fid,String name);
	 
	 public int updateFeatureIneractApt(long aptid,long fid);
	 
	 public Feature118InteractApt findFeature118InteractAptById(long fid);
	 
	 public AppointmentModel findFeatureInteractAptById(long fid);
	 
	 public AppointmentModel findFeatureInteractAptByAptid(long aptid);
	 
	 public List<OrderMappingModel> findInteractAptMappingByFId(long fid);
	 
	 public List<OrderMappingModel> findInteractAptMappingById(long fid);
	 
	 public PageBlockRelation findPageBlockRelationByRelationid(long relationid);
	 
	 public int updatePageBlockRelationByRelationid(long relationid,String json);
}
