<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<s:if test="appId==1">
	<s:include value="/WEB-INF/page/community_tool.jsp" />
</s:if>
<s:elseif test="appId==2">
	<s:include value="/WEB-INF/page/cb_mgr_tool.jsp" />
</s:elseif>
<s:elseif test="appId==3">
	<s:include value="/WEB-INF/interactpage/setting/user_center_tool.jsp" />
</s:elseif>
<s:elseif test="appId==4">
	<s:include value="/WEB-INF/interactpage/shop/wsc_tool.jsp" />
</s:elseif>
<s:elseif test="appId==5">
	<s:include value="/WEB-INF/interactpage/shop/jfsc_tool.jsp" />
</s:elseif>
<s:elseif test="appId==6">
	<s:include value="/WEB-INF/interactpage/offcheck/offCheckTool.jsp" />
</s:elseif>
<s:elseif test="appId==7">
	<s:include value="/WEB-INF/interactpage/yuyue/yuyue_tool.jsp" />
</s:elseif>
<s:elseif test="appId==8">
	<s:include value="/WEB-INF/interactpage/leader/leader_tool.jsp" />
</s:elseif>
<s:elseif test="appId==10">
	<s:include value="/WEB-INF/interactpage/sitesearch/site_search_tool.jsp" />
</s:elseif>
<s:elseif test="appId==11">
	<s:include value="/WEB-INF/interactpage/ad/ad_tool.jsp" />
</s:elseif>
<s:elseif test="appId==12">
	<s:include value="/WEB-INF/interactpage/servicerpj/pj_tool.jsp" />
</s:elseif>
