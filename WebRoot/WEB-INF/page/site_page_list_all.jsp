<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
<s:if test="dto.sites.size>0">
<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>导航名</th>
			<th>状态</th>
			<th>所属站点</th>
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
			        <s:if test='%{#s.sinaToken.token==null}'>
						  不在线
				    </s:if> 
				    <s:elseif test='%{#s.sinaToken.outOfEndTime<=0}'>
						 已过期
				    </s:elseif>
				    <s:else>
						 在线
				    </s:else>
			    </td>
			    <td>
			       ${s.groupName}
			    </td>
			    <td>
			      <a href="javascript:void(0);" onclick="$('#feature_div').load('/page/feature_list.action',{siteid:${s.id }});">板块</a>
			       <s:if test='%{#s.sinaToken.token==null}'>
						  <a href="/user/oauthlink.action?siteid=${s.id}">授权</a>
				    </s:if> 
				    <s:elseif test='%{#s.sinaToken.outOfEndTime<=0}'>
						 <a href="/user/oauthlink.action?siteid=${s.id}">重新授权</a>
				    </s:elseif>
				    <s:else>
						    取消授权
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