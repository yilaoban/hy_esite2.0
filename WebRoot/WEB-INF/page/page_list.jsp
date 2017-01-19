<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<script type="text/javascript">
$(document).ready(function(){
	var siteid = ${siteid};
	if(siteid == 0){
		setTimeout("$('#feature_div').load('/page/feature.action',{pageid:${dto.sites[0].pages[0].id}})",1000);
	}
})
</script>
<div class="wrap_content">
<div class="toolbar pb10">
	<span class="breadcumb">微博PAGE >> ${dto.sitegroup.groupname }</span>
    <input value="创建导航" type="button" onclick="create_site(${dto.sitegroup.id})"/>
</div>
<s:if test="dto.sites.size>0">
<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
	<thead>
		<tr>
			<th>导航名</th>
			<th>页面名</th>
			<th>功能操作</th>
			<th>功能查看</th>
			<th>创建时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.sites" var ="s">
			<tr align="center" >
				<td <s:if test="#s.pages.size > 0"> rowspan="${fn:length(s.pages)}" </s:if>>
					${s.name}
					<a href="javascript:void(0);" onclick="update_site(${s.id})">编辑</a><i class="split">|</i>
					<a href="javascript:void(0)" onclick="del_site(${s.id},'${s.name }')">删除</a><i class="split">|</i>
					<a href="javascript:void(0);" onclick="create_page(${s.id},0)"/>创建页面</a>
				</td>
			<s:if test='#s.pages.size>0'>
			<s:iterator value="#s.pages" var="p" status="status"> 
			<s:if test="#status.index!=0"><tr ></s:if>
				<td>
					<a href="javascript:void(0);" onclick="$('#feature_div').load('/page/feature.action',{pageid:${p.id }});"><spam id="spam${p.id}">${p.name}</spam></a>
					<s:if test='type=="H"'>(首页)</s:if>
				</td>
				<td>
					<a href="javascript:void(0);" onclick="add(${p.id})">增加</a>
				</td>
				<td>
					<a href="/page/preview.action?pageid=${p.id}" target="_blank"><STRONG>预览</STRONG></a>
				</td>
				<td>
					<s:date name="createtime" format="yyyy-MM-dd HH:mm"/>
				</td>
				<td>
					<a href="javascript:void(0);" onclick="create_page(${s.id},${p.id})">编辑</a> 
					<s:if test='type!="H"'><a href="javascript:void(0);" onclick="del_page(${p.id},'${p.name}')">删除</a></s:if>
					<s:if test='type!="H"'><a href="javascript:void(0);" onclick="setHome(${s.id},${p.id },'${p.name}')">设为首页</a></s:if>
					<s:if test='status!=PUB'><a href="javascript:void(0);" onclick="pubPage(${p.id},'${p.name }')">上线</a></s:if>
					<s:if test='#s.sinaToken == null'>
						  <a href="/user/oauthlink.action?siteid=${s.id}">授权</a>
				    </s:if> 
				    <s:elseif test='#s.sinaToken.outOfEndTime<=0'>
						 <a href="/user/oauthlink.action?siteid=${s.id}">重新授权</a>
				    </s:elseif>
				    <s:else>
						    取消授权
				    </s:else>
				</td>
			</tr>
			</s:iterator>
			</s:if>
			<s:else>
				<td></td><td></td><td></td><td></td><td></td></tr>
			</s:else>
			<s:if test="#s.id == siteid">
				<script type="text/javascript">
					var pageid=${s.pages[0].id};
					setTimeout("$('#feature_div').load('/page/feature.action',{pageid:"+${s.pages[0].id}+"})",1000);
					$("#spam"+pageid).css("color","red");
				</script>
			</s:if>
		</s:iterator>
	</tbody>
</table>
</s:if>
<s:else>
	该微博PAGE还没有页面
</s:else>
<div id="feature_div">
</div>
</div>