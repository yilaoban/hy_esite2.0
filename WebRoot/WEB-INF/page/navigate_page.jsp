<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<div class="wrap_content">
<div class="switch_tab_div pb10">
	<span><s:if test='dto.sitegroup.type=="W"'><a href="/page/sitegroupList.action">微博page</a></s:if><s:elseif test='dto.sitegroup.type=="C"'>着陆页</s:elseif><s:elseif test='dto.sitegroup.type=="Q"'>微信page</s:elseif> <i class="gt">&gt;&gt;</i> ${dto.sitegroup.groupname }</span><br/>
</div>
<div class="toolbar pb10">
    <input value="创建导航" type="button" onclick="create_site(${dto.sitegroup.id})"/>
</div>
<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>导航名</th>
			<th>活动数</th>
			<th>版块数</th>
			<th>操作</th>
			<th>创建时间</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.sites" var="s">
			<tr align="center" >
			    <td>
			       ${s.name}
			    </td>
			    <td>
				    <a href="javascript:void(0);" onclick="$('#activity_div').load('/page/activity_list.action',{siteid:${s.id }});"><spam id="spam${s.id}">${s.activityCount}</spam></a>
				    <a href="javascript:void(0);" onclick="create_activity(${s.id})">创建</a>
			    </td>
			    <td>
			       <s:property value="#s.modules.size"/>
			    </td>
			    <td>
			    	<a href="javascript:void(0);" onclick="update_site(${s.id})">编辑</a><i class="split">|</i>
			       <a href="javascript:void(0);" onclick="del_site(${s.id },'${s.name}')">删除</a><i class="split">|</i>
			      	<s:if test='#s.sinaToken == null || #s.sinaToken.tokenendtime == null'>
						  <a href="/user/oauthlink.action?siteid=${s.id}">授权</a>
				    </s:if> 
				    <s:elseif test='#s.sinaToken.outOfEndTime <= 0'>
						 <a href="/user/oauthlink.action?siteid=${s.id}">重新授权</a>
				    </s:elseif>
				    <s:else>
						    取消授权
				    </s:else>
			    </td>
			    <td>
			       <s:date name="createtime" format="yyyy-MM-dd HH:mm:ss"/>
			    </td>
			</tr>	
		</s:iterator>
	</tbody>
</table>
<div id="activity_div"></div>
</div>