<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="tab">
	<ul>
		<li <s:if test='lightType==1'>class="select"</s:if>><a href="/${oname}/setting/jfSetting.action">积分</a></li>
		<li <s:if test='lightType==2'>class="select"</s:if>><a href="/${oname}/setting/rmbRule.action">会员</a></li>
		<li <s:if test='lightType==3'>class="select"</s:if>><a href="/${oname}/setting/data_rmb.action">账单</a></li>	
		<li <s:if test='lightType==4'>class="select"</s:if>><a href="/${oname}/setting/dz.action">管理员</a></li>
		<li <s:if test='lightType==5'>class="select"</s:if>><a href="/${oname}/setting/appSite.action?lightType=5&grouptype=E">应用站点</a></li>
		<li <s:if test='lightType==6'>class="select"</s:if>><a href="/${oname}/setting/template.action">模板消息</a></li>
		<s:set name="app_name" value="'用户中心'" ></s:set>
		<s:include value="/WEB-INF/page/my_app_hyperlink.jsp" />	
	</ul>
</div>
