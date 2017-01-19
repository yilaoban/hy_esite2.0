<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div id="tab">
	<ul>
		<li <s:if test="lightType==1">class="select"</s:if>><a href="/${sessionScope.account.owner.entity}/bbs/index.action">社区管理</a></li>
		<!-- 
		<li <s:if test="lightType==2">class="select"</s:if>><a href="/${sessionScope.account.owner.entity}/bbs/user_index.action">会员管理</a></li>
		 -->
		 <li <s:if test="lightType==2">class="select"</s:if>><a href="/${sessionScope.account.owner.entity}/bbs/userInfo.action?type=2">会员管理</a></li>
		 <li <s:if test='lightType==3'>class="select"</s:if>><a href="/${oname}/template/sQShowPage.action?lightType=3&grouptype=B">应用站点</a></li>
		 <s:set name="app_name" value="'微社区'" ></s:set>
		 <s:include value="/WEB-INF/page/my_app_hyperlink.jsp" />
	</ul>
	<!-- <%@include file="/WEB-INF/page/base_app_select.jsp" %> -->
</div>