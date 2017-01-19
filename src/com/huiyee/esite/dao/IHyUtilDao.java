package com.huiyee.esite.dao;

import java.util.List;

import com.huiyee.esite.model.OwnerSetting;

public interface IHyUtilDao
{
  public long findOwnerByName(String oname);
  
  public List<OwnerSetting> findOwnerSetting();
  
}
