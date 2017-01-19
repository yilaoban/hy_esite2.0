<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div id="tab">
	<ul>
		<li <s:if test="lightType==2">class="select"</s:if>><a href="/${oname }/interact/offCheckSource.action?lightType=2">二维码配置</a></li>	
		<li <s:if test="lightType==3">class="select"</s:if>><a href="/${oname }/interact/offCheckRecord.action">用户管理</a></li>	
		<li <s:if test="lightType==5">class="select"</s:if>><a href="/${oname }/interact/offCheckLogs.action">签到记录</a></li>	
		<li <s:if test="lightType==4">class="select"</s:if>><a href="/${oname }/interact/offCheckSites.action?grouptype=A&lightType=4">应用站点</a></li>	
		<s:set name="app_name" value="'线下签到'" ></s:set>
		<s:include value="/WEB-INF/page/my_app_hyperlink.jsp" />	
	</ul>
</div>