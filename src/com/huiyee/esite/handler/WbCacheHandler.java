package com.huiyee.esite.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.huiyee.esite.action.ShowPageAction;
import com.huiyee.esite.action.ShowPageAction2;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.job.WbCacheJob;
import com.huiyee.esite.util.DateUtil;
public class WbCacheHandler extends Handler
{

	/**
     * 
     */
    private static final long serialVersionUID = -6022164370385283918L;

    @Override
	public void work(Object o)
	{
		try
		{
			dowork(o);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		//WbCacheJob.removeJobCahe(1);
	}

	private void dowork(Object o)
	{
//	    Map<String, String> map=(Map<String, String>)o;
//	    List<String> keylist=new ArrayList<String>();
//	    for (String key : map.keySet()) {
//            String value=map.get(key);
//            String datestring=value.split(",")[1];
//            long wbtime=Long.parseLong(datestring);//time
//            long nowtime=new Date().getTime();
//            if((nowtime-wbtime)>IPageConstants.WB_CACHE_PEROID){
//                keylist.add(key);
//            }
//        }
//	    for (String str : keylist) {
//	        ShowPageAction2.removeWbCahe(str);
//        }
//	    System.out.println(map.size());
	    
	}
}
