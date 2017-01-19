<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<div class="wrap_content">
<div class="switch_tab_div pb10">
		<s:if test='dto.sitegroup.stype=="E"'>
			<span><a href="/${oname}/page/sitegroupList.action">我的活动</a><i class="gt">&gt;&gt;</i> ${dto.site.name }</span>
		</s:if>
		<s:if test='dto.sitegroup.stype=="W"'>
			<span><a href="/${oname}/page/website.action">我的网站</a><i class="gt">&gt;&gt;</i> ${dto.site.name }</span>
		</s:if>
		<s:if test='dto.sitegroup.stype=="X"'>
			<span><a href="/${oname}/page/xcSiteGroup.action">我的现场</a><i class="gt">&gt;&gt;</i><a href="/${oname }/page/xcSite.action?xcid=${xcid}">${dh.sitegroup.groupname }</a><i class="gt">&gt;&gt;</i>${dto.site.name }</span>
		</s:if>
	<p class="clearfix"></p>
</div>
<div class="toolbar pb10">
   <input value="创建页面" type="button" class="btn btn-primary" onclick="create_page(${dto.site.id})"/>
   <input value="上传整站" type="button" class="btn btn-primary" onclick="upload_page_html(${dto.site.id})" />
   <s:if test='dto.site.upWhole == "Y"'>
   <div class="btn-group">
   	<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" >整站资源<span class="caret"></span></button>
   	 <ul class="dropdown-menu">
	    <li><a href="/${oname}/page/editFile.action?siteid=${dto.site.id}&isImg=N" target="_blank">css/js</a></li>
	    <li><a href="/${oname}/page/editFile.action?siteid=${dto.site.id}&isImg=Y" target="_blank">图片管理</a></li>
	    <li><a href="/${oname}/page/editFile.action?siteid=${dto.site.id}&isImg=P" target="_blank">通用代码管理</a></li>
	  </ul>
	</div>
	   <input value="整站同步" type="button" class="btn btn-primary" onclick="upwhole('${sessionScope.account.owner.entity }',${dto.site.id})" />
   	   <%-- <input value="恢复备份" type="button" onclick="beifen('${sessionScope.account.owner.entity }',${dto.site.id})" />--%>
   </s:if>
 	<div class="btn-group">
   	<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" >一键操作<span class="caret"></span></button>
   	 <ul class="dropdown-menu">
	    <li><a href="javascript:void(0);" onclick="yjsx(${dto.site.id})" >上线</a></li>
	    <li><a href="javascript:void(0);" onclick="yjgx(${dto.site.id})" >更新数据</a></li>
	    <li><a href="javascript:void(0);" onclick="yjxx(${dto.site.id})" >下线</a></li>
	  </ul>
	</div>
	<s:if test='#session.account.owner.sup=="Y"'>
	<div class="btn-group">
	  <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" >超级用户特权<span class="caret"></span></button>
	  <ul class="dropdown-menu">
	    <li><a href="javascript:void(0);" onclick="createMb(${dto.site.id})">创建模板</a></li>
	    <li><a href="source_manager.action?siteid=${dto.site.id}">资源列表</a></li>
	</ul>
	</div>
	</s:if>
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
				<tr >
				<s:if test='type=="C"'>
					<td >
						<a href="javascript:void(0);" onclick="updatePageName(${p.id},'${p.name}')"><spam id="spam${p.id}">${p.name}</spam></a>
					</td>
					<td>
						<s:date name="createtime" format="yyyy-MM-dd"/>
					</td>
					<td>
							<a target="_blank" href="/${oname}/page/editpage.action?pageid=${p.id}">页面编辑</a><i class="split">|</i><a href="javascript:void(0);" onclick="copyPage(${p.id},'${dto.site.id }')">页面复制</a><i class="split">|</i><a href="javascript:void(0);" onclick="del_page(${p.id},'${p.name}')">删除</a>
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
						<s:date name="createtime" format="yyyy-MM-dd"/>
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
						<s:date name="createtime" format="yyyy-MM-dd"/>
					</td>
					<td>
							<a target="_blank" href="/${oname}/page/editpage.action?pageid=${p.id}">页面编辑</a><i class="split">|</i><a href="javascript:void(0);" onclick="copyPage(${p.id},'${dto.site.id }')">页面复制</a><i class="split">|</i><a href="javascript:void(0);" onclick="del_page(${p.id},'${p.name}')">删除</a>
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
						<s:date name="createtime" format="yyyy-MM-dd"/>
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