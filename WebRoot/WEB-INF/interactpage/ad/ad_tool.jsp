<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div id="tab">
	<ul>
		<li <s:if test="lightType==1">class="select"</s:if>><a href="/${oname}/interact/adQrList.action">二维码</a></li>	
		<li <s:if test="lightType==2">class="select"</s:if>><a href="/${oname}/interact/adGGList.action">广告设置</a></li>	
		<li <s:if test="lightType==3">class="select"</s:if>><a href="/${oname}/interact/adMediaList.action">媒体管理</a></li>	
		<li <s:if test="lightType==4">class="select"</s:if>><a href="/${oname}/interact/adSites.action?lightType=4&grouptype=D">应用站点</a></li>	
		<li <s:if test="lightType==5">class="select"</s:if>><a href="javascript:void(0)">广告效果</a></li>	
		<s:set name="app_name" value="'微投放'" ></s:set>
		<s:include value="/WEB-INF/page/my_app_hyperlink.jsp" />
	</ul>
</div>