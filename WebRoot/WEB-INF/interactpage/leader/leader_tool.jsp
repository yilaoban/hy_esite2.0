<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="tab">
  <ul>
    <li <s:if test="lightType==1">class="select"</s:if>>
      <a href="/${oname}/interact/leaderIndex.action">潜客</a>
    </li>
    <s:set name="app_name" value="'潜客管理'" ></s:set>
   <s:include value="/WEB-INF/page/my_app_hyperlink.jsp" />
  </ul>
</div>
