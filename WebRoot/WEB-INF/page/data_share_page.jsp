<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
	<div class="toolbar pb10">
		<ul class="c_switch">
			<li><a href="/${oname}/data/share.action">趋势</a></li>
			<li><a href="/${oname}/data/share_user.action">用户</a></li>
			<li class="selected"><a href="/${oname}/data/share_page.action">页面</a></li>
		</ul>
	</div>
	<div class="toolbar mt20">
		<input id="pname" value="${pname}" placeholder="页面名称">
		<a href="javascript:void(0);" onclick="searchPage()">搜索</a>
	</div>
	<table width="100%" cellspacing="1" cellpadding="1" border="1" class="tb_normal">
		<thead>
			<tr>
				<th>页面</th>
				<th>站点</th>
				<th>转发(人数)</th>
				<th>点击(人数)</th>
				<th>关注(人数)</th>
				<th>互动(人数)</th>
				<th>外部(人数)</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.plist" var="p">
				<tr align="center">
					<td id="p_${p.pageid}"><a href="/${oname}/user/show.action?pageid=${p.pageid}" target="_blank">${p.pagename}</a></td>
					<td id="s_${p.pageid}">${p.sitename}</td>
					<td><s:if test="#p.share>0">
							<a href="javascript:void(0);" onclick="showDetail('${p.pageid}','s')">${p.share} (${p.share_g})</a>
						</s:if> <s:else>${p.share}</s:else></td>
					<td><s:if test="#p.click>0">
							<a href="javascript:void(0);" onclick="showDetail('${p.pageid}','c')">${p.click} (${p.click_g})</a>
						</s:if> <s:else>${p.click}</s:else>
					<td><s:if test="#p.subscribe>0">
							<a href="javascript:void(0);" onclick="showDetail('${p.pageid}','g')">${p.subscribe} (${p.subscribe_g})</a>
						</s:if> <s:else>${p.subscribe}</s:else></td>
					<td><s:if test="#p.interact>0">
							<a href="javascript:void(0);" onclick="showDetail('${p.pageid}','h')">${p.interact} (${p.interact_g})</a>
						</s:if> <s:else>${p.interact}</s:else></td>
					<td><s:if test="#p.outer>0">
							<a href="javascript:void(0);" onclick="showDetail('${p.pageid}','w')">${p.outer} (${p.outer_g})</a>
						</s:if> <s:else>${p.outer}</s:else></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<div id="pagination" class="mt10">
		<a href="javascript:void(0);" class="pre" id="prev" style="display: none;">上一页</a>
		<a href="javascript:void(0);" class="next" id="next" style="display: none;">下一页</a>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		var pageId = parseInt('${pageId}');
		if ($("tbody").find("tr").size() == 10) {
			$("#next").attr("href", "/${oname}/data/share_page.action?pname=${pname}&pageId=" + (pageId + 1));
			$("#next").show();
		}
		if (pageId > 1) {
			$("#prev").attr("href", "/${oname}/data/share_page.action?pname=${pname}&pageId=" + (pageId - 1));
			$("#prev").show();
		}
	});

	function searchPage() {
		var pname = $("#pname").val().trim();
		if (pname == "") {
			return;
		}
		layer.open({
			type : 2,
			area : [ '600px', '550px' ],
			title : '页面搜索',
			content : "/${oname}/data/share_page_search.action?pname=" + pname
		});
	}

	function showDetail(pageid, atype) {
		layer.open({
			type : 2,
			area : [ '600px', '550px' ],
			title : '详情',
			content : "/${oname}/data/share_page_detail.action?page=" + pageid + "&atype=" + atype
		});
	}

	
</script>