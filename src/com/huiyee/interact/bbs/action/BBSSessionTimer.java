package com.huiyee.interact.bbs.action;

import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.huiyee.esite.dto.BbsUserLoginTime;
import com.huiyee.esite.util.CacheUtil;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.interact.bbs.model.BBSUser;

public class BBSSessionTimer implements HttpSessionListener
{
	
	/* Session创建事件 */
	public void sessionCreated(HttpSessionEvent se) {
		
	}

	/* Session失效事件 */
	public void sessionDestroyed(HttpSessionEvent se) {
		if(!HyConfig.isRun()){
			ServletContext ctx = se.getSession().getServletContext();
			BBSUser bbsUser =  (BBSUser) ctx.getAttribute("timeVu");
			if(bbsUser != null){
				BbsUserLoginTime time = new BbsUserLoginTime();
				time.setTime(new Date().getTime() -  bbsUser.getLogintime());
				time.setUid(bbsUser.getId());
				time.setLogid(bbsUser.getLogid());
				CacheUtil.addBbs(time);
			}
		}
		}

	
}