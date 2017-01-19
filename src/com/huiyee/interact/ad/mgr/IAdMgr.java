
package com.huiyee.interact.ad.mgr;

import java.util.List;

import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.AreaProvince;
import com.huiyee.interact.ad.model.Ad;
import com.huiyee.interact.ad.model.AdMedia;
import com.huiyee.interact.ad.model.Adwd;

public interface IAdMgr
{

	public Ad findAdByOwner(Account account);

	public int saveAd(Account account);

	public IDto findQrListInfo(Account account, String qwd, String media, String wd, int pageId);

	public List<AreaProvince> findAreaProvince(Account account, String qwd);

	public List<Adwd> findWdList(Account account, String wd, String type);

	public List<AdMedia> findMediaList(Account account, String media);

	public long saveAdWay(Account account, String media, String qwd, String wd, long gid, String url, String fsurl,String type,int num) throws Exception;

	public int updateAdWay(Account account,long wayid, String media, String qwd, String wd, long gid, String url, String fsurl,String type,int num) throws Exception;

	public int updateMediaWay(Account account,long wayid, String qwd, String wd, long gid, String url, String fsurl) throws Exception;

	public IDto findAdWayList(Account account, long wayid, int pageId);

	public int saveWaygg(Account account, long wayid, String ggids);

}
