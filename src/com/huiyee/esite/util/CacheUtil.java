package com.huiyee.esite.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import java.util.concurrent.ConcurrentHashMap;

import com.huiyee.esite.dto.BbsUserLoginTime;
import com.huiyee.esite.dto.CardDto;
import com.huiyee.esite.dto.VisitLogDto;
import com.huiyee.esite.dto.VisitPageTime;
import com.huiyee.esite.model.OwnerSetting;
import com.huiyee.weixin.model.WxMp;

public class CacheUtil
{
  public static Map<String, CardDto> psds=new ConcurrentHashMap<String, CardDto>();//卡片模块的缓存,key为pcid
  
  public static Map<Long, CardDto> pgds=new ConcurrentHashMap<Long, CardDto>();//页面卡片的缓存,key为pageid
  
  public static Map<Long, String> htmls=new ConcurrentHashMap<Long, String>();//空白页面html的缓存,key为pageid
  
  public static List<VisitLogDto> vlogs=new ArrayList<VisitLogDto>();//访问记录的日志
  
  public static List<VisitPageTime> vpt=new ArrayList<VisitPageTime>();//页面访问时长
  
  public static List<BbsUserLoginTime> bbs=new ArrayList<BbsUserLoginTime>();//论坛用户登录时长
  
//  public static Map<Long, OwnerSetting> oss=new ConcurrentHashMap<Long, OwnerSetting>();//osetting集合
  
  public static Map<Long, WxMp> wxmps = new HashMap<Long, WxMp>();//公众号的缓存key为mpid
  public static Map<Long, WxMp> wxmpso = new HashMap<Long, WxMp>();//公众号的缓存key为owner
  
  
  public static synchronized void addvpt(VisitPageTime vl)
  {
	  vpt.add(vl);
  }

  public static synchronized List<VisitPageTime> findvpt()
  {
	  List<VisitPageTime> vl=vpt;
	  vpt=new ArrayList<VisitPageTime>();
	  return vl;
  }
  
 //================================================
  
  public static synchronized void addVisitLog(VisitLogDto vl)
  {
	  vlogs.add(vl);
  }

  public static synchronized List<VisitLogDto> findVisitLog()
  {
	  List<VisitLogDto> vl=vlogs;
	  vlogs=new ArrayList<VisitLogDto>();
	  return vl;
  }
  
  //================================================
  
  public static synchronized void addBbs(BbsUserLoginTime vl)
  {
	  bbs.add(vl);
  }

  public static synchronized List<BbsUserLoginTime> findBbs()
  {
	  int size=1000;
	  if(bbs.size()<1000)
	  {
		  size=bbs.size();
	  }
	  List<BbsUserLoginTime> vl= bbs.subList(0, size);
	  return vl;
  }

  public static synchronized void removeBbs(List<BbsUserLoginTime> vl)
  {
	  bbs.removeAll(vl);
  }
  
   //================================================
  
}
