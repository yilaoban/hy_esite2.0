package com.huiyee.bdmap.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.huiyee.bdmap.dto.BDLBSDto;
import com.huiyee.bdmap.dto.BDLBSRs;
import com.huiyee.bdmap.dto.BDPoint;
import com.huiyee.bdmap.utils.BaiDuMapApp;
import com.huiyee.bdmap.utils.JsonStringUtil;
import com.huiyee.esite.util.HttpTookit;

public class BDLBSDao implements IBDLBSDao
{

	public static void main(String[] args)
	{
		BDLBSDao dd=new BDLBSDao();
		String str="{            'name':'无锡(国家hzm)软件园(震泽路)',            'location':{                'lat':31.494355,                'lng':120.38016            },            'address':'震泽路18号',            'street_id':'07f3c8b5f56de11e6240a91e',            'uid':'07f3c8b5f56de11e6240a91e'        }";
		BDPoint bdp=(BDPoint) JsonStringUtil.String2Obj(str, BDPoint.class); 
		//BDLBSRs rs=dd.saveLBS(bdp, "MYMO");
		//BDLBSRs rs=dd.updateLBS(757355991,bdp, "MYMO");
		//System.out.println(rs.getMessage()+","+rs.getId());
		
		List<BDLBSDto> ls=dd.findLBS("120.393655", "31.480821", "MYMO", 30000,0);
		System.out.println(ls.size());
				
	}
	
	@Override
	public  BDLBSRs saveLBS(BDPoint bdp,String tags,String hykey)
	{
		String url="http://api.map.baidu.com/geodata/v3/poi/create";
		Map<String, String> params=new HashMap<String, String>();
		params.put("title", bdp.getName());
		params.put("geotable_id",BaiDuMapApp.geotable_id+"");
		params.put("address", bdp.getAddress());
		params.put("latitude",bdp.getLocation().getLat()+"");
		params.put("longitude",bdp.getLocation().getLng()+"");
		params.put("coord_type","3");
		params.put("ak", BaiDuMapApp.ak);
		params.put("tags", tags);
		params.put("hykey", hykey);
		String rs=HttpTookit.doPost(url, params, "utf-8", false);
		return (BDLBSRs) JsonStringUtil.String2Obj(rs,BDLBSRs.class );
	}

	@Override
	public BDLBSRs updateLBS(long id, BDPoint bdp,String tags)
	{
		String url="http://api.map.baidu.com/geodata/v3/poi/update";
		Map<String, String> params=new HashMap<String, String>();
		params.put("id", id+"");
		params.put("title", bdp.getName());
		params.put("geotable_id",BaiDuMapApp.geotable_id+"");
		params.put("address", bdp.getAddress());
		params.put("latitude",bdp.getLocation().getLat()+"");
		params.put("longitude",bdp.getLocation().getLng()+"");
		params.put("coord_type","3");
		params.put("ak", BaiDuMapApp.ak);
		params.put("tags", tags);
		String rs=HttpTookit.doPost(url, params, "utf-8", false);
		return (BDLBSRs) JsonStringUtil.String2Obj(rs,BDLBSRs.class );
	}
	
	@Override
	public BDLBSRs deleteLBS(long id)
	{
		String url="http://api.map.baidu.com/geodata/v3/poi/delete";
		Map<String, String> params=new HashMap<String, String>();
		params.put("id", id+"");
		params.put("geotable_id",BaiDuMapApp.geotable_id+"");
		params.put("ak", BaiDuMapApp.ak);
		String rs=HttpTookit.doPost(url, params, "utf-8", false);
		return (BDLBSRs) JsonStringUtil.String2Obj(rs,BDLBSRs.class );
	}

	@Override
	public List<BDLBSDto> findLBS(String x, String y, String tags, int ra,int limit)
	{
		String lt="";
		if(limit>0){
			lt="&page_size="+limit;
		}
		String str2= HttpTookit.doGet("http://api.map.baidu.com/geosearch/v3/nearby", "geotable_id="+BaiDuMapApp.geotable_id+"&location="+x+","+y+"&radius="+ra+"&ak="+BaiDuMapApp.ak+"&tags="+tags+"&sortby=distance:1"+lt, "utf-8", false);
		JSONObject jo=JSONObject.fromObject(str2);
		List<BDLBSDto> ls=(List<BDLBSDto>) jo.getJSONArray("contents").toCollection(jo.getJSONArray("contents"), BDLBSDto.class);
		return ls;
	
	}

}
