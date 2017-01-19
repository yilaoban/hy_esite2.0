<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div id="tab">
  <ul>
    <li <s:if test="lightType==2">class="select"</s:if>>
      <a href="/${oname }/content/ebProductList.action?subtype=${subtype}">商品管理</a>
    </li>
    <li <s:if test="lightType==3">class="select"</s:if>>
      <a href="/${oname }/page/marketingOrderList.action?subtype=${subtype}">订单管理</a>
    </li>
    <li <s:if test="lightType==1">class="select"</s:if>>
      <a href="/${oname }/page/wsc_list.action?subtype=${subtype}&grouptype=<s:if test='subtype=="W"'>S</s:if><s:elseif test='subtype=="J"'>F</s:elseif>">应用站点</a>
    </li>
    <%--
    <li <s:if test="lightType==4">class="select"</s:if>>
      <a href="/${oname }/pay/config.action?subtype=${subtype}">商城设置</a>
    </li>
     --%>
    <li <s:if test="lightType==5">class="select"</s:if>>
      <a href="/${oname}/pay/template.action?subtype=${subtype}">模板消息</a>
    </li>
    <s:if test='subtype=="W"'>
    	    <s:set name="app_name" value="'微商城'" ></s:set>
    </s:if>
    <s:elseif test='subtype=="J"'>
	    <s:set name="app_name" value="'积分商城'" ></s:set>
    </s:elseif>
    <%@include file="/WEB-INF/page/my_app_hyperlink.jsp" %>
  </ul>
</div>