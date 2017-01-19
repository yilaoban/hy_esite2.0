package com.huiyee.esite.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.google.gson.Gson;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dto.CardDto;
import com.huiyee.esite.dto.TemplateSiteDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.CardBackGround;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.PageTitleProperty;
import com.huiyee.esite.model.TemplateBlock;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.service.FileUploadService;
import com.huiyee.esite.util.CacheUtil;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.esite.util.JsonUtil;
import com.opensymphony.xwork2.ActionContext;

public class CardAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1333828607567536632L;
	private long pageid;
	private CardDto dto;
	private String type = "W";
	private long cardid;
	private long relationid;// 模块展示时用
	private long pcid;
	private String json;
	private String cardMoveStr;
	private File pic;
	private String picFileName;
	private String musicname;
	private String picContentType;
	private String method = "P";
	private String name;
	private List<JSONObject> blocks;
	private long featureid;
	private long fid;
	private String ptype;
	private long fartherPageid;
	private String moveType;
	private String jspstyle;
	private JSONObject bgJson = null;
	private String bg;
	private String css;
	private String uploadMusic;
	private String uploadMusicFileName;
	private String uploadMusicContentType;
	private CardBackGround cardbg;
	private int pageId=1;
	private TemplateSiteDto tDto;
	private String title;
	private String keywords;
	private String description;
	private String flag;
	private PageTitleProperty ptp;
	private String isshow;
	private long xcid;
	private String ps;
	private String subtype;
	private String divs = "";
	private String eventName;
	private Page page;
	private String pageheight;
	private String uploadArrow;
	private JSONObject tagJson;
	
	public Page getPage()
	{
		return page;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getDivs() {
		return divs;
	}

	private VelocityEngine velocityEngine;

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public String getIsshow()
	{
		return isshow;
	}

	public void setIsshow(String isshow)
	{
		this.isshow = isshow;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getKeywords()
	{
		return keywords;
	}

	public void setKeywords(String keywords)
	{
		this.keywords = keywords;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getMoveType()
	{
		return moveType;
	}

	public void setMoveType(String moveType)
	{
		this.moveType = moveType;
	}

	public long getFartherPageid()
	{
		return fartherPageid;
	}

	public void setFartherPageid(long fartherPageid)
	{
		this.fartherPageid = fartherPageid;
	}

	public String getPtype()
	{
		return ptype;
	}

	public void setPtype(String ptype)
	{
		this.ptype = ptype;
	}

	public long getFid()
	{
		return fid;
	}

	public void setFid(long fid)
	{
		this.fid = fid;
	}

	public long getFeatureid()
	{
		return featureid;
	}

	public void setFeatureid(long featureid)
	{
		this.featureid = featureid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardMoveStr() {
		return cardMoveStr;
	}

	public void setCardMoveStr(String cardMoveStr) {
		this.cardMoveStr = cardMoveStr;
	}

	public File getPic() {
		return pic;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}

	public String getPicFileName() {
		return picFileName;
	}

	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}

	public String getPicContentType() {
		return picContentType;
	}

	public void setPicContentType(String picContentType) {
		this.picContentType = picContentType;
	}

	// 选卡片
	@Override
	public String execute() throws Exception {
		dto = (CardDto) pageCompose.composeCardAction(pageid);
		return SUCCESS;
	}
	
	//替换卡片
	public String updateCard()throws Exception{
		dto = (CardDto) pageCompose.composeCardListAction(pageid, subtype);
		return SUCCESS;
	}
	
	// 选卡片提交
	public String submit() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("text/html; charset=utf-8");
		pcid = pageCompose.composeAddCard(pageid, cardid);
		PrintWriter pw = response.getWriter();
		pw.print(pcid);
		pw.flush();
		pw.close();
		return null;
	}
	
	//替换卡片提交
	public String updateOldCard()throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("text/html; charset=utf-8");
		pcid = pageCompose.composeUpdateCard(pageid, cardid, pcid);
		PrintWriter pw = response.getWriter();
		pw.print(pcid);
		pw.flush();
		pw.close();
		return null;
	}

	// 展示card
	public String showCard() throws Exception {
		VisitUser vu = (VisitUser) ServletActionContext.getRequest().getSession().getAttribute("visitUser");
		if(HyConfig.isRun()){
			dto = (CardDto) pageCompose.composeShowCard(pcid);
			jspstyle = dto.getJspstyle();
		}else{
			if(this.getOwner()==0){
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setHeader("pragma", "no-cache");
				response.setHeader("cache-control", "no-cache");
				response.setContentType("text/html; charset=utf-8");
				PrintWriter pw = response.getWriter();
				pw.print("error:vu:"+vu+",owner"+this.getOwner());
				pw.flush();
				pw.close();
				return null;
			}
			if(vu==null)
			{
				vu=new VisitUser();
			}
			dto=CacheUtil.psds.get(pcid+vu.getKv());
			if(dto==null){
			dto = (CardDto) pageCompose.composeShowCard2(pcid);
			CacheUtil.psds.put(pcid+vu.getKv(), dto);
			}
		}
		pageCompose.setXq(dto);//卡片详情页数据
		page = pageCompose.findPageByid(pageid);
		if(dto.getTc() == null){
			System.out.println("pcid 不存在："+pcid);
			return ERROR;
		}
		List<TemplateBlock> b = dto.getTc().getBlocks();
		if (blocks == null) {
			blocks = new ArrayList<JSONObject>();
		}
		for (int i = 0; i < b.size(); i++) {
			TemplateBlock tb = b.get(i);
			JSONObject jb = b.get(i).getValue();//值
			if("N".equals(tb.getAlone())){
				//非组件
				if ("I".equals(tb.getType())) {
					String hd = jb.get("obj").toString();
					String hyct=null;
					if(null != jb.get("hyct")){
						hyct = jb.get("hyct").toString();
					}
					try {
						jb = JSONObject.fromObject(hd);
						Object obj = jb.get("featureid");
						if(obj != null){
							jb = pageCompose.findContentDetails(jb,vu);
						}
						if(null != hyct){
							jb = jb.element("hyct",hyct);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else if("A".equals(tb.getType())){
					long appid = tb.getFeatureid();
					Object obj = pageCompose.findAppObjectByAppid(this.getOwner(),appid);
					jb.element("obj", obj);
				}
				jb.element("rid", tb.getRelationid()).element("display", tb.getDisplay());
				blocks.add(i, jb);
			}else{
				Map map = JsonUtil.parseJSON2Map(jb.toString());
				map.put("pcbid", tb.getRelationid());
				map.put("isRun", false || "E".equals(method));
				String html = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/WEB-INF/velocity/zujian/"+tb.getName(),"UTF-8", map);
				divs += html;
			}
		}
		//网站资源
		List<String> sources = pageCompose.findPageSourceByPageid(pageid,false || "E".equals(method),IPageConstants.OWNER_SOURCE_LEVEL_CARD);
		if(sources != null && sources.size() > 0){
			for(String str : sources){
				divs += str;
			}
		}
		return SUCCESS;
	}
	
	// 编辑版块
	public String showBlock() throws Exception {
		dto = (CardDto) pageCompose.composeEditBlock(relationid,pageid);
		if(dto == null){
			return "error";
		}
		if ("S".equals(dto.getTb().getType())) {
			return "commen";
		}else if("C".equals(dto.getTb().getType())){
			return "list";
		}else if("A".equals(dto.getTb().getType())){
			return "no_edit";
		}else {
			type = dto.getTb().getType();
			fid =dto.getPbr().getFid();
			featureid = dto.getTb().getFid();
			return "kongjian";
		}
	}

	// 版块保存
	public String blockSub() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("text/html; charset=utf-8");
		json = json.replaceAll ("\\\\r\\\\n", "");
		json = json.replaceAll("'", "&apos;");
		int result = pageCompose.composeSaveRelationJson(relationid, json);
		PrintWriter pw = response.getWriter();
		pw.print(result);
		pw.flush();
		pw.close();
		return null;
	}
	
	public String blockSubC() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("text/html; charset=utf-8");
		json = json.replaceAll("'", "&apos;");
		JSONObject jo = JSONObject.fromObject(json);
		String str = jo.getString("list");
		JSONArray ja ;
		try {
			ja = JSONArray.fromObject(str);
		} catch (RuntimeException e) {
			str = "["+str+"]";
			ja = JSONArray.fromObject(str);
			jo.element("list", ja);
		}
		
		int result = pageCompose.composeSaveRelationJson(relationid, jo.toString());
		PrintWriter pw = response.getWriter();
		pw.print(result);
		pw.flush();
		pw.close();
		return null;
	}

	public String editPage() throws Exception {
		dto = (CardDto) pageCompose.composeEditPage(pageid,this.getOwner());
		if(dto == null){
			return "error";
		}
		return SUCCESS;
	}
	
	public String moveCard() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("text/html; charset=utf-8");
		int result = pageCompose.composeMovePosition(cardMoveStr);
		PrintWriter pw = response.getWriter();
		pw.print(result);
		pw.flush();
		pw.close();
		return null;
	}

	public String deleteCard() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("text/html; charset=utf-8");
		int result = pageCompose.composeDeleteCard(pcid, pageid);
		PrintWriter pw = response.getWriter();
		pw.print(result);
		pw.flush();
		pw.close();
		return null;
	}

	public String updateCardName() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("text/html; charset=utf-8");
		int result = pageCompose.updateCardName(pcid, name);
		PrintWriter pw = response.getWriter();
		pw.print(result);
		pw.flush();
		pw.close();
		return null;
	}

	public String online() throws Exception {
        String urlStr = "http://localhost:8080/page/preview.action?pageid=" + pageid; 
        URL url = null;              
        HttpURLConnection httpConn = null;            
        BufferedReader in = null;   
        StringBuffer sb = new StringBuffer();   
        try{     
        	url = new URL(urlStr);     
        	in = new BufferedReader( new InputStreamReader(url.openStream(),"UTF-8") );   
        	String str = null;    
        	while((str = in.readLine()) != null) {    
        		sb.append( str ).append("\n");     
        	}     
        } catch (Exception ex) {   
                
        } finally{    
             try{             
              if(in!=null) {  
            	  in.close();     
              }     
             }catch(IOException ex) {      
             }     
          }     
        String sTotalString =sb.toString();
        pageCompose.addPageHtml(pageid,sTotalString);
        
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setContentType("text/html; charset=utf-8");
        String result = pageCompose.findHtmlByPageid(pageid);
        PrintWriter pw = response.getWriter();
        pw.print(result);
        pw.flush();
        pw.close();
		return null;
	}
	
	//页面流程
	public String pageProcess() throws Exception {
		dto = (CardDto)pageCompose.composeCardListByPageid(pageid,type);
		return SUCCESS;
	}
	
	public String topageProcess() throws Exception{
		return SUCCESS;
	}
	
	public String pageProcessNew() throws Exception {
		String result = pageCompose.composePageRelation(pageid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter pw;
		try {
			pw = response.getWriter();
			pw.print(result);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//查看url
	public String showUrl() throws Exception{
		return SUCCESS;
	}
	
	//选择卡片
	public String selectCard() throws Exception {
		dto = (CardDto) pageCompose.composeCard(pageid, type,ptype);
		return SUCCESS;
	}
	
	//页面流程编辑卡片
	public String editCard() throws Exception {
		dto = (CardDto) pageCompose.composeTemplateCardByPageidAndPcid(pageid,pcid);
		List<TemplateBlock> b = dto.getTc().getBlocks();
		if (blocks == null) {
			blocks = new ArrayList<JSONObject>();
		}
		for (int i = 0; i < b.size(); i++) {
			JSONObject jb = b.get(i).getValue();//值
			if ("S".equals(b.get(i).getType())) {
				jb.element("rid", b.get(i).getRelationid());
				blocks.add(i, jb);
			} else if("C".equals(b.get(i).getType())){
				jb.element("rid", b.get(i).getRelationid());
				blocks.add(i, jb);
			}else {
				String hd = jb.get("obj").toString();
				jb = JSONObject.fromObject(hd);
				jb.element("rid", b.get(i).getRelationid());
				blocks.add(i, jb);
			}
		}
		return SUCCESS;
		
	}
	
	public String cardMoveStyle() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("text/html; charset=utf-8");
		int result = pageCompose.composeMoveStyle(jspstyle,pageid);
		PrintWriter pw = response.getWriter();
		pw.print(result);
		pw.flush();
		pw.close();
		return null;
	}
	
	public String savePageCardStyle() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("text/html; charset=utf-8");
		if(bg != null && bg.contains("/")){
			String url="url("+bg+")";
			int result = pageCompose.savePageCardStyle(cardid, url);
			PrintWriter pw = response.getWriter();
			pw.print(result);
			pw.flush();
			pw.close();
			return null;
		}else{
			int result = pageCompose.savePageCardStyle(cardid, bg);
			PrintWriter pw = response.getWriter();
			pw.print(result);
			pw.flush();
			pw.close();
		}
		return null;
	}
	
	public String savePageCardCss() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("text/html; charset=utf-8");
		int result = pageCompose.savePageCardCss(cardid, css);
		PrintWriter pw = response.getWriter();
		pw.print(result);
		pw.flush();
		pw.close();
		return null;
	}
	
	public String bgset() throws Exception{
		dto=(CardDto) pageCompose.findBgByCardid(pcid);
		return SUCCESS;
	}
	
	public String cssset() throws Exception{
		dto=(CardDto) pageCompose.findcssBgByCardid(pcid);
		return SUCCESS;
	}
	
	public String btset(){
		Gson gson = new Gson();
		String bgStr = pageCompose.findPageBg(pageid);
		page=pageCompose.findPageByid(pageid);
		if(StringUtils.isNotEmpty(bgStr)){
			String bgc =gson.fromJson(bgStr, PageTitleProperty.class).getBackground();
			if(bgc != null){
				if(bgc.startsWith("#")){
					flag="C";
					ptp=gson.fromJson(bgStr, PageTitleProperty.class);
				}
				if(bgc.contains("/")){
					flag="P";  
					ptp=gson.fromJson(bgStr, PageTitleProperty.class);
					if(ptp.getBackground() != null){
						String str=ptp.getBackground().substring(4, ptp.getBackground().length()-1);
						ptp.setBackground(str);
					}
				}else{
					ptp=gson.fromJson(bgStr, PageTitleProperty.class);
				}
			}else{
				ptp=gson.fromJson(bgStr, PageTitleProperty.class);
			}
		}else{
			flag="N";
			
		}
		return SUCCESS;
	}
	
	public String btsetSub() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("text/html; charset=utf-8");
		int result;
		if (uploadMusic != null)
		{
			result = pageCompose.savePageBg(pageid, title,keywords,description,bg,uploadMusic,musicname,uploadArrow,tagJson);
		}else{
			result = pageCompose.savePageBg(pageid, title,keywords,description,bg,"","",uploadArrow,tagJson);
		}
		PrintWriter pw = response.getWriter();
		pw.print(result);
		pw.flush();
		pw.close();
		return null;
	}
	
	public String pageheight() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("text/html; charset=utf-8");
		int result = pageCompose.savePageHeight(pageid, pageheight);
		PrintWriter pw = response.getWriter();
		pw.print(result);
		pw.flush();
		pw.close();
		return null;
	}
	
	public String selpage() throws Exception{
		return SUCCESS;
	}
	
	/**
	 * 隐藏block
	 */
	public String updatehiddenBlk() throws Exception
	{
		int num = pageCompose.updatehiddenBlk(relationid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.write(num);
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 删除block
	 */
	public String deleteBlk() throws Exception
	{
		int num = pageCompose.deleteBlk(relationid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.write(num);
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 显示black
	 */
	public String updateshowBlk() throws Exception
	{
		int num = pageCompose.updateshowBlk(pcid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.write(num);
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * 是否显示菜单
	 */
	public String updateisshowMenu() throws Exception
	{
		int num = pageCompose.updateisshowMenu(pcid,isshow);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.write(num);
		out.flush();
		out.close();
		return null;
	}
	
	public String savePosition() throws Exception{
		pageCompose.savePosition(ps);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.write("1");
		out.flush();
		out.close();
		return null;
	}
	
	public long getPageid() {
		return pageid;
	}

	public void setPageid(long pageid) {
		this.pageid = pageid;
	}

	public CardDto getDto() {
		return dto;
	}

	public void setDto(CardDto dto) {
		this.dto = dto;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getCardid() {
		return cardid;
	}

	public void setCardid(long cardid) {
		this.cardid = cardid;
	}

	public long getRelationid() {
		return relationid;
	}

	public void setRelationid(long relationid) {
		this.relationid = relationid;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public long getPcid() {
		return pcid;
	}

	public void setPcid(long pcid) {
		this.pcid = pcid;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public List<JSONObject> getBlocks() {
		return blocks;
	}

	public String getBg()
	{
		return bg;
	}

	public void setBg(String bg)
	{
		this.bg = bg;
	}

	public JSONObject getBgJson() {
		return bgJson;
	}

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public TemplateSiteDto getTDto()
	{
		return tDto;
	}

	public String getFlag()
	{
		return flag;
	}

	public void setFlag(String flag)
	{
		this.flag = flag;
	}

	public PageTitleProperty getPtp()
	{
		return ptp;
	}

	public void setPtp(PageTitleProperty ptp)
	{
		this.ptp = ptp;
	}

	public String getJspstyle() {
		return jspstyle;
	}

	public void setJspstyle(String jspstyle) {
		this.jspstyle = jspstyle;
	}

	public long getXcid()
	{
		return xcid;
	}

	public void setXcid(long xcid)
	{
		this.xcid = xcid;
	}

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

	public String getSubtype()
	{
		return subtype;
	}

	public void setSubtype(String subtype)
	{
		this.subtype = subtype;
	}

	public String getCss()
	{
		return css;
	}

	public void setCss(String css)
	{
		this.css = css;
	}


	public String getUploadMusicFileName() {
		return uploadMusicFileName;
	}

	public void setUploadMusicFileName(String uploadMusicFileName) {
		this.uploadMusicFileName = uploadMusicFileName;
	}

	public String getUploadMusicContentType() {
		return uploadMusicContentType;
	}

	public void setUploadMusicContentType(String uploadMusicContentType) {
		this.uploadMusicContentType = uploadMusicContentType;
	}

	public String getUploadMusic() {
		return uploadMusic;
	}

	public void setUploadMusic(String uploadMusic) {
		this.uploadMusic = uploadMusic;
	}

	public String getMusicname() {
		return musicname;
	}

	public void setMusicname(String musicname) {
		this.musicname = musicname;
	}

	public String changeEvent() throws Exception{
		int result = pageCompose.changeEvent(pcid, eventName);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}
	
	public String copyCard() throws Exception{
		long result = pageCompose.copyCard(pcid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}

	
	public String getPageheight()
	{
		return pageheight;
	}

	
	public void setPageheight(String pageheight)
	{
		this.pageheight = pageheight;
	}

	
	public String getUploadArrow()
	{
		return uploadArrow;
	}

	
	public void setUploadArrow(String uploadArrow)
	{
		this.uploadArrow = uploadArrow;
	}

	
	public JSONObject getTagJson()
	{
		return tagJson;
	}

	
	public void setTagJson(JSONObject tagJson)
	{
		this.tagJson = tagJson;
	}
}
