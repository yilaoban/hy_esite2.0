package com.huiyee.esite.action;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.constants.IPageConstants;
import com.opensymphony.xwork2.ActionContext;
public class LogoutAction extends AbstractAction implements IPageConstants {

	/**
     * 
     */
    private static final long serialVersionUID = -5572915976885726787L;
    private Cookie ownernameCookie;
    private Cookie accountnameCookie;
	private Cookie passwordCookie;
	private String url;
	
	@Override
	public String execute() throws Exception {

		ActionContext.getContext().getSession().remove("account");
		ServletActionContext.getRequest().getSession().invalidate();
		
		ownernameCookie = new Cookie("ownernameCookie", "");
		ownernameCookie.setPath("/");
		ownernameCookie.setMaxAge(0);
		
		accountnameCookie = new Cookie("accountnameCookie", "");
		accountnameCookie.setPath("/");
		accountnameCookie.setMaxAge(0);

		passwordCookie = new Cookie("passwordCookie", "");
		passwordCookie.setPath("/");
		passwordCookie.setMaxAge(0);

		ServletActionContext.getResponse().addCookie(ownernameCookie);
		ServletActionContext.getResponse().addCookie(accountnameCookie);
		ServletActionContext.getResponse().addCookie(passwordCookie);

		return "successRedirect";
	}
	public Cookie getPasswordCookie() {
		return passwordCookie;
	}

	public void setPasswordCookie(Cookie passwordCookie) {
		this.passwordCookie = passwordCookie;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

    public Cookie getOwnernameCookie() {
        return ownernameCookie;
    }

    public void setOwnernameCookie(Cookie ownernameCookie) {
        this.ownernameCookie = ownernameCookie;
    }

    public Cookie getAccountnameCookie() {
        return accountnameCookie;
    }

    public void setAccountnameCookie(Cookie accountnameCookie) {
        this.accountnameCookie = accountnameCookie;
    }

}
