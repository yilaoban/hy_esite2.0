<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="tab">
  <ul>
	<li <s:if test="lightType==1">class="select"</s:if>><a href="/${oname}/cd-guanggao/adGGList.action">广告管理</a></li>
	<li <s:if test="lightType==2">class="select"</s:if>><a href="/${oname}/cd-guanggao/tfggList.action">投放广告</a></li>		
  </ul>
</div>
