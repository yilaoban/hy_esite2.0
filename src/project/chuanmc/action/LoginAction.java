package project.chuanmc.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.Owner;
import com.huiyee.esite.util.TicketUtil;
import com.huiyee.esite.util.ToolUtils;

import project.chuanmc.dto.LoginDto;
import project.chuanmc.mgr.IAccountManager;


public class LoginAction extends AbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IAccountManager accountManager;
	private String ownername;
	private String accountname;
	private String password;
	private String msg;
	
	public void setAccountManager(IAccountManager accountManager)
	{
		this.accountManager = accountManager;
	}

	public String getMsg()
	{
		return msg;
	}
	
	public void setMsg(String msg)
	{
		try
		{
			this.msg = ToolUtils.decodeString(msg);
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public String execute() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		if(session.getAttribute("account") != null){
			//已登录的访问登录页面 直接跳转到对应首页
			return "already";
		}
		return SUCCESS;
	}
	
	public String sign() throws Exception{
		if(StringUtils.isEmpty(ownername)){
			msg=ToolUtils.encodeString("会员名不能为空");
			return "input";
		}
		if(StringUtils.isEmpty(accountname)){
			msg=ToolUtils.encodeString("账号不能为空");
			return "input";
		}
		if(StringUtils.isEmpty(password)){
			msg=ToolUtils.encodeString("密码不能为空");
			return "input";
		}
		
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session = request.getSession();
        String ip = request.getHeader("x-forwarded-for"); 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("PRoxy-Client-IP"); 
        } 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("WL-Proxy-Client-IP"); 
        } 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getRemoteAddr(); 
        } 
        
        LoginDto dto = (LoginDto) accountManager.login(ownername, accountname, ToolUtils.getMD5Str(password), ip);
        if(dto.getAccount() != null){
        	this.setOname(ownername);//设置oname
        	Account account = dto.getAccount();
    		Owner owner = account.getOwner();
    		//保存ticked
    		session.setAttribute("ticket",(TicketUtil.encodeString(TicketUtil.encode(owner.getId()+","+account.getId()+",1,"+TicketUtil.getLicence("Y", "Y", "Y")))));
    		session.setAttribute("account", dto.getAccount());//保存account
    		session.setAttribute("ebLicence", 1);
    		session.setAttribute("eyLicence", 1);
    		session.setAttribute("esLicence", 1);
    		return SUCCESS;
        }
        msg=ToolUtils.encodeString("账号异常");
        return "input";
	}
	
	public String getOwnername()
	{
		return ownername;
	}

	
	public void setOwnername(String ownername)
	{
		this.ownername = ownername;
	}

	
	public String getAccountname()
	{
		return accountname;
	}
	
	public void setAccountname(String accountname)
	{
		this.accountname = accountname;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}

	public String logout() throws Exception{
		
		return SUCCESS;
	}
}
