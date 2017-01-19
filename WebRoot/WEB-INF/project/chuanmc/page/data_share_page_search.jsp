<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="toolbar">
	<input id="pname" value="${pname}" placeholder="页面名称">
	<a href="javascript:void(0);" onclick="searchPage()">搜索</a>
</div>
<table width="100%" cellspacing="1" cellpadding="1" border="1" class="tb_normal">
	<thead>
		<tr>
			<th>页面名称</th>
			<th>所属站点</th>
			<th>上线时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="dto.plist" var="p">
			<tr align="center">
				<td>${p.pagename}</td>
				<td>${p.sitename}</td>
				<td><s:date name="onlinetime" format="yyyy-MM-dd HH:mm:ss" /></td>
				<td><a href="javascript:void(0);" onclick="choosePage(${p.pageid})">选择</a></td>
			</tr>
		</s:iterator>
	</tbody>
</table>
<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {
	});
	function searchPage(){
		var pname = $("#pname").val().trim();
		if (pname == "") {
			return;
		}
		window.location.href="/${oname}/data/share_page_search.action?pname=" + pname;
	}
	function choosePage(page){
		parent.window.location.href="/${oname}/data/share_page.action?page=" + page;
	}
</script>