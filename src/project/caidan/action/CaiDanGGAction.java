package project.caidan.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import project.caidan.mgr.ICaiDanGGMgr;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.interact.ad.dto.AdGGDto;
import com.huiyee.interact.ad.mgr.IAdGGMgr;
import com.huiyee.interact.ad.model.Adgg;


public class CaiDanGGAction extends AbstractAction
{
	private static final long serialVersionUID = -9211368138073008413L;
	private IAdGGMgr adGGMgr;
	private ICaiDanGGMgr cdGGMgr;
	
	private AdGGDto dto;
	private int pageId = 1;
	private int lightType = 1;
	private long ggid;
	private Adgg adgg;
	private String ids;
	private List<Long> ggids;
	private long id;//投放广告关系表id
	private int type;
	
	public void setCdGGMgr(ICaiDanGGMgr cdGGMgr)
	{
		this.cdGGMgr = cdGGMgr;
	}

	public void setAdGGMgr(IAdGGMgr adGGMgr)
	{
		this.adGGMgr = adGGMgr;
	}
	
	@Override
	public String execute() throws Exception
	{
		dto = (AdGGDto) adGGMgr.findAdGGListByOwner(this.getOwner(),pageId);
		ggids = cdGGMgr.findGGidsByOwner(this.getOwner());
		return SUCCESS;
	}
	
	public String addGG() throws Exception{
		return SUCCESS;
	}
	
	public String saveGG() throws Exception{
		adgg.setOwner(this.getOwner());
		adGGMgr.saveGG(adgg);
		return SUCCESS;
	}
	
	public String editGG() throws Exception{
		adgg = adGGMgr.findadGGById(ggid);
		return SUCCESS;
	}
	
	public String updateGG() throws Exception{
		adgg.setOwner(this.getOwner());
		adGGMgr.updateGG(adgg);
		return SUCCESS;
	}
	
	public String delGG() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		long result = cdGGMgr.delTfGGByGGid(ggid, this.getOwner(), type);
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}
	
	public String tfgg() throws Exception
	{
		cdGGMgr.saveTfGG(ids,this.getOwner(),type);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String result = "Y";
		out.write(result);
		out.flush();
		out.close();
		return null;
	}
	
	public String tfggList() throws Exception{
		lightType = 2;
		dto = (AdGGDto) cdGGMgr.findtfGGsByOwner(this.getOwner(),pageId,type);
		return SUCCESS;
	}
	
	public String ggUp() throws Exception {
		long result = cdGGMgr.updateGGUp(id, this.getOwner());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 上移
	 * 
	 * @return
	 * @throws Exception
	 */
	public String ggDown() throws Exception {
		long result = cdGGMgr.updateGGDown(id, this.getOwner());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}
	
	
	public String delTfGG() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		long result = cdGGMgr.delTfGGById(id,this.getOwner());
		Gson gson = new Gson();
		out.print(gson.toJson(result));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 前台展示广告列表
	 * @return
	 * @throws Exception
	 */
	public String findGuanggaoListByOwner() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		dto = (AdGGDto) cdGGMgr.findtfGGsByOwner(this.getOwner(),type);
		Gson gson = new Gson();
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}
	
	
	public AdGGDto getDto()
	{
		return dto;
	}
	
	public void setDto(AdGGDto dto)
	{
		this.dto = dto;
	}

	public int getPageId()
	{
		return pageId;
	}

	
	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public int getLightType()
	{
		return lightType;
	}

	
	public void setLightType(int lightType)
	{
		this.lightType = lightType;
	}

	
	public long getGgid()
	{
		return ggid;
	}

	
	public void setGgid(long ggid)
	{
		this.ggid = ggid;
	}

	
	public Adgg getAdgg()
	{
		return adgg;
	}

	
	public void setAdgg(Adgg adgg)
	{
		this.adgg = adgg;
	}

	
	public String getIds()
	{
		return ids;
	}

	
	public void setIds(String ids)
	{
		this.ids = ids;
	}

	
	public List<Long> getGgids()
	{
		return ggids;
	}

	
	public void setGgids(List<Long> ggids)
	{
		this.ggids = ggids;
	}

	
	public long getId()
	{
		return id;
	}

	
	public void setId(long id)
	{
		this.id = id;
	}

	
	public int getType()
	{
		return type;
	}

	
	public void setType(int type)
	{
		this.type = type;
	}
	
	
}
