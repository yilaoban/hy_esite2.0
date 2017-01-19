package com.huiyee.esite.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dto.FeatureActionDto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.Account;

public class FeatureAction extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7728806655339813276L;
	private String type = IPageConstants.SITE_TYPE_W;
	private FeatureActionDto dto ;
	private long pageid;
	private String result;
	private long featureId;
	private long fid;
	private String fname;
    private long pfid;
    private long siteid;
	
	//page列表
	@Override
	public String execute() throws Exception {
		dto = (FeatureActionDto) pageCompose.composeFeatureAction(pageid);
		return SUCCESS;
	}
	public String getFeatures() throws Exception {
        dto = (FeatureActionDto) pageCompose.composeFeatureBySiteid(siteid);
        return SUCCESS;
    }
	
	//删除page
	public String del_page() throws Exception{
		int result = pageCompose.composeDelPage(pageid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}
	
	//修改功能名称
    public String updateName() throws Exception{
        int result=0;
        if(fname!=null&&!"".equals(fname)){
            result = pageCompose.updateName(pfid, fname);
        }else{
            result=2;//名称为空
        }
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
        return null;
    }
	
	//创建page
	public String page_create() throws Exception{
		return SUCCESS;
	}
	
	//创建page保存
	public String page_create_sub() throws Exception{
		return SUCCESS;
	}
	
	//编辑page
	public String page_update() throws Exception{
		return SUCCESS;
	}
	
	//编辑page保存
	public String page_update_sub() throws Exception{
		return SUCCESS;
	}
	
	public long getPageid() {
		return pageid;
	}

	public void setPageid(long pageid) {
		this.pageid = pageid;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public FeatureActionDto getDto() {
		return dto;
	}

	public void setDto(FeatureActionDto dto) {
		this.dto = dto;
	}

	public void setFeatureId(long featureId) {
		this.featureId = featureId;
	}

	public void setFid(long fid) {
		this.fid = fid;
	}

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setPfid(long pfid) {
        this.pfid = pfid;
    }
    public void setSiteid(long siteid) {
        this.siteid = siteid;
    }

}
