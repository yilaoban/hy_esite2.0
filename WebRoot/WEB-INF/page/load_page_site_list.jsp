<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<div class="wrap_content">
<s:if test="dto.siteList.size>0">
<div class="toolbar pb10">
    <input value="创建导航" type="button" onclick="update_load_site(0)"/>
</div>
<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>导航名称</th>
			<th>page数</th>
			<th>上线日期</th>
			<th>绑定账号</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
	      <s:iterator value="dto.siteList" var="st" status="status">
			<tr align="center" >
					<td>
						<a href="/loadpage/loadPage.action?siteid=${st.id}">${st.name}</a><br>
					</td>
					<td>
						${st.pageTotal } 
					</td>
					<td>
						<s:date name="createtime" format="yyyy-MM-dd HH:mm"/>
					</td>
					<td>${st.sinaToken.nickname }</td>
					<td>
						<a href="javascript:void(0);" onclick="update_load_site(${st.id})">编辑</a><i class="split">|</i>
						<a href="javascript:void(0)" onclick="del_load_site(${st.id},'${st.name }')">删除</a><i class="split">|</i>
						  <s:if test='%{#st.sinaToken.token==null}'>
						  <a href="/user/oauthlink.action?siteid=${st.id}">授权</a>
						  </s:if> 
						 <s:elseif test='%{#st.sinaToken.outOfEndTime<=0}'>
						 <a href="/user/oauthlink.action?siteid=${st.id}">重新授权</a>
						 </s:elseif>
						 <s:else>
						     已经授权
						 </s:else>
					</td>
				</tr>
				</s:iterator>
	</tbody>
</table>
</s:if>
<s:else>
	还没有站点
</s:else>
</div>