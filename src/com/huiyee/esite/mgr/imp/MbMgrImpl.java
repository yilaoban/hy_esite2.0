package com.huiyee.esite.mgr.imp;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.huiyee.esite.dao.IMbDao;
import com.huiyee.esite.mgr.IMbMgr;
import com.huiyee.esite.model.Mb;
import com.huiyee.esite.model.Tag;


public class MbMgrImpl implements IMbMgr
{
	private IMbDao mbDao;

	public void setMbDao(IMbDao mbDao)
	{
		this.mbDao = mbDao;
	}

	@Override
	public List<Mb> findMbList(String type, long tagid)
	{
		return mbDao.findMbList(type, tagid);
	}

	@Override
	public List<Tag> findTagList(String type)
	{
		return mbDao.findTagList(type);
	}

	@Override
	public int saveMb(Mb mb)
	{
		long mbid = mbDao.saveMb(mb);
		if(mb.getPages() != null && mb.getPages().size()>0){
			for(Long p : mb.getPages()){
				if(p>0){
					mbDao.saveMbPage(mbid, p);
				}
			}
		}
		if(mb.getTags()!=null && mb.getTags().size()>0){
			for(String t : mb.getTags()){
				if(StringUtils.isNotEmpty(t)){
					int count = mbDao.findMbTag(mbid, t);
					if(count == 0 ){
						long tid = mbDao.findTag(t,mb.getType());
						if(tid ==0){
							tid = mbDao.saveTag(t,mb.getType());
						}
						mbDao.saveMbTag(mbid, tid);
					}
				}
			}
		}
		if(mbid >0 ){
			return 1;
		}else{
			return 0;
		}
	}

	@Override
	public int findTotalMb(String type)
	{
		return mbDao.findTotalMb(type);
	}
	
}
