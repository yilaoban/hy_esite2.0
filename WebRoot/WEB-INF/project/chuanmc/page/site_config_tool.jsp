<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%-- <div id="tab">
	<ul>
	<s:if test='lightType == 1'>
		<li ><a href="/${oname}/page/pageconfig_new.action?siteid=${siteid }&xcid=${xcid}">页面配置</a></li>
		<li><a href="/${oname}/page/publish_to_wx.action?siteid=${siteid }&xcid=${xcid}">微信分享设置</a></li>
		<li><a href="javascript:void(0);" onclick="publish_sina('${oname }',${siteid })">发布到微博</a></li>
	</s:if>
	<s:elseif test="lightType == 2">
		<li><a href="/${oname}/page/pageconfig_new.action?siteid=${siteid }&xcid=${xcid}">页面配置</a></li>
		<li class="select"><a href="/${oname}/page/publish_to_wx.action?siteid=${siteid }&xcid=${xcid}">微信分享设置</a></li>
		<li><a href="javascript:void(0);" onclick="publish_sina('${oname }',${siteid })">发布到微博</a></li>
	</s:elseif>
	<s:else>
		<li class="select"><a href="/${oname}/page/pageconfig_new.action?siteid=${siteid }&xcid=${xcid}">页面配置</a></li>
		<li><a href="/${oname}/page/publish_to_wx.action?siteid=${siteid }&xcid=${xcid}">微信分享设置</a></li>
		<li><a href="javascript:void(0);" onclick="publish_sina('${oname }',${siteid })">发布到微博</a></li>
	</s:else>
	
	</ul>
</div>
 --%>