package com.huiyee.interact.EmailPeriodical.mgr;

import java.util.List;

import com.huiyee.interact.EmailPeriodical.dao.IEmailPeriodicalDao;
import com.huiyee.interact.EmailPeriodical.model.EmailPeriodicalModel;


public class EmailPeriodicalManagerImpl implements IEmailPeriodicalManager{
	
	private IEmailPeriodicalDao emailPeriodicalDao;
	
	public IEmailPeriodicalDao getEmailPeriodicalDao() {
		return emailPeriodicalDao;
	}

	public void setEmailPeriodicalDao(IEmailPeriodicalDao emailPeriodicalDao) {
		this.emailPeriodicalDao = emailPeriodicalDao;
	}

	@Override
	public long saveEmailPeriodical(String title,String url,String status,String publish, long ownerid) {
		int result=0;
		long id=emailPeriodicalDao.saveEmailPeriodical(title, url, status, publish, ownerid);
		if(id>0){
			result=1;
		}
		return result;
	}

	@Override
	public List<EmailPeriodicalModel> searchEmailPeriodical(long ownerid,
			int start, int size) {
		return emailPeriodicalDao.searchEmailPeriodical(ownerid, start, size);
	}

	@Override
	public int searchEmailPeriodicalTotal(long ownerid) {
		return emailPeriodicalDao.searchEmailPeriodicalTotal(ownerid);
	}

	@Override
	public int updatePublish(long id,long ownerid) {
		int result=emailPeriodicalDao.updatePublishByOwnerid(ownerid);
		if(result>0){
			result=emailPeriodicalDao.updatePublishById(id);
		}
		return result;
	}

	@Override
	public int deleteEmailPeriodical(long id) {
		return emailPeriodicalDao.deleteEmailPeriodical(id);
	}

	@Override
	public EmailPeriodicalModel findemailpublishByLpid(long id)
	{
		return emailPeriodicalDao.findemailpublishByLpid(id);
	}

	@Override
	public long updateemailpublish(String title, String url,long id)
	{
		return emailPeriodicalDao.updateemailpublish(title,url,id);
	}


	

}
