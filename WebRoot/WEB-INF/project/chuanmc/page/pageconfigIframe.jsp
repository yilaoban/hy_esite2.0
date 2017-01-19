<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<div class="switch_tab_div pb10">
	<ul class="nav nav-tabs">
		<li class="active"><a href="javascript:pageconfig('/${oname}/page/pageconfig_iframe.action?siteid=${siteid }')">页面配置</a></li>
		<li><a href="javascript:wxpublish('/${oname}/page/publish_to_wx_iframe.action?siteid=${siteid }')">微信分享设置</a></li>
		<li><a href="javascript:void(0);" onclick="publish_sina('${oname }',${siteid })">发布到微博</a>	</li>
	</ul>
</div>
<div class="toolbar pb10">
   <input value="创建页面" type="button" class="btn btn-primary" onclick="create_page(${dto.site.id})"/>
 	<div class="btn-group">
   	<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" >一键操作<span class="caret"></span></button>
   	 <ul class="dropdown-menu">
	    <li><a href="javascript:void(0);" onclick="yjsx(${dto.site.id})" >上线</a></li>
	    <li><a href="javascript:void(0);" onclick="yjgx(${dto.site.id})" >更新数据</a></li>
	    <li><a href="javascript:void(0);" onclick="yjxx(${dto.site.id})" >下线</a></li>
	  </ul>
	</div>
</div>
<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
		<tr>
			<td>页面名</td>
			<td>创建时间</td>
			<td>操作</td>
			<td>上线</td>
			<td>链接</td>
		</tr>
		<s:if test='dto.site.pages.size>0'>
			<s:iterator value="dto.site.pages" var="p" status="st"> 
				<tr>
				<s:if test='type=="C"'>
					<td>
						<a href="javascript:void(0);" onclick="updatePageName(${p.id},'${p.name}')"><spam id="spam${p.id}">${p.name}</spam></a>
					</td>
					<td>
						<s:date name="createtime" format="yyyy-MM-dd HH:mm"/>
					</td>
					<td>
							<a target="_blank" href="/${oname}/page/editpage.action?pageid=${p.id}">页面编辑</a><i class="split">|</i><a href="javascript:void(0);" onclick="del_page(${p.id},'${p.name}')">删除</a>
					</td>
					<td>
							<s:if test='#p.isonline=="N"'><a href="javascript:void(0);" onclick="takeOnline(${p.id },'${p.name }')">上线</a></s:if>
							<s:if test='#p.isonline=="Y"'><a href="javascript:void(0);" onclick="takeOffline(${p.id },'${p.name }')">下线</a><i class="split">|</i><a href="javascript:void(0);" onclick="updateData(${p.id },'${p.name }')">更新数据</a></s:if>
					</td>
				</s:if>
				<s:elseif test='type=="G"'>
					<td>
						<a href="javascript:void(0);" onclick="updatePageName(${p.id},'${p.name}')"><span id="spam${p.id}">${p.name}</span></a>
						<s:if test='type=="H"'>(首页)</s:if>
					</td>
					<td>
						<s:date name="createtime" format="yyyy-MM-dd HH:mm"/>
					</td>
					<td>
						<a href="/${oname}/page/edit_page_g.action?pageid=${p.id}" target="_blank">页面编辑</a><i class="split">|</i><a href="javascript:void(0);" onclick="$('#feature_div').load('/${oname}/page/feature.action',{pageid:${p.id }});"><span id="spam${p.id}">功能列表</span></a><i class="split">|</i><a href="javascript:void(0);" onclick="add(${p.id})">功能配置</a><i class="split">|</i><s:if test='type!="H"'><a href="javascript:void(0);" onclick="del_page(${p.id},'${p.name}')">删除</a></s:if>
					</td>
					<td>
						<s:if test='#p.isonline=="N"'><a href="javascript:void(0);" onclick="takeOnline(${p.id},'${p.name }')">上线</a></s:if>
						<s:if test='#p.isonline=="Y"'><a href="javascript:void(0);" onclick="takeOffline(${p.id },'${p.name }')">下线</a><i class="split">|</i><a href="javascript:void(0);" onclick="updateData(${p.id },'${p.name }')">更新数据</a></s:if>
					</td>
				</s:elseif>
				<s:elseif test='type=="H"'>
					<td>
						<a href="javascript:void(0);" onclick="updatePageName(${p.id},'${p.name}')"><spam id="spam${p.id}">${p.name}</spam></a>
					</td>
					<td>
						<s:date name="createtime" format="yyyy-MM-dd HH:mm"/>
					</td>
					<td>
							<a target="_blank" href="/${oname}/page/editpage.action?pageid=${p.id}">页面编辑</a><i class="split">|</i><a href="javascript:void(0);" onclick="del_page(${p.id},'${p.name}')">删除</a>
					</td>
					<td>
							<s:if test='#p.isonline=="N"'><a href="javascript:void(0);" onclick="takeOnline(${p.id },'${p.name }')">上线</a></s:if>
							<s:if test='#p.isonline=="Y"'><a href="javascript:void(0);" onclick="takeOffline(${p.id },'${p.name }')">下线</a><i class="split">|</i><a href="javascript:void(0);" onclick="updateData(${p.id },'${p.name }')">更新数据</a></s:if>
					</td>
				</s:elseif>
				<s:else>
					<td>
						<a href="javascript:void(0);" onclick="updatePageName(${p.id},'${p.name}')"><spam id="spam${p.id}">${p.name}</spam></a>
					</td>
					<td>
						<s:date name="createtime" format="yyyy-MM-dd HH:mm"/>
					</td>
					<td>
						<a target="_blank" href="/${oname}/page/edit_kongbaipage.action?pageid=${p.id}">页面编辑</a>
						<a href="/${oname}/page/preview.action?pageid=${p.id}" target="_blank"><STRONG>预览</STRONG></a>
						<a href="javascript:void(0);" onclick="del_page(${p.id},'${p.name}')">删除</a>
					</td>
					<td>
						<s:if test='status!=PUB'><a href="javascript:void(0);" onclick="takeOnline(${p.id},'${p.name }')">上线</a></s:if>
					</td>
				</s:else>
				<td><s:if test='#p.isonline=="Y"'><a href="javascript:void(0);" onclick="promotion(${p.id })">查看</a></s:if></td>
				</tr>
			</s:iterator>
		</s:if>
</table>
<div id="feature_div">
</div>
<div id="normalModal" class="modal fade bs-example-modal-lg" role="dialog" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
    </div>
  </div>
</div>
<div id="wideModal" class="modal fade bs-example-modal-lg" role="dialog" aria-hidden="true">
  <div class="modal-dialog" style="width:900px;">
    <div class="modal-content">
    </div>
  </div>
</div>
<div id="myModal1" class="modal fade bs-example-modal-lg" role="dialog" aria-hidden="true">
	<div class="modal-dialog" style="width:400px;">
			<div class="modal-content">
			</div>
	</div>
</div>
<div id="sckModal" class="modal fade bs-example-modal-lg" role="dialog" aria-hidden="true">
	<div class="modal-dialog" style="width:800px;">
			<div class="modal-content">
			</div>
	</div>
</div>