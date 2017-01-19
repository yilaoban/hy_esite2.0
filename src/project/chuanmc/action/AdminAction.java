package project.chuanmc.action;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.huiyee.esite.action.AbstractAction;
import project.caidan.mgr.IAccountManager;


public class AdminAction extends AbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IAccountManager accountManager;
	
	public void setAccountManager(IAccountManager accountManager)
	{
		this.accountManager = accountManager;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String sign() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
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
        return "input";
	}
	
}
