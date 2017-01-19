<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content left_module">
	<div class="toolbar pb10">
	 <ul class="c_switch">
	   <s:if test="dto.scategoryList.size() > 0">
		<s:iterator value="dto.scategoryList" var="c" status="st">
		<li <s:if test='scategoryid == #c.id'> class="selected"</s:if> ><a href="/${oname}/material/index.action?fcategoryid=${fcategoryid}&scategoryid=${c.id}">${c.name}</a></li>
	   </s:iterator>
	  </s:if>
	  </ul>
	</div>
	  <div class="clearfix" style="margin-left:-20px;">
	   <s:iterator value="dto.materiallist" var="t">
	    <div class="mtrl-item">
	      <img src="${t.path}" <s:if test='fcategoryid == 1'>width="238"</s:if><s:if test='scategoryid == 11'>width="238"</s:if> />
	     </div>
	    </s:iterator>
	   </div>
	    <%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
	    <!-- <span  id="materialprepage"></span>&nbsp;<span id="materialnextpage"> <s:if test="#attr.dto.total>2"><a href="javascript:void(0);" onclick="getMaterialData(2)">下一页</a></s:if></span>-->
</div>
