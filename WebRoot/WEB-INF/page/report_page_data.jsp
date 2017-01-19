<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
	   <table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
		 <tr>
		    <th><font style=" font-weight:bold">页面名</font></th>
		    <th><font style=" font-weight:bold">所属站点</font></th>
		    <th><font style=" font-weight:bold">新增访问数</font></th>
		    <th><font style=" font-weight:bold">新增互动数</font></th>
		    <th><font style=" font-weight:bold">总访问数</font></th>
		    <th><font style=" font-weight:bold">总互动数</font></th>
	      </tr>
		<s:iterator value="dto.list" var="c" status="u">
			<tr>
			      <td >${c.pagename }</td>
			      <td >${c.sitename }</td>
			      <td >${c.addvnum}</td>
			      <td >${c.addhnum}</td>
			      <td >${c.totalvnum}</td>
			      <td >${c.totalhnum}</td>
			</tr>
		</s:iterator>
		
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
	    <iframe src="/data/owner_visit_data.action"  width="100%" height="100%" scrolling="no" frameborder="0" marginheight="0" marginwidth="0" onload="this.height=this.contentWindow.document.documentElement.scrollHeight">
	    </iframe>
	</div>