package com.huiyee.esite.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;

import sun.misc.BASE64Decoder;

import com.google.gson.Gson;
import com.huiyee.esite.compose.IFeatureCompose;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dto.DynamicActionDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.ContentPicture;
import com.huiyee.esite.model.UserUpload;
import com.huiyee.esite.model.UserUploadRecord;
import com.huiyee.esite.service.FileUploadService;
import com.huiyee.esite.util.HttpRequestDeviceUtils;
import com.huiyee.esite.util.HyConfig;
import com.opensymphony.xwork2.ActionContext;

public class MusicUploadAction extends AbstractAction {

	private static final long serialVersionUID = 1518177104254048441L;
	private String name;
	public void setName(String name) {
		this.name = name;
	}
	private IFeatureCompose featureCompose;
	private long featureid;
	private long fid;
	private long pageid;
	
	
	public String musicUpload() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Access-Control-Allow-Origin", HyConfig.getPageyuming(this.getOwner()));
		response.addHeader("Access-Control-Allow-Methods", "HEAD,GET,POST,PUT,DELETE,OPTIONS");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type,Origin,Accept");
        response.addHeader("Access-Control-Max-Age", "120");
		PrintWriter out = response.getWriter();
		HttpServletRequest request = ServletActionContext.getRequest();
		if("OPTIONS".equals(request.getMethod())){
			return null;
		}
		long ownerid = this.getOwner();
		if(ownerid==0)
		{
		if(fid == 0){
			ownerid = pageCompose.findOwnerByPageid(pageid);
		}else{
			ownerid = featureCompose.findOwneridByPageidAndFid(pageid, fid);//ownerid			
		}
		}
		String path = FileUploadService.getFilePath(IPageConstants.TYPE_FEATURE, ownerid, featureid+"");//Â·¾¶
		String fname = FileUploadService.createFileName(name);
		String filepath=HyConfig.getRootPath()+path;
		File dir = new File(filepath);
		if (!dir.exists())
		{
			dir.mkdirs();
		}
		File file = new File(filepath, fname);
        FileOutputStream fout = new FileOutputStream(file);
        IOUtils.copy(request.getInputStream(), fout);
        fout.flush();
		fout.close();
		String picUrl = path+"/"+fname;
		out.print("{\"status\":1,\"picUrl\":\""+picUrl+"\",\"picName\":\""+name+"\"}");
		out.flush();
		out.close();
		return null;
	}


	public void setFeatureCompose(IFeatureCompose featureCompose) {
		this.featureCompose = featureCompose;
	}
	

}
