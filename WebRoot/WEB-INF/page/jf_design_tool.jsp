<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="tab">
	<ul>
		<li <s:if test='tab == "S"'>class="select"</s:if>><a href="/${oname}/page/jfDesign.action">设置</a></li>
		<li <s:if test='tab == "R"'>class="select"</s:if>><a href="/${oname}/page/rmbRule.action">充值规则</a></li>
		<li <s:if test='tab == "U"'>class="select"</s:if>><a href="/${oname}/page/jfUser.action">用户</a></li>	
		<li <s:if test='tab == "Q"'>class="select"</s:if>><a href="/${oname}/page/findCheckinRecordList.action?tab=Q">签到</a></li>
		<li <s:if test='lightType == 5'>class="select"</s:if>><a href="/${oname }/page/jfSites.action?lightType=5&grouptype=E">应用站点</a></li>
		<li <s:if test='tab == "M"'>class="select"</s:if>><a href="/${oname}/page/jfTemplate.action">模板消息</a></li>
		<s:set name="app_name" value="'用户中心'" ></s:set>
		<s:include value="/WEB-INF/page/my_app_hyperlink.jsp" />	
	</ul>
</div>
