<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div id="tab">
	<ul>
		<li <s:if test="tool==1">class="select"</s:if>><a href="/${oname }/content/ebProductList.action?subtype=${subtype}">商品管理</a></li>	
		<li <s:if test="tool==2">class="select"</s:if>><a href="/${oname }/page/marketingOrderList.action?subtype=${subtype}">订单管理</a></li>
		<s:if test='subtype=="W"'>
		<li <s:if test="tool<=0">class="select"</s:if>><a href="/${oname }/page/wsc_list.action?subtype=${subtype}&grouptype=S">应用站点</a></li>
		</s:if>
		<s:else>
		<li <s:if test="tool<=0">class="select"</s:if>><a href="/${oname }/page/wsc_list.action?subtype=${subtype}&grouptype=F">应用站点</a></li>
		</s:else>
		<li <s:if test="tool==3">class="select"</s:if>><a href="/${oname }/page/marketingHomePage.action?subtype=${subtype}">商城设置</a></li>
        <li <s:if test="tool==4">class="select"</s:if>><a href="/${oname }/page/marketingTemplate.action?subtype=${subtype}">模板消息</a></li>
	</ul>
</div>