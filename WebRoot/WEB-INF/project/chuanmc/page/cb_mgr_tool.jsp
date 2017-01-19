<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="tab">
	<ul>
			<li <s:if test="lightType==4">class="select"</s:if>><a href="/${oname }/interact/cbAptEdit.action">表单配置</a></li>		
			<li <s:if test="lightType==5">class="select"</s:if>><a href="/${oname }/interact/marketing_activity.action">市场活动</a></li>		
			<li <s:if test="lightType==6">class="select"</s:if>><a href="/${oname }/interact/cbhbConfig.action">资金管理</a></li>
			<li <s:if test="lightType==1">class="select"</s:if>><a href="/${oname }/interact/cbSender.action">合伙人</a></li>
			<li <s:if test="lightType==7">class="select"</s:if>><a href="/${oname}/template/cBShowPage.action?lightType=7&grouptype=C">应用站点</a></li>
			<s:set name="app_name" value="'合伙人'" ></s:set>
			<s:include value="/WEB-INF/page/my_app_hyperlink.jsp" />
	</ul>
</div>