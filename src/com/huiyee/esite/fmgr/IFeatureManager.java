package com.huiyee.esite.fmgr;

import java.util.List;

import com.huiyee.esite.dto.DynamicActionDto;
import com.huiyee.esite.dto.ExportDto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.VisitUser;

public interface IFeatureManager {
	
	public IDto config(long fid); 
	
	public IDto config(long fid,Account account);
	
	public String configSub(long featureid,IDto dto,Account account);
	
	public long add(long pageid, long featureid,String featurename);
	
	public String dynamicAction(long uid,DynamicActionDto dto);

	public List<String> export(long featureid, long sitegroupid, long ownerid, ExportDto exportDto);
	
	public IDto configNew(long fid,Account account,long relationid);
	
	public String configSubNew(long featureid,IDto dto,Account account);
	
	public IDto featureUserRecord(VisitUser visit,long fid);
	
}
