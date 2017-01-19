<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<div class="wrap_content">
<a href="/loadpage/loadPageIndex.action">导航列表</a> >> ${dto.site.name}
<div class="toolbar pb10">
          <input type="button" onclick="create_page(${dto.site.id},0)" value="创建页面">
</div>
<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th width="20%">导航名</th>
			<th width="10%">页面名</th>
			<th width="10%">功能操作</th>
			<th width="10%">功能查看</th>
			<th width="20%">创建时间</th>
			<th width="20%">操作</th>
		</tr>
	</thead>
	<tbody>
			<tr align="center" >
				<td <s:if test="#attr.dto.pages.size > 0"> rowspan="${fn:length(dto.pages)}" </s:if>>
					${dto.site.name}<br>
				</td>
			<s:if test='#attr.dto.pages.size>0'>
			<s:iterator value="dto.pages" var="p" status="status">
			 <s:if test="#attr.status.index!=0"><tr ></s:if>
				<td>
					<a href="javascript:void(0);" onclick="$('#feature_div').load('/page/feature.action',{pageid:${p.id }});">${p.name}</a>
					<s:if test='type=="H"'>(首页)</s:if>
				</td>
				<td>
					<a href="javascript:void(0);" onclick="add(${p.id})">增加</a>
				</td>
				<td>
					<a href="/user/show.action?pageid=${p.id}" target="_blank">浏览</a>
				</td>
				<td>
					<s:date name="createtime" format="yyyy-MM-dd HH:mm"/>
				</td>
				<td>
					<a href="javascript:void(0);" onclick="create_page(${dto.site.id},${p.id})">编辑</a> 
					<s:if test='type!="H"'><a href="javascript:void(0);" onclick="del_page(${p.id},'${p.name}')">删除</a></s:if>
					<s:if test='type!="H"'><a href="javascript:void(0);" onclick="setHome(${dto.site.id},${p.id },'${p.name}')">设为首页</a></s:if>
					<s:if test='status!=PUB'><a href="javascript:void(0);" onclick="pubPage(${p.id},'${p.name }')">上线</a></s:if>
				</td>
			 </tr>
			</s:iterator>
			</s:if>
			<s:else>
				<td></td><td></td><td></td><td></td><td></td></tr>
			</s:else>
	</tbody>
</table>
<div id="feature_div">
</div>
</div>