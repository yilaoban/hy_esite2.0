package com.huiyee.esite.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.dto.MoveToGroupDto;
import com.huiyee.esite.mgr.IMoveToGroupMgr;
import com.huiyee.esite.mgr.IWeiXinMgr;
import com.huiyee.esite.model.WxGroup;
import com.huiyee.weixin.model.WxMp;


public class WxMoveToGroupAction extends InteractModelAction
{
	private static final long serialVersionUID = -1924522167623861326L;
	private MoveToGroupDto dto;
	private IMoveToGroupMgr moveToGroupMgr;
	private List<WxGroup> wxGroupList;
	private IWeiXinMgr weiXinMgr;
	private long mpid;
	private String name;
	private String result;
	private int type;//2:新建组 ，1 选择已经存在组
	private long xcid;
	
	
	public void setWeiXinMgr(IWeiXinMgr weiXinMgr)
	{
		this.weiXinMgr = weiXinMgr;
	}

	public void setDto(MoveToGroupDto dto)
	{
		this.dto = dto;
	}
	
	public void setMoveToGroupMgr(IMoveToGroupMgr moveToGroupMgr)
	{
		this.moveToGroupMgr = moveToGroupMgr;
	}

	@Override
	public String execute() throws Exception
	{
		
		return SUCCESS;
	}

	public String saveWxGroupJob() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		Gson gson = new Gson();
		if(dto != null){
			if (type == 2) {
				long groupid = moveToGroupMgr.saveCrmWxGroup(mpid, name);
				dto.setAsigngid(groupid);
			}
			String jobrule = gson.toJson(dto);
			String jtype = "REM";
			if(type == 3){//移动至潜客
				name = "潜客";
				jtype = "HUB";
				dto.setAsigngid(0);
			}
			if(dto.getXcid() > 0){
				jtype = "XCQ";
			}
			long res = moveToGroupMgr.saveWxGroupJob(mpid,name,jobrule,jtype);
			if(res > 0){
				result = "Y";
			}
		}else{
			result = "操作失败";
		}
		return SUCCESS;
	}
	
	public  String findWxGroupList() throws Exception{
		String msg = dto.getArea();
		String str=new String(msg.getBytes("ISO-8859-1"),"UTF-8");
		dto.setArea(str);
		WxMp woa = weiXinMgr.updateFindWxMp(this.getOwner(), 0);
		if (woa != null) {
			mpid = woa.getId();
			wxGroupList = moveToGroupMgr.findWxGroupList(mpid);
		}
		return SUCCESS;
	}
	
	
	
	public List<WxGroup> getWxGroupList()
	{
		return wxGroupList;
	}

	
	public void setWxGroupList(List<WxGroup> wxGroupList)
	{
		this.wxGroupList = wxGroupList;
	}

	
	
	public long getMpid()
	{
		return mpid;
	}

	
	public void setMpid(long mpid)
	{
		this.mpid = mpid;
	}

	public MoveToGroupDto getDto()
	{
		return dto;
	}

	
	public String getName()
	{
		return name;
	}

	
	public void setName(String name)
	{
		this.name = name;
	}

	
	public String getResult()
	{
		return result;
	}

	
	public void setResult(String result)
	{
		this.result = result;
	}

	
	public int getType()
	{
		return type;
	}

	
	public void setType(int type)
	{
		this.type = type;
	}

	
	public long getXcid()
	{
		return xcid;
	}

	
	public void setXcid(long xcid)
	{
		this.xcid = xcid;
	}
	
}
