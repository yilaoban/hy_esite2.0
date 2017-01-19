package com.huiyee.interact.EmailPeriodical.mgr;

import java.util.List;

import com.huiyee.interact.EmailPeriodical.model.EmailPeriodicalModel;
import com.huiyee.interact.lottery.model.LotteryPrize;


public interface IEmailPeriodicalManager {

	public long saveEmailPeriodical(String title,String url,String status,String publish,long ownerid);
	
	public List<EmailPeriodicalModel> searchEmailPeriodical(long ownerid,int start,int size);
	
	public int searchEmailPeriodicalTotal(long ownerid);
	
	public int deleteEmailPeriodical(long id);
	
	public int updatePublish(long id,long ownerid);
	
	public EmailPeriodicalModel findemailpublishByLpid(long id);
	
	public long updateemailpublish(String title,String url,long id);
	

}
