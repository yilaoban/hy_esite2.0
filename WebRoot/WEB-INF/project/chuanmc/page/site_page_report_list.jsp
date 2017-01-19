<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
<s:if test="list.size>0">
<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
		<tr align="center">
			<td>站点名称</td>
			<td>站点类型</td>
			<td>页面名称</td>
			<td>操作</td>	
		</tr>
		<s:iterator value="list" var="s" status="sta">
			<tr align="center" >
			   <s:if test="#attr.sta.index==0">
			        <td rowspan="${s.pnum}">${s.sitename}  <a href="/data/site_visit_data.action?siteid=${s.siteid}">站点报告</a></td>
			        <td rowspan="${s.pnum}">
			    	       <s:if test='stype=="C"'>PC端</s:if>
			    	      <s:elseif test='stype=="P"'>移动端</s:elseif>
				     </td>
			   </s:if> 
			   <s:else>
			        <s:if test="#attr.s.sitename!=list[#attr.sta.index-1].sitename">
			           <td rowspan="${s.pnum}">${s.sitename} <a href="/data/site_visit_data.action?siteid=${s.siteid}">站点报告</a></td>
			            <td rowspan="${s.pnum}">
			    	       <s:if test='stype=="C"'>PC端</s:if>
			    	      <s:elseif test='stype=="P"'>移动端</s:elseif>
				        </td>
			        </s:if>
			   </s:else>
			   
			    <td>${s.pagename}</td>
			    <td>
			    	<a href="/data/page_visit_area.action?src=s&pid=${s.pageid}" >微博报告</a>
			        <a href="/data/page_visit_area.action?src=q&pid=${s.pageid}" >微信报告</a>
			    </td>
			</tr>	
		</s:iterator>
</table>
</s:if>
<s:else>
	还没有站点
</s:else>
</div>