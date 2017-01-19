<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
<div class="toolbar pb10">
	<span class="breadcumb"> <a href="/page/sitegroupList.action">${dto.sitegroup.groupname }</a> >> 导航</span>
</div>
<s:if test="dto.sites.size>0">
<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>导航名</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.sites" var ="s">
			<tr align="center" >
			    <td>
			       <a href="javascript:void(0);" onclick="changeSiteName(${s.id},'${s.name }')"><span id="sitename${s.id }">${s.name }</span></a>
			    </td>
			    <td>
			      <a href="javascript:void(0);" onclick="$('#feature_div').load('/page/feature_list.action',{siteid:${s.id }});"><STRONG>板块</STRONG></a>
			      <a href="javascript:void(0);" onclick="pubPageAll(${s.id},'${s.name }')"><STRONG>更新</STRONG></a>
			      <s:if test='%{#s.homepageId>0}'>
			          <a href="/page/preview.action?pageid=${s.homepageId}" target="_blank"><STRONG>预览</STRONG></a>
			      </s:if>
			      <s:else>
			            暂无页面
			      </s:else>
			    </td>
		    </tr>
		</s:iterator>
	</tbody>
</table>
</s:if>
<s:else>
	该微博还没有创建站点
</s:else>
<div id="feature_div">
</div>
</div>