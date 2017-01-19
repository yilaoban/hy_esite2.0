<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="tab">
<ul>
	<s:iterator value="dto.fcategoryList" var="c" status="st">
		<li <s:if test='fcategoryid == #c.id'> class="select"</s:if> ><a href="/${oname }/material/index.action?fcategoryid=${c.id}">${c.name}</a></li>
	</s:iterator>
</ul>
</div>
