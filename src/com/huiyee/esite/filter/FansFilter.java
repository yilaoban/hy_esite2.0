package com.huiyee.esite.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.huiyee.esite.model.VisitUser;

public class FansFilter implements Filter
{
	public void destroy()
	{
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		httpRequest.setCharacterEncoding("UTF-8");
		// 已经登录的
		HttpSession session = httpRequest.getSession();
		VisitUser vu = (VisitUser) session.getAttribute("visitUser");
		System.out.println("fansfilter pageid:"+vu.getPageid());
		if(vu != null && (vu.getPageid() == 41446 || vu.getPageid() == 41449 || vu.getPageid() == 41429 || vu.getPageid() == 41448 || vu.getPageid() == 41417 || vu.getPageid() == 41575)){
			//广告页、砸金蛋页过滤
			System.out.println("chain.doFilter");
			chain.doFilter(request, response);
			return;
		}
		if((vu != null && vu.getWxUser() != null && vu.getWxUser().getSubscribe() != 1)  ){
			//微信环境 非粉丝去到关注引导页
			String url = "http://page.mocaidan.com/caidan/user/wxshow/41575/wxn.html";
			((HttpServletResponse) response).sendRedirect(url);
			return;
		}
		chain.doFilter(request, response);
	}


	public void init(FilterConfig filterConfig) throws ServletException
	{
	}

}
