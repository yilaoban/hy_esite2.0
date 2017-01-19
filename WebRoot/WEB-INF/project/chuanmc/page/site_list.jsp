<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<div class="wrap_content">
<div class="toolbar pb10">
    <input value="创建站点" type="button" onclick="create_sitegroup()"/>
</div>
<s:if test="dto.sitegroup.size>0">
<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>站点名称</th>
			<th>导航名称</th>
			<th>page数</th>
			<th>上线日期</th>
			<th>绑定账号</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.sitegroup" var="s">
			<tr align="center" >
				<td <s:if test="#s.site.size > 0"> rowspan="${fn:length(s.site)}" </s:if>>
					<a href="/page/site.action?sitegroupid=${s.id}">${s.groupname}</a><br>
				</td>
				<s:if test='#s.site.size > 0'>
				<s:iterator value="#s.site" var="st" status="status">
					<s:if test="#status.index!=0"><tr></s:if>
					<td>
						<a href="/page/site.action?sitegroupid=${s.id}&siteid=${st.id}">${st.name}</a>
					</td>
					<td>
						${st.pageTotal }
					</td>
					<td>
						<s:date name="createtime" format="yyyy-MM-dd HH:mm"/>
					</td>
					<td>${st.sinaToken.nickname }</td>
					<td>
						<a href="javascript:void(0);" onclick="update_site(${st.id})">编辑</a><i class="split">|</i>
						<a href="javascript:void(0)" onclick="del_site(${st.id},'${st.name }')">删除</a><i class="split">|</i>
						  <s:if test='%{#st.sinaToken.token==null}'>
						  <a href="/user/oauthlink.action?siteid=${st.id}">授权</a>
						  </s:if> 
						 <s:elseif test='%{#st.sinaToken.outOfEndTime<=0}'>
						 <a href="/user/oauthlink.action?siteid=${st.id}">重新授权</a>
						 </s:elseif>
						 <s:else>
						     已授权
						 </s:else>
					</td>
				</tr>
				</s:iterator>
				</s:if>
				<s:else>
					<td></td><td></td><td></td><td></td><td></td> 
				</s:else>
		</s:iterator>
	</tbody>
</table>
<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</s:if>
<s:else>
	还没有站点
</s:else>
</div>