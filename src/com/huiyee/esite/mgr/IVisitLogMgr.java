package com.huiyee.esite.mgr;

import java.util.List;

import com.huiyee.esite.dto.VisitLogDto;
import com.huiyee.esite.dto.VisitPageTime;

public interface IVisitLogMgr
{
	 public void addLogs(List<VisitLogDto> vls);
	 
	 public void addVpts(List<VisitPageTime> vls);
}
