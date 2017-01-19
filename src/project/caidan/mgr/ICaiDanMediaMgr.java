package project.caidan.mgr;

import com.huiyee.esite.model.Account;


public interface ICaiDanMediaMgr
{
	public long findEWMPageidByType(String type);
	
	public long saveAdWay(Account account, String media, String qwd, String wd, long pageid, String url, String fsurl,String type,int num) throws Exception;
	
	public int updateAdWay(Account account,long wayid, String media, String qwd, String wd, long pageid, String url, String fsurl,String type,int num) throws Exception;
}
