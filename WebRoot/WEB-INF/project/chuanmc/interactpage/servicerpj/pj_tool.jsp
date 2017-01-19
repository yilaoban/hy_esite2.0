<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="tab">
  <ul>
    <li <s:if test="lightType==1">class="select"</s:if>><a href="/${oname }/servicerpj/servicerIndex.action">服务人员</a></li>
    <li <s:if test="lightType==2">class="select"</s:if>><a href="/${oname }/servicerpj/pjIndex.action">评论记录</a></li>
    <li <s:if test="lightType==3">class="select"</s:if>><a href="/${oname }/servicerpj/pjSites.action?lightType=3&grouptype=H">应用站点</a></li>
    <li <s:if test="lightType==4">class="select"</s:if>><a href="/${oname}/servicerpj/template.action">模版消息</a></li>
    <li <s:if test="lightType==5">class="select"</s:if>><a href="/${oname}/servicerpj/config.action">服务设置</a></li>
    <li <s:if test="lightType==6">class="select"</s:if>><a href="/${oname}/servicerpj/pjwd.action">评价维度</a></li>
    <s:set name="app_name" value="'服务评价'"></s:set>
	<s:include value="/WEB-INF/page/my_app_hyperlink.jsp" />	
  </ul>
</div>