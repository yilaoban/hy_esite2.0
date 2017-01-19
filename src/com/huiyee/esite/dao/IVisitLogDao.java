package com.huiyee.esite.dao;

import java.util.List;

import com.huiyee.esite.dto.VisitLogDto;
import com.huiyee.esite.dto.VisitPageTime;

public interface IVisitLogDao
{
  public void addLogs(List<VisitLogDto> vls);
  
  public void addVpts(List<VisitPageTime> vls);
}
