package project.caidan.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import project.caidan.mgr.IAccountManager;

import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.util.ClientUserIp;


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
		String ip = ClientUserIp.getIpAddr(request); 
        return "input";
	}
	
}
