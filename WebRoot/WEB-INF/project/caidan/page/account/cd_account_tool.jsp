<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="tab">
  <ul>
  	 <li <s:if test='type=="ALZ"'>class="select"</s:if>><a href='index.action?type=ALZ'>渠道总监</a></li>
  	 <li <s:if test='type=="PRZ"'>class="select"</s:if>><a href='index.action?type=PRZ'>渠道经理</a></li>
  	 <li <s:if test='type=="YYR"'>class="select"</s:if>><a href='index.action?type=YYR'>运营经理</a></li>
  </ul>
</div>
