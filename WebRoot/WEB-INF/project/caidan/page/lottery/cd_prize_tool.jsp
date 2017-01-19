<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="tab">
  <ul>
	<li <s:if test='returnType == "Y"'>class="select"</s:if>><a href="/${oname}/cd-lottery/index.action?htype=Y">摇一摇</a></li>
	<li <s:if test='returnType == "L"'>class="select"</s:if>><a href="/${oname}/cd-lottery/index.action?htype=L">砸金蛋</a></li>
	<li <s:if test='returnType == "H"'>class="select"</s:if>><a href="/${oname}/cd-lottery/index.action?htype=H">抢红包</a></li>				
  </ul>
</div>
