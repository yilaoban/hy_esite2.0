
package com.huiyee.esite.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.constants.IPrivilegeConstants;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.OwnerPrivilege;
import com.huiyee.esite.service.Permission;
import com.huiyee.esite.util.HyConfig;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class PrivilegeInterceptor extends AbstractInterceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1723632795257410745L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception
	{
	    if( !HyConfig.isRun() || validate(invocation)){
	    	return invocation.invoke();
	    }else{
	    	HttpServletRequest request = ServletActionContext.getRequest();
	    	HttpServletResponse response = ServletActionContext.getResponse();
	    	Account account = (Account) request.getSession().getAttribute("account");
	    	String url = "/"+account.getOwner().getEntity()+"/page/app_out_of_time.action";
	    	request.getRequestDispatcher(url).forward(request, response);
			return null;
	    }
	    
	}
	
	private boolean validate(ActionInvocation invocation) throws SecurityException, NoSuchMethodException {
		String  methodName=invocation.getProxy().getMethod();
		Method currentMethod = invocation.getAction().getClass().getMethod(methodName);
		if(currentMethod != null && currentMethod.isAnnotationPresent(Permission.class)){
			//得到方法上的注解
			Permission permission = currentMethod.getAnnotation(Permission.class);  
			//得到当前登录的用户
			Account account = (Account)ServletActionContext.getRequest().getSession().getAttribute("account");
			//遍历当前用户下的所有的权限组  
			if(account.getOwner().getPrivileges() != null ){
				for(OwnerPrivilege op : account.getOwner().getPrivileges()){
					//如果该权限组下包含需要的权限
					String module = permission.module();
					if(IPrivilegeConstants.MODULE_APP_商城.equals(module)){
						//微电商、积分商城 特殊处理
						String subtype = ServletActionContext.getRequest().getParameter("subtype");
						if("W".equalsIgnoreCase(subtype)){
							module = IPrivilegeConstants.MODULE_APP_微电商;
						}else if("J".equalsIgnoreCase(subtype)){
							module = IPrivilegeConstants.MODULE_APP_积分商城;
						}
					}
					if(module.equalsIgnoreCase(op.getModule())){
						if(IPrivilegeConstants.OWNER_PERMISSION_TRY.equalsIgnoreCase(op.getStatus())){
							//试用阶段
							long time = System.currentTimeMillis() - op.getStarttime().getTime();
							if(time > 0 && time <= 15 * 24 * 3600 * 1000){
								//试用 15天
								return true;
							}
						}else if(IPrivilegeConstants.OWNER_PERMISSION_NOR.equalsIgnoreCase(op.getStatus())){
							//正式使用
							long time = System.currentTimeMillis() - op.getEndtime().getTime();
							if(permission.privilege() <= op.getLevel() && time < 0 ){
								// 权限等级够  && 未过期
								return true;
							}
						}else{
							//非法操作
							return false;
						}
					}  
				}  
			}
            //说明遍历的该用户所有的权限组，没有发现该权限，说明没有该权限  
            return false;  
		}
		//没有标注注解，表示谁都可以调用该方法
		return true;
	}

}
