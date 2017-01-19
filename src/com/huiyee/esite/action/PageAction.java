package com.huiyee.esite.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.compose.IFeatureCompose;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dto.PageActionDto;
import com.huiyee.esite.dto.PageTemplateDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.util.GProcessHtml;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.esite.util.PageFileUtil;
import com.huiyee.esite.util.PageReader;
import com.huiyee.esite.util.UrlReader;
import com.opensymphony.xwork2.ActionContext;

public class PageAction extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8841839491295257701L;
	/**
	 * 
	 */
	private String type = IPageConstants.SITE_TYPE_W;
	private long siteid;
	private PageActionDto dto ;
	private long pageid;
	private String pagename;
	private String jspname;
	private String result;
	private long sitegroupid;
	private int pageId=1;
	private int sitetype;
	private PageTemplateDto pdto;
	private String ownertempid;	
	private String istemplate;
	private String stype;
	private IFeatureCompose featureCompose;
	private String uploadType;
	private File file;
	private String fileFileName;
	private String fileContentType;
	private String errmsg;//错误信息
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public String getStype()
	{
		return stype;
	}
	public void setStype(String stype)
	{
		this.stype = stype;
	}
	public String getOwnertempid()
	{
		return ownertempid;
	}
	public void setOwnertempid(String ownertempid)
	{
		this.ownertempid = ownertempid;
	}
	public String getIstemplate()
	{
		return istemplate;
	}
	public void setIstemplate(String istemplate)
	{
		this.istemplate = istemplate;
	}
	//page列表
	@Override
	public String execute() throws Exception {
		dto = (PageActionDto) pageCompose.composePageAcion(sitegroupid,type,siteid);
		return SUCCESS;
	}
	//group导航列表
    public String getSites() throws Exception {
        Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
        dto = (PageActionDto) pageCompose.composeSiteAcion(sitegroupid,account, type);
        return SUCCESS;
    }
    
  //owner导航列表
    public String getSitesList() throws Exception {
        Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
        dto = (PageActionDto) pageCompose.composeSites(account, type, pageId);
        sitetype=2;
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
	
	//创建page
	public String page_create() throws Exception{
		long res = featureCompose.composeCreatePage(pagename, jspname, siteid,stype);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(res);
		out.flush();
		out.close();
		return null;
	}
	
	//创建page保存
	public String page_create_sub() throws Exception{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		if(StringUtils.isEmpty(pagename)){
			pdto=(PageTemplateDto) pageCompose.getPageTemplateList(ownerid);
			result="页面名称为空!";
			return SUCCESS;
		}
		if("N".equals(istemplate)){
			if(StringUtils.isEmpty(jspname)){
				result="jsp名称为空！";
				pdto=(PageTemplateDto) pageCompose.getPageTemplateList(ownerid);
				return SUCCESS;
			}
		}
		long res = featureCompose.composeCreatePage(pagename, jspname, siteid,stype);
		if(res==1){
			result="Y";
		}
		return SUCCESS;
	}
	
	//编辑page
	public String page_update() throws Exception{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long ownerid = account.getOwner().getId();
		dto = (PageActionDto) pageCompose.composeUpdatePage(pageid,ownerid);
		return SUCCESS;
	}
	
	//编辑pagename
	public String updatePageName() throws Exception{
		int result=0;
        if(pagename!=null&&!"".equals(pagename)){
            result = pageCompose.updatePageName(pagename, pageid);
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
	
	//修改导航名称
    public String updateSiteName() throws Exception{
        int result=0;
        if(pagename!=null&&!"".equals(pagename)){
            result = pageCompose.updateSiteName(pagename, siteid);
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
	
	//设为首页
	public String setHome() throws Exception{
		int result = pageCompose.composeSetHome(siteid, pageid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}
	
	public String uploadpage() throws Exception{
		return SUCCESS;
	}
	
	public String uploadpagehtml() throws Exception{
		return SUCCESS;
	}
	
	public String fsave() throws Exception{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		if(IPageConstants.PAGE_UPLOAD_TYPE_ZIP.equals(uploadType)){
		    InputStream is = null;
		    try{
				String filename = fileFileName;
				is = new FileInputStream(file);	
				if (filename.endsWith(".zip") == false) {
					errmsg="无法解析此文件,请确认文件格式!";
					result = "N";
					return SUCCESS;
				}
				long ctime = System.currentTimeMillis();
				String outdir = HyConfig.getRootPath() + "/" + "page" + "/"+ account.getOwner().getId()+ "/" + siteid + "/" + ctime;
				try {
					unzip(is, outdir);
				}catch (Exception x) {
					errmsg="无法解析此ZIP 文件,请确认文件格式!";
					result = "N";
					return INPUT;
				}
				try {
					String html = processHTML(outdir, siteid, account.getOwner().getId(), ctime);
					if (html == null) {
						errmsg="找不到对应的HTML 文件,请确认文件格式!";
						result = "N";
						return INPUT;
					}
					pageCompose.composeUploadPage(siteid, pagename, html,getJsStr(outdir),getCssStr(outdir));
					processCSS(outdir,ctime);
				}catch (Exception x) {
					x.printStackTrace();
					errmsg="无法处理文件,请确认文件格式!";
					result = "N";
					return INPUT;
				}
		    } catch (Exception e) {
				errmsg ="errors.upload.size.exceeded";
				result = "N";
				return INPUT;

			}finally{
				try{
					if(is != null){
						is.close();
					}				
				}catch(Exception ex){
				}
			}
			result = "Y";
			return SUCCESS;
		}
		return INPUT;
	}
	
	public String htmlSave() throws Exception{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
	    InputStream is = null;
	    try{
			String filename = fileFileName;
			is = new FileInputStream(file);	
			if (filename.endsWith(".zip") == false) {
				errmsg="无法解析此文件,请确认文件格式!";
				result = "N";
				return SUCCESS;
			}
			long ctime = System.currentTimeMillis();
			String outdir = HyConfig.getTmp()+"/page/"+ account.getOwner().getId()+ "/" + siteid + "/" + ctime;
			try {
				unzip(is, outdir);
			}catch (Exception x) {
				errmsg="无法解析此ZIP 文件,请确认文件格式!";
				result = "N";
				return INPUT;
			}
			try {
				String cssPath = outdir + "/css";
				String jsPath = outdir + "/js";
				String imgPath = outdir + "/img";
				String imgPath1 = outdir + "/image";
				String imgPath2 = outdir + "/images";
				String mpPath = outdir + "/mp";//多媒体
				PageFileUtil.copyFolder(cssPath, HyConfig.getRoot()+"/res/"+siteid+"/css");//复制CSS
				PageFileUtil.copyFolder(jsPath, HyConfig.getRoot()+"/res/"+siteid+"/js");//复制js
				PageFileUtil.copyFolder(imgPath,HyConfig.getRootPath()+"/"+account.getOwner().getId()+"/"+siteid+"/img");//复制image
				PageFileUtil.copyFolder(imgPath1,HyConfig.getRootPath()+"/"+account.getOwner().getId()+"/"+siteid+"/image");//复制image
				PageFileUtil.copyFolder(imgPath2,HyConfig.getRootPath()+"/"+account.getOwner().getId()+"/"+siteid+"/images");//复制image
				PageFileUtil.copyFolder(mpPath,HyConfig.getRootPath()+"/"+account.getOwner().getId()+"/"+siteid+"/mp");//复制多媒体
				
				processCSS(HyConfig.getRoot()+"/res/"+siteid,ctime);//处理CSS
				
				File dir = new File(outdir);
				String[] htmls = dir.list(new HTMLFileNameFilter());
				
				Map<String, String> pub = new HashMap<String, String>();//存公用部分
				if (htmls !=null && htmls.length > 0) {
					File file = new File(HyConfig.getShowPagePath()+"/"+siteid+"/");
					File pubFile = new File(HyConfig.getShowPagePath()+"/"+siteid+"/public/");//公共部分
					if(!file.exists()){
						file.mkdirs();
					}
					if(!pubFile.exists()){
						pubFile.mkdirs();
					}
					JSONObject jo = pageCompose.findJspname(siteid);
					for(String s : htmls){
						int index = s.lastIndexOf('.');
						String name = s.substring(0,index)+".jsp";
						long pageid = 0 ;
						if(jo.containsKey(siteid+"/"+name)){
							String pid = jo.getString(siteid+"/"+name);
							pageid = Long.parseLong(pid);
						}else{
							 pageid = pageCompose.composeUploadPageHtml(siteid,s.substring(0,index),siteid+"/"+name);//新建page							
						}
						jo.element(siteid+"/"+name, pageid);
					}
					Map<String,String> map = jsonObjectToMap(jo,siteid);
					for(String s : htmls){
						String baseUrl = "/" +account.getOwner().getId()+"/"+ siteid;
						String html = PageFileUtil.readFile(outdir + "/" + s);
						int index = s.lastIndexOf('.');
						String jsppath=HyConfig.getShowPagePath()+"/"+siteid+"/";
						String jspname = s.substring(0,index)+".jsp";
						html = PageReader.getHTMLByFile(outdir+"/"+s, baseUrl,"/res/"+siteid,map);//替换路径
						html = GProcessHtml.processGenernalCode(html,pub);//处理通用部分
						Map<String,String> result = new GProcessHtml().process(html);//改链接 提取hyvar
						if(result != null){
							//有定义hyvar 走此代码
							PageFileUtil.SaveFile(jsppath+jspname, result.get("jsp"));//创建jsp文件
							pageCompose.composeSavePageParamAndValue(Long.parseLong(map.get(s)), siteid+"/"+jspname, result.get("define"), result.get("value"));							
						}else{
							StringBuffer sb =new StringBuffer("<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>\n<%@ taglib uri='/struts-tags' prefix='s'%>\n");
							sb.append(html);
							PageFileUtil.SaveFile(jsppath+jspname, sb.toString());//创建jsp文件
						}
						pageCompose.updateSiteIsWhole(siteid);
						String tile=html.substring(html.indexOf("<title>")+7 ,html.indexOf("</title>"));
						if(StringUtils.isEmpty(tile)){
							tile="未命名";
						}
						pageCompose.updateNamebypageid(Long.parseLong(map.get(s)),tile);
					}
				}
				for (String key : pub.keySet()) {
					//创建通用jsp
				   StringBuffer sb =new StringBuffer("<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>\n<%@ taglib uri='/struts-tags' prefix='s'%>\n");
				   sb.append(pub.get(key));
				   PageFileUtil.SaveFile(HyConfig.getShowPagePath()+"/"+siteid+"/public/"+key+".jsp", sb.toString());//创建空的jsp文件
				  }				
				
			}catch (Exception x) {
				x.printStackTrace();
				errmsg="无法处理文件,请确认文件格式!";
				result = "N";
				return INPUT;
			}
	    } catch (Exception e) {
			errmsg ="errors.upload.size.exceeded";
			result = "N";
			return INPUT;

		}finally{
			try{
				if(is != null){
					is.close();
				}				
			}catch(Exception ex){
			}
		}
		result = "Y";
		return SUCCESS;
	}
	
	private Map<String,String> jsonObjectToMap(JSONObject jo ,long siteid){
		Map<String,String> map = new HashMap<String, String>();
		for (Iterator iter = jo.keys(); iter.hasNext();) { //先遍历整个 people 对象  
		    String key = (String)iter.next(); 
		    String value = jo.getString(key);
		    key = key.replaceAll(siteid+"/", "").replaceAll(".jsp", ".html");
		    map.put(key, value);
		}  
		return map;
	}
	
	private String processHTML(String outdir, long siteid, long owner, long ctime) throws Exception {
		File dir = new File(outdir);
		String[] htmls = dir.list(new HTMLFileNameFilter());
		if (htmls==null || htmls.length == 0) {
			return null;
		}
		String baseUrl = "/" + "page" + "/" + owner + "/" + siteid + "/" + ctime;
		String html = UrlReader.getHTMLByFile(outdir + "/" + htmls[0], baseUrl);

		return html;
	}
	
	private String getJsStr(String outdir){
		File dir = new File(outdir+"/js");
		String[] jses = dir.list(new JSFileNameFilter());
		if(jses != null && jses.length >0){
			Map<String,String> map = new HashMap<String, String>();
			for(String j : jses){
				map.put(j, outdir+"/js/"+j);
			}
			return new Gson().toJson(map);
		}
		return null;
	}
	
	private String getCssStr(String outdir){
		File dir = new File(outdir+"/css");
		String[] csses = dir.list(new CSSFileNameFilter());
		if(csses != null && csses.length >0){
			Map<String,String> map = new HashMap<String, String>();
			for(String c : csses){
				map.put(c, outdir+"/css/"+c);
			}
			return new Gson().toJson(map);
		}
		return null;
	}
	
	private void processCSS(String outdir,long ctime)throws Exception{
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		String baseUrl = "/" + account.getOwner().getId() + "/" + siteid + "/";
		String svpath="/";
		File dir = new File(outdir+"/css");
		String[] csses = dir.list(new CSSFileNameFilter());
		if(csses != null && csses.length >0){
			for(String c : csses){
				String fileUrl=outdir+"/css/"+c;
				File file = new File(fileUrl);
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));// 获取该文件的输入流 
		        String line = "";
		        StringBuffer sb=new StringBuffer();
		        while((line = br.readLine()) != null){
		        	if(line.contains("url(")){
		        		String url = line.substring(line.indexOf("url(")+4, line.indexOf(')',line.indexOf("url(")));
		        		if(url.startsWith("../")){
		        			String newline=url.substring(3,url.length());
		        			if (newline.startsWith("/")) {
					            String newurl = HyConfig.getImgDomain()+ baseUrl+  newline;
					            String src=line.replace(url, newurl);
					            sb.append(src);
					         } else if (newline.startsWith("http://") == false) { // maybe, this is the relative path
					        	 String newurl = HyConfig.getImgDomain() +baseUrl + svpath+ newline;
					        	 String src=line.replace(url, newurl);
					        	 sb.append(src);
					         }else{
					        	 sb.append(line);
					         }
		        		}else{
		        			if (url.startsWith("/")) {
					            String newurl = HyConfig.getImgDomain()+ baseUrl+  url;
					            String src=line.replace(url, newurl);
					            sb.append(src);
					         } else if (url.startsWith("http://") == false) { // maybe, this is the relative path
					        	 String newurl = HyConfig.getImgDomain() +baseUrl + svpath+ url;
					        	 String src=line.replace(url, newurl);
					        	 sb.append(src);
					         }else{
					        	 sb.append(line);
					         }
		        		}
		        	}else{
		        		sb.append(line+"\r\n");
		        	}
		        }
		        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
		        String content=sb.toString();
			    writer.write(content);
			    writer.flush();
			    writer.close();
		        br.close();
			}
		}
	}
	
	private void unzip(InputStream is, String outdir) throws Exception {
		ZipArchiveInputStream in = new ZipArchiveInputStream(new BufferedInputStream(is), "GBK", true);
		ZipArchiveEntry z;
		int size = 0;
		
		while ((z=in.getNextZipEntry() )!= null)
		{
			if (z.isDirectory())
				continue;
			else{
				File f = getParsedFileName(outdir, z.getName());
				FileOutputStream out=new FileOutputStream(f);
				byte[] buffer = new byte[1024];
				BufferedOutputStream bos = new BufferedOutputStream(out,buffer.length);
				
				while((size=in.read(buffer, 0, buffer.length)) != -1)
					bos.write(buffer, 0, size);
				bos.flush();
				bos.close();
				try {
					out.flush();
					out.close();
				}catch(Exception x) {
					x.printStackTrace();
				}
			}	
		}
		
		try {
			in.close();
		}catch (Exception x) {
			x.printStackTrace();
		};
		try {
			is.close();
		}catch (Exception x) {
			x.printStackTrace();
		};
		
	}
	
	private File getParsedFileName(String outdir, String name) throws Exception {
		
		int cutoff = 0;
		if (name.endsWith(".htm") || name.endsWith(".html"))
			cutoff = 1;
		else
			cutoff = 2;
		
		String[] x = org.springframework.util.StringUtils.tokenizeToStringArray(name, "/\\");
		int idx = 0;
		if (x.length > cutoff) {
			idx = 1;
		}
		String dir = outdir;
		String fileName = x[x.length - 1];
		int dirlen = x.length -1;
		
		for (int i=idx; i<dirlen; i++) 
			dir = dir + "/" + x[i];
		
		File dirs = new File(dir);
		dirs.mkdirs();
		File  file = new File(dir + "/" + fileName);
		if(file.exists()) {
			file.delete();
		}
		file.createNewFile();
		return file;
	}
	
	class HTMLFileNameFilter implements FilenameFilter {

		@Override
		public boolean accept(File dir, String name) {
			
			if (name.endsWith(".html") || name.endsWith(".htm"))
				return true;
			return false;
		}
		
	}

	class CSSFileNameFilter implements FilenameFilter {

		@Override
		public boolean accept(File dir, String name) {
			if (name.endsWith(".css"))
				return true;
			return false;
		}
		
	}
	
	class JSFileNameFilter implements FilenameFilter {

		@Override
		public boolean accept(File dir, String name) {
			if (name.endsWith(".js"))
				return true;
			return false;
		}
		
	}
	
	public String beifen() throws Exception{
		InputStream is = null;
		String jspPath = HyConfig.getRoot()+ "/page2c" + "/" + siteid; 
		String cssPath = HyConfig.getRoot()+ "/res2c" + "/" + siteid;
		Map<String,String> map = new HashMap<String, String>();
	    try{
			File f = new File(jspPath);
			File[] fs = f.listFiles();
//			if(fs.length > 0){
//				for(int i = 0 ; i< fs.length; i++){
//					String name = fs[i].getName();
//					if(fs[i].isDirectory() && "back.".equals(name)){
//						String time = name.substring(5, name.length());
//						Date date = new Date(Long.parseLong(time));
//						map.put(DateUtil.getDateTimeString(date), fs[i].getCanonicalPath());
//					}
//				}
//			}
			
	    }catch (Exception e) {
	    	e.printStackTrace();
		}
	    
		return SUCCESS;
	}
	
	public long getSiteid() {
		return siteid;
	}

	public void setSiteid(long siteid) {
		this.siteid = siteid;
	}

	public PageActionDto getDto() {
		return dto;
	}

	public void setDto(PageActionDto dto) {
		this.dto = dto;
	}

	public long getPageid() {
		return pageid;
	}

	public void setPageid(long pageid) {
		this.pageid = pageid;
	}

	public String getPagename() {
		return pagename;
	}

	public void setPagename(String pagename) {
		this.pagename = pagename;
	}

	public String getJspname() {
		return jspname;
	}

	public void setJspname(String jspname) {
		this.jspname = jspname;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public long getSitegroupid() {
		return sitegroupid;
	}

	public void setSitegroupid(long sitegroupid) {
		this.sitegroupid = sitegroupid;
	}

	public int getPagePosition(){
		return 1;
	}
    public int getPageId() {
        return pageId;
    }
    public void setPageId(int pageId) {
        this.pageId = pageId;
    }
    public String getType() {
        return type;
    }
    public int getSitetype() {
        return sitetype;
    }
	public PageTemplateDto getPdto() {
		return pdto;
	}
	public void setPdto(PageTemplateDto pdto) {
		this.pdto = pdto;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public void setSitetype(int sitetype)
	{
		this.sitetype = sitetype;
	}
	public void setFeatureCompose(IFeatureCompose featureCompose) {
		this.featureCompose = featureCompose;
	}
	public String getUploadType() {
		return uploadType;
	}
	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
}
