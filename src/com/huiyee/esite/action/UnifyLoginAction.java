package com.huiyee.esite.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dto.LoginDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.model.WxUser;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.esite.util.TicketUtil;
import com.huiyee.esite.util.ToolUtils;
import com.huiyee.interact.bbs.model.BBSUser;

public class UnifyLoginAction extends AbstractAction implements IPageConstants{

	/**
	 * 
	 */
	private static final long serialVersionUID = -691667751104357036L;
	private String ticket;
	private String rPath;
	private String unifyLoginUrl = HyConfig.getUnifyloginurl();
	private LoginDto dto;
	private String errmsg;
	private int type;
	private long ownerId;
	private long accountid;
	private String accountname;
    public long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}

	public long getAccountid() {
		return accountid;
	}

	public void setAccountid(long accountid) {
		this.accountid = accountid;
	}

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	@Override
    public String execute() throws Exception {
//    	ticket = (String) ServletActionContext.getRequest().getSession().getAttribute(IPageConstants.TICKET);
//    	ServletActionContext.getRequest().getSession().removeAttribute(IPageConstants.TICKET);
    	if(ticket == null ){
    		errmsg=ToolUtils.encodeURLString("身份验证失败!");
    		return "failed";
    	}
    	HttpServletRequest request=ServletActionContext.getRequest();
    	String ip = ClientUserIp.getIpAddr(request); 
    	//验证ticket 完成登录
    	String[] params = TicketUtil.decode(TicketUtil.decodeString(ticket)).split(",");
    	if(params.length == 4 ){
    		dto = (LoginDto) pageCompose.composeUnifyLogin(Long.parseLong(params[0]), Long.parseLong(params[1]), ip);
    		if(dto == null){
    			errmsg=ToolUtils.encodeURLString("账号密码不匹配!");
    			return "failed";
    		}
    		Account account=dto.getAccount();
    		int licence = Integer.parseInt(params[3]);
    		HttpSession session = ServletActionContext.getRequest().getSession();
    		session.setAttribute("ebLicence", TicketUtil.ebLicence(licence));
    		session.setAttribute("eyLicence", TicketUtil.eyLicence(licence));
    		session.setAttribute("esLicence", TicketUtil.esLicence(licence));
    		session.setAttribute("account", account);
    		session.setAttribute("leftControl", dto.getHideControl());
    		session.setAttribute("ticket", ticket);
    		session.setMaxInactiveInterval(-1);
    		rPath=(String) session.getAttribute(IPageConstants.REDIRECT_URL);
    		 if (session.getAttribute("visitUser") == null && HyConfig.isRun())
    		 {
    			 //后台代码，为了让前台链接也能在后台正常点击，故此做此假信息
    		 VisitUser u = new VisitUser();
    		 u.setSource("pcn", 0);
    		 session.setAttribute("visitUser", u);
    		 }
    		if (rPath != null && (!"".equals(rPath)) && (!"/".equals(rPath))) {
    			ServletActionContext.getRequest().getSession().removeAttribute(IPageConstants.REDIRECT_URL);
    			System.out.println("UnifyLoginAction.rpath=:"+rPath);
    			if(rPath.contains("ticket")){
    				rPath = removeTicket(rPath);
    				System.out.println("UnifyLoginAction.rpath=:"+rPath);
    				Map<Integer, String> map=dto.getHideControl();
    				for (Map.Entry<Integer, String> entry : map.entrySet()) {  
    					if(!"N".equals(entry.getValue())){
    						int i=entry.getKey();
    						switch (i) {
							case 1:break;
							case 2:rPath = rPath.replace("/page/sitegroupList.action", "/content/content_index.action");break;
							case 3:rPath = rPath.replace("/page/sitegroupList.action", "/interact/index.action");break;
							case 4:rPath = rPath.replace("/page/sitegroupList.action", "/bbs/index.action");break;
							case 5:rPath = rPath.replace("/page/sitegroupList.action", "/data/index.action");break;
							case 6:rPath = rPath.replace("/page/sitegroupList.action", "/template/index.action?type=F");break;
							case 7:rPath = rPath.replace("/page/sitegroupList.action", "/material/index.action?type=Z");break;
							}
    						break;
    					}
    				}  
    			}
    			return "oldRedirect";
    		}
    		return "successRedirect";
    	}else{
    		errmsg=ToolUtils.encodeURLString("身份验证失败!");
    		return "failed";
    	}
    }
    
    public String removeTicket(String rPath){
		String domainString=rPath.substring(0,rPath.indexOf("?")+1);
		String queryString = rPath.substring(rPath.indexOf("?")+1,rPath.length());
		String[] params = queryString.split("&");
		for(String str : params){
			if(!str.startsWith("ticket")){
				domainString += str +"&";
				}
		}
		return domainString.substring(0,domainString.length()-1);
    }
    
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public LoginDto getDto() {
		return dto;
	}
	public void setDto(LoginDto dto) {
		this.dto = dto;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getUnifyLoginUrl() {
		return unifyLoginUrl;
	}
	public void setUnifyLoginUrl(String unifyLoginUrl) {
		this.unifyLoginUrl = unifyLoginUrl;
	}
	public String getRPath() {
		return rPath;
	}
	public void setRPath(String path) {
		rPath = path;
	}

}
