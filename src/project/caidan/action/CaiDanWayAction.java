
package project.caidan.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import project.caidan.mgr.ICaiDanMediaMgr;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.Sitegroup;
import com.huiyee.interact.ad.dto.AdDto;
import com.huiyee.interact.ad.mgr.IAdMediaMgr;
import com.huiyee.interact.ad.mgr.IAdMgr;
import com.huiyee.interact.ad.model.Ad;
import com.huiyee.interact.ad.model.AdMedia;
import com.huiyee.interact.ad.model.AdWay;
import com.opensymphony.xwork2.ActionContext;

public class CaiDanWayAction extends AbstractAction
{
	private static final long serialVersionUID = -8309970037545692201L;
	private IAdMgr adMgr;
	private IAdMediaMgr adMediaMgr;
	private ICaiDanMediaMgr cdMediaMgr;
	private int lightType;
	private AdDto dto;
	private String qwd;
	private String wd;
	/**
	 * 维度Type BBN-版本；QHO-期号
	 */
	private String type;
	private String media;
	private int pageId = 1;
	private long gid;
	private List<Sitegroup> list;
	private long wayid;
	private String ggids;
	private String url;
	private String fsurl;
	
	private long pageid;
	private AdWay adway;
	private List<AdMedia> adMediaList;
	
	private int num;
	
	public void setAdMediaMgr(IAdMediaMgr adMediaMgr)
	{
		this.adMediaMgr = adMediaMgr;
	}

	
	public void setAdway(AdWay adway)
	{
		this.adway = adway;
	}

	public void setCdMediaMgr(ICaiDanMediaMgr cdMediaMgr)
	{
		this.cdMediaMgr = cdMediaMgr;
	}

	/**
	 * 已开通时显示二维码 未开通是显示开通按钮
	 */
	public String index() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		Ad ad = adMgr.findAdByOwner(account);
		if (ad != null)
			return "toQR";
		else
			return SUCCESS;
	}

	/**
	 * 开通AD 创建实例
	 * 
	 * @return
	 * @throws Exception
	 */
	public String open() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		int rs = adMgr.saveAd(account);
		return SUCCESS;
	}

	public String adQrList() throws Exception
	{
		lightType = 1;
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (AdDto) adMgr.findQrListInfo(account, qwd, media, wd, pageId);
		list = pageCompose.findGroupTofs(account, IPageConstants.SITE_GROUP_D);
		return SUCCESS;
	}

/**
 * 新增维度
 * @return
 * @throws Exception
 */
	public String saveAdWay() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("json/application;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String msg;
		try
		{	
			pageid = cdMediaMgr.findEWMPageidByType("EWM");
			long rs = cdMediaMgr.saveAdWay(account, media, qwd, wd, pageid, url, fsurl,type,num);
			msg = String.valueOf(rs);
		} catch (Exception e)
		{
			msg = e.getMessage();
		}
		out.print(msg);
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 编辑
	 * @return
	 * @throws Exception
	 */
	public String showAdWay() throws Exception{
		adway = adMediaMgr.findAdWayById(wayid,this.getOwner());
		adMediaList = adMediaMgr.findAdMediaListByOwner(this.getOwner());
		return SUCCESS;
	}
	
	/**
	 * 编辑保存
	 * @return
	 * @throws Exception
	 */
	public String editAdWay() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("json/application;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String msg;
		try
		{	
			pageid = cdMediaMgr.findEWMPageidByType("EWM");
			int rs = cdMediaMgr.updateAdWay(account, wayid, media, qwd, wd, pageid, url, fsurl,type,num);
			msg = String.valueOf(rs);
		} catch (Exception e)
		{
			msg = e.getMessage();
		}
		out.print(msg);
		out.flush();
		out.close();
		return null;
	}
	
	public String adWayggSet() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		dto = (AdDto) adMgr.findAdWayList(account, wayid,pageId);
		return SUCCESS;
	}
	
	public String saveWaygg() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("json/application;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int rs = adMgr.saveWaygg(account, wayid, ggids);
		out.print(new Gson().toJson(rs > 0 ? "Y" : "N"));
		out.flush();
		out.close();
		return null;

	}
	
	public String editMediaWay() throws Exception
	{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("json/application;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String msg;
		try
		{
			int rs = adMgr.updateMediaWay(account,wayid, qwd, wd, gid, url,fsurl);
			msg = String.valueOf(rs);
		} catch (Exception e)
		{
			msg = e.getMessage();
		}
		out.print(msg);
		out.flush();
		out.close();
		return null;
	}

	public void setAdMgr(IAdMgr adMgr)
	{
		this.adMgr = adMgr;
	}

	public int getLightType()
	{
		return lightType;
	}

	public void setLightType(int lightType)
	{
		this.lightType = lightType;
	}

	public AdDto getDto()
	{
		return dto;
	}

	public void setDto(AdDto dto)
	{
		this.dto = dto;
	}

	public String getWd()
	{
		return wd;
	}

	public void setWd(String wd)
	{
		this.wd = wd;
	}

	public String getMedia()
	{
		return media;
	}

	public void setMedia(String media)
	{
		this.media = media;
	}

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public List<Sitegroup> getList()
	{
		return list;
	}

	public void setList(List<Sitegroup> list)
	{
		this.list = list;
	}

	public long getGid()
	{
		return gid;
	}

	public void setGid(long gid)
	{
		this.gid = gid;
	}

	public long getWayid()
	{
		return wayid;
	}

	public void setWayid(long wayid)
	{
		this.wayid = wayid;
	}

	public String getGgids()
	{
		return ggids;
	}

	public void setGgids(String ggids)
	{
		this.ggids = ggids;
	}

	public String getQwd()
	{
		return qwd;
	}

	public void setQwd(String qwd)
	{
		this.qwd = qwd;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	
	public String getUrl()
	{
		return url;
	}

	
	public void setUrl(String url)
	{
		this.url = url;
	}

	
	public String getFsurl()
	{
		return fsurl;
	}

	
	public void setFsurl(String fsurl)
	{
		this.fsurl = fsurl;
	}

	
	public long getPageid()
	{
		return pageid;
	}

	
	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}


	
	public AdWay getAdway()
	{
		return adway;
	}


	
	public int getNum()
	{
		return num;
	}


	
	public void setNum(int num)
	{
		this.num = num;
	}


	
	public List<AdMedia> getAdMediaList()
	{
		return adMediaList;
	}


	
	public void setAdMediaList(List<AdMedia> adMediaList)
	{
		this.adMediaList = adMediaList;
	}

}
