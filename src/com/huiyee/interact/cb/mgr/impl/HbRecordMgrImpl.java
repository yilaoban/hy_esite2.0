package com.huiyee.interact.cb.mgr.impl;

import java.math.BigDecimal;
import java.util.List;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.interact.cb.dao.ICbSenderDao;
import com.huiyee.interact.cb.dao.IHbRecordDao;
import com.huiyee.interact.cb.dto.HbRecordDto;
import com.huiyee.interact.cb.mgr.IHbRecordMgr;
import com.huiyee.interact.cb.model.CbHbRecord;
import com.huiyee.interact.cb.model.CbSender;


public class HbRecordMgrImpl implements IHbRecordMgr
{
	private IHbRecordDao hbRecordDao;
	private ICbSenderDao cbSenderDao;
	
	public void setCbSenderDao(ICbSenderDao cbSenderDao)
	{
		this.cbSenderDao = cbSenderDao;
	}

	public void setHbRecordDao(IHbRecordDao hbRecordDao)
	{
		this.hbRecordDao = hbRecordDao;
	}

	
	public static double sub(double v1,double v2){   
        BigDecimal b1 = new BigDecimal(Double.toString(v1));   
        BigDecimal b2 = new BigDecimal(Double.toString(v2));   
        return b1.subtract(b2).doubleValue();   
    }  	
	
	 public static double div(double v1,double v2){   
	        return div(v1,v2,2);   
	 }  
	
	 public static double div(double v1,double v2,int scale){   
        if(scale<0){   
            throw new IllegalArgumentException(   
            "The scale must be a positive integer or zero");   
        }   
        BigDecimal b1 = new BigDecimal(Double.toString(v1));   
        BigDecimal b2 = new BigDecimal(Double.toString(v2));   
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();   
	 }
	 
	 
	@Override
	public HbRecordDto findHbRecordList(long sender, int pageId)
	{
		HbRecordDto dto = new HbRecordDto();
		CbSender cbSender =  hbRecordDao.findHbSender(sender);
		if(cbSender != null){
			double total = cbSender.getHbtotal();
			double used = cbSender.getHbused();
			double balance = sub(total,used);
			total = div(total,100);
			balance = div(balance,100);
			dto.setBalance(balance);
			dto.setTotal(total);
			dto.setCbSender(cbSender);
		}
		int total = hbRecordDao.findTotalHbRecordList(sender);
		if(total > 0){
			int start = IPageConstants.CB_SENDER_RECORD * (pageId - 1);
			List<CbHbRecord> list = hbRecordDao.findHbRecordList(sender,start,IPageConstants.CB_SENDER_RECORD);
			if(list != null && list.size()>0){
				dto.setCbHbRecordList(list);
			}else{
				if(pageId -1 > 0){
					pageId = pageId - 1;
					start = IPageConstants.CB_SENDER_RECORD * (pageId - 1);
					list = hbRecordDao.findHbRecordList(sender,start,IPageConstants.CB_SENDER_RECORD);
					if(list != null && list.size()>0){
						dto.setCbHbRecordList(list);
					}
				}
			}
		}
		dto.setPageId(pageId);
		return dto;
	}

	@Override
	public CbSender findHbSender(long sender)
	{
		return hbRecordDao.findHbSender(sender);
	}


}
