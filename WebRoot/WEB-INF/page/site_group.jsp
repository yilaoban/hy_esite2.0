<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<div class="wrap_content">
<div class="toolbar pb10">
    <input value="创建站点" type="button" onclick="create_sitegroup()"/>
</div>
<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>站点名称</th>
			<th>站点类型</th>
			<th>最近更新时间</th>
			<th>活动报告</th>
			<th>互动报告</th>
			<th>用户报告</th>
			<th>管理</th>
			<th>站点配置</th>	
			<!--  
			<th>操作</th>
			-->
		</tr>
	</thead>
	<tbody>
		
			<tr align="center" >
			    <td>${sitegroup.groupname}</td>
			    <td>
			    	<s:if test='#attr.sitegroup.type=="W"'>微博page</s:if>
			    	<s:elseif test='#attr.sitegroup.type=="C"'>着陆页</s:elseif>
			    	<s:elseif test='#attr.sitegroup.=="Q"'>微信page</s:elseif>
				</td>
				<td>
					<s:date name="sitegroup.updatetime" format="yyyy-MM-dd HH:mm:ss"/>
				</td>
			    <td><a href="/page/activity_report.action?sitegroupid=${sitegroup.id}">进入</a></td>
			    <td><a href="/page/hd_report.action?sgid=${sitegroup.id}">进入</a></td>
			    <td><a href="/page/visit_report.action?sgid=${sitegroup.id}">进入</a></td>
			    <td><a href="/page/site_list.action?sitegroupid=${sitegroup.id}">进入</a></td>
			    <td>
			        <a href="/page/pageconfig.action?sitegroupid=${sitegroup.id}">进入</a>
			    </td>
			    <!-- 
			    <td>
			        <a href="javascript:void(0)" onclick="del_site_group(${s.id},'${s.groupname}')">删除</a>
			        <a href="javascript:void(0)" onclick="updateSiteGroup(${s.id},'${s.groupname}')">编辑</a>
			    </td>
			    -->
			</tr>	
	</tbody>
</table>
</div>