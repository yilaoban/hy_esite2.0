<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<script type="text/javascript">
$(document).ready(function(){
	var siteid = '${siteid}';
	if(siteid == 0){
		setTimeout("$('#feature_div').load('/page/feature.action',{pageid:${dto.sites[0].pages[0].id}})",1000);
	}
})
</script>
<div class="wrap_content">
<div class="switch_tab_div pb10">
	<span><a href="/page/sitegroupList.action">微博page</a>着陆页微信page <i class="gt">&gt;&gt;</i> ${dto.site.name }</span>
	<p class="clearfix"></p>
</div>
<s:if test="dto.sites.size > 0">
<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>导航名</th>
			<th>页面数</th>
			<th>页面名</th>
			<th>功能操作</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.sites" var="s">
			<tr align="center" >
				<!-- 导航名 -->
			    <td <s:if test="#s.pages.size > 0"> rowspan="${fn:length(s.pages)}" </s:if>>${s.name}</td>
			    <!-- 页面数 -->
			    <td <s:if test="#s.pages.size > 0"> rowspan="${fn:length(s.pages)}" </s:if>><s:property value="#s.pages.size"></s:property>
			    	<a href="javascript:void(0);" onclick="create_page(${s.id},0)"/>创建页面</a>
				</td>
			<s:if test='#s.pages.size>0'>
			<s:iterator value="#s.pages" var="p" status="status"> 
			<s:if test="#status.index!=0"><tr></s:if>
			<!-- 页面名 -->
				<td>
					<a href="javascript:void(0);" onclick="$('#feature_div').load('/page/feature.action',{pageid:${p.id }});"><spam id="spam${p.id}">${p.name}</spam></a>
					<s:if test='type=="H"'>(首页)</s:if>
				</td>
				<td>
					<a href="javascript:void(0);" onclick="add(${p.id})">增加</a>
				</td>
				<td>
					<a href="/page/preview.action?pageid=${p.id}" target="_blank"><STRONG>预览</STRONG></a>
					<!-- 
					<a href="javascript:void(0);" onclick="create_page(${s.id},${p.id})">编辑</a> 
					 -->
					<s:if test='type!="H"'><a href="javascript:void(0);" onclick="del_page(${p.id},'${p.name}')">删除</a></s:if>
					<s:if test='type!="H"'><a href="javascript:void(0);" onclick="setHome(${s.id},${p.id },'${p.name}')">设为首页</a></s:if>
					<s:if test='status!=PUB'><a href="javascript:void(0);" onclick="pubPage(${p.id},'${p.name }')">上线</a></s:if>
				</td>
			</tr>
			</s:iterator>
			</s:if>
			<s:else>
				<td></td><td></td><td></td></tr>
			</s:else>
			</tr>
		</s:iterator>
	</tbody>
</table>
</s:if>
<s:else>
	还没有站点
</s:else>
<div id="feature_div">
</div>
</div>