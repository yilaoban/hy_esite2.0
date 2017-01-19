<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="tab">
	<ul>
	   <s:if test="datatype==0">
		<li class="select"><a href="data/report_index.action">全部站点</a></li>
		 <s:iterator value="indexDto.siteList" var="s">
		     <li><a href="/data/site_report.action?siteid=${s.id}">${s.name}</a></li>
		 </s:iterator>
		</s:if>
		<s:else>
		    <li><a href="/data/report_index.action">全部站点</a></li>
		    <s:iterator value="siteData.sitelist" var="s">
		     <li <s:if test='datatype==#s.id'> class="select" </s:if> ><a href="/data/site_report.action?siteid=${s.id}">${s.name}</a></li>
		    </s:iterator>
		</s:else>
	</ul>
</div>
