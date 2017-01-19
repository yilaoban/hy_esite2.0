package project.caidan.dao;

import java.util.List;

import project.caidan.model.CDNews;


public interface ICaiDanNewsDao
{

	public List<CDNews> findList(long owner, String accountType, int start, int size);

	public int findTotal(long owner, String accountType);

	public void saveNews(long ownerid, String accountType, long pid);
	
	public int findMaxIdx(long owner,String accountType);

	public CDNews findcdNews(long contentid, long owner);

	public CDNews findFrontcdNews(int idx, int type, long owner);

	public int updatecdNews(CDNews current);

	public CDNews findNextcdNews(int idx, int type, long owner);

	public List<CDNews> findForCaiMinPage(long owner, int start, int size);

	public List<CDNews> findForStation(long owner, int level, int start, int size);

}
