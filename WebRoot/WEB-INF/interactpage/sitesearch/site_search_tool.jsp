<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="tab">
  <ul>
    <li <s:if test="lightType==1">class="select"</s:if>>
      <a href="/${oname}/interact/site_search.action">站内搜索</a>
    </li>
    <li <s:if test="lightType==2">class="select"</s:if>>
      <a href="/${oname}/interact/site_search_config.action">搜索配置</a>
    </li>
    <li <s:if test="lightType==3">class="select"</s:if>>
      <a href="/${oname}/interact/site_search_app_site.action?grouptype=Q&lightType=3">应用站点</a>
    </li>
    <s:set name="app_name" value="'站内搜索'" ></s:set>
    <s:include value="/WEB-INF/page/my_app_hyperlink.jsp" />
  </ul>
</div>