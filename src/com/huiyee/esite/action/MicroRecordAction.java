package com.huiyee.esite.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONObject;

import com.huiyee.esite.dto.ContentRecordDto;
import com.huiyee.esite.mgr.IContentRecordMgr;
import com.huiyee.esite.model.ContentRecord;


public class MicroRecordAction extends AbstractAction
{
	private static final long serialVersionUID = -5615120671435058354L;
	private IContentRecordMgr contentRecordMgr;
	private ContentRecordDto dto;
	private int pageId = 1;
	private int utype; //0-sina 1-weixin
	private String type = "N";
	private long crid;
	private ContentRecord contentRecord;
	private HashMap<String, String> map;
	private long uid;
	private String title;
	
	public void setContentRecordMgr(IContentRecordMgr contentRecordMgr)
	{
		this.contentRecordMgr = contentRecordMgr;
	}

	@Override
	public String execute() throws Exception
	{
		dto = (ContentRecordDto)pageCompose.findContentRecordList(pageId,type,utype,uid,title);
		return SUCCESS;
	}
	
	/**
	 * …Û∫À
	 * @return
	 * @throws Exception
	 */
	public String microRecordChecked() throws Exception{
		contentRecord = contentRecordMgr.findContentRecordById(crid);
		return SUCCESS;
	}
	
	
	public String updateMicroRecord() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String result = "N";
		int res = contentRecordMgr.updateMicroRecord(crid,contentRecord);
		if(res > 0){
			result = "Y";
		}
		out.print(result);
		out.flush();
		out.close();
		return null;
	}
	/**
	 * œÍœ∏–≈œ¢
	 * @return
	 * @throws Exception
	 */
	public String microRecordDetail() throws Exception{
		HashMap<String, String> data = new HashMap<String, String>();  
		contentRecord = contentRecordMgr.findContentRecordById(crid);
		if(contentRecord != null && StringUtils.isNotBlank(contentRecord.getLongdesc())){
			String object = contentRecord.getLongdesc();
		       JSONObject jsonObject = JSONObject.fromObject(object);  
		       Iterator it = jsonObject.keys();  
		       while (it.hasNext())  
		       {  
		           String key = String.valueOf(it.next());  
		           String value = (String) jsonObject.get(key);  
		           data.put(key, value);  
		       }  
		}
		map = data;
		return SUCCESS;
	}
	
	public ContentRecordDto getDto()
	{
		return dto;
	}

	public void setDto(ContentRecordDto dto)
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
	
	public int getUtype()
	{
		return utype;
	}

	public void setUtype(int utype)
	{
		this.utype = utype;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	
	public long getCrid()
	{
		return crid;
	}

	
	public void setCrid(long crid)
	{
		this.crid = crid;
	}

	
	public ContentRecord getContentRecord()
	{
		return contentRecord;
	}

	
	public void setContentRecord(ContentRecord contentRecord)
	{
		this.contentRecord = contentRecord;
	}

	
	public HashMap<String, String> getMap()
	{
		return map;
	}

	
	public void setMap(HashMap<String, String> map)
	{
		this.map = map;
	}

	
	public long getUid()
	{
		return uid;
	}

	
	public void setUid(long uid)
	{
		this.uid = uid;
	}

	
	public String getTitle()
	{
		return title;
	}

	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	
}
