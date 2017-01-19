<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
	<!-- <div class="switch_tab_div">
		<a href="/data/page.action?ptype=today"
			<s:if test="ptype=='today'">class="switch_tab_b"</s:if>
			<s:else>class="switch_tab_a"</s:else>>今日上线页面</a>
		<a href="/data/page.action?ptype=week"
			<s:if test="ptype=='week'">class="switch_tab_b"</s:if>
			<s:else>class="switch_tab_a"</s:else>>本周上线页面</a>
		<a href="/data/page.action?ptype=top10"
			<s:if test="ptype=='top10'">class="switch_tab_b"</s:if>
			<s:else>class="switch_tab_a"</s:else>>Top10页面</a>
	</div>
	<div class="tab_content"> -->
	<table width="100%" cellspacing="1" cellpadding="1" border="1" class="tb_normal">
		<thead>
			<tr>
				<th>页面名称</th>
				<th>所属站点</th>
				<th>上线时间</th>
				<th>历史趋势分析</th>
				<th>用户访问详情</th>
				<th>PV</th>
				<th>UV</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="plist" var="p">
				<tr align="center">
					<td>${p.name}</td>
					<td>${p.jspname}</td>
					<td><s:date name="updatetime" format="yyyy-MM-dd HH:mm:ss" /></td>
					<td><a href="javascript:void(0);" onclick="showData('${p.id}','${p.name}')">分析</a></td>
					<td><a href="javascript:void(0);" onclick="showUser('${p.id}','${p.name}')">详情</a></td>
					<td>${p.pv}</td>
					<td>${p.uv}</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<form id="form" action="/${oname}/data/page.action" method="post">
		<input type="hidden" name="pageId" />
	</form>
	<div id="pagination" class="mt10">
			<a href="javascript:void(0);" class="pre" id="prev" style="display:none;">上一页</a>
			<a href="javascript:void(0);" class="next" id="next" style="display:none;">下一页</a>
	</div>
	<input id="pagename" type="hidden" />
	<iframe id="data" src="" width="100%" height="100%" scrolling="no" frameborder="0" marginheight="0" marginwidth="0"
		onload="this.height=this.contentWindow.document.documentElement.scrollHeight"> </iframe>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		if ($("tbody").find("tr").size() == 10) {
			$("#next").show();
		}
		var pageId = parseInt('${pageId}');
		if (pageId > 1) {
			$("#prev").show();
		}
		$("#prev").click(function() {
			$("#form").find("input").val(pageId - 1);
			$("#form").submit();
		});
		$("#next").click(function() {
			$("#form").find("input").val(pageId + 1);
			$("#form").submit();
		});

	});

	var ptype = "top10";
	var gtype = "DAY";

	function showData(pageid, pagename) {
		$("#pagename").val(pagename);

		var url = "/${oname}/data/iframe_data_page.action?mtype=wx&gtype=" + gtype + "&ptype=" + ptype + "&pageid=" + pageid;
		$("#data").attr("src", url);
		$("body").animate({
			scrollTop : $("#data").offset().top
		}, 'slow');
	}

	function showUser(pageid, pagename) {
		$("#pagename").val(pagename);

		var url = "/${oname}/data/iframe_user_page.action?mtype=wx&pageid=" + pageid;
		$("#data").attr("src", url);
		$("body").animate({
			scrollTop : $("#data").offset().top
		}, 'slow');
	}

	
</script>