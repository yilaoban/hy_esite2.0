<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
	<div class="toolbar pb10">
		<ul class="c_switch">
			<li><a href="/${oname}/data/share.action">趋势</a></li>
			<li class="selected"><a href="/${oname}/data/share_user.action">用户</a></li>
			<li><a href="/${oname}/data/share_page.action">页面</a></li>
		</ul>
	</div>

	<div class="tac">
		<a <s:if test="atype=='f'.toString()">class="chosen"</s:if> href="/${oname}/data/share_user.action?atype=f">分享排行</a>
		/
		<a <s:if test="atype!='f'.toString()">class="chosen"</s:if> href="/${oname}/data/share_user.action?atype=c">效果排行</a>
	</div>
	<s:if test="atype!='f'.toString()">
		<div class="tac">
			<a <s:if test="atype=='c'.toString()">class="chosen"</s:if> href="/${oname}/data/share_user.action?atype=c">点击</a>
			/
			<a <s:if test="atype=='s'.toString()">class="chosen"</s:if> href="/${oname}/data/share_user.action?atype=s">转发</a>
			/
			<a <s:if test="atype=='g'.toString()">class="chosen"</s:if> href="/${oname}/data/share_user.action?atype=g">关注</a>
			/
			<a <s:if test="atype=='h'.toString()">class="chosen"</s:if> href="/${oname}/data/share_user.action?atype=h">互动</a>
			/
			<a <s:if test="atype=='w'.toString()">class="chosen"</s:if> href="/${oname}/data/share_user.action?atype=w">外部</a>
		</div>
	</s:if>

	<div class="toolbar mt20">
		<form id="dateForm" action="/${oname}/data/share_user.action">
			<input name="atype" type="hidden" value="${atype}">
			<input name="starttime" id="startTime" value="${starttime}" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
			-
			<input name="endtime" id="endTime" value="${endtime}" class="Wdate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
			<a href="javascript:void(0);" onclick="submit()">搜索</a>
		</form>
	</div>
	<table width="100%" cellspacing="1" cellpadding="1" border="1" class="tb_normal">
		<thead>
			<tr>
				<th>分享者</th>
				<th>是否是粉丝</th>
				<th>性别</th>
				<th>区域</th>
				<th>
					<s:if test="atype=='f'.toString()">分享次数</s:if>
					<s:elseif test="atype=='s'.toString()">产生转发次数</s:elseif>
					<s:elseif test="atype=='c'.toString()">产生点击次数</s:elseif>
					<s:elseif test="atype=='g'.toString()">产生关注次数</s:elseif>
					<s:elseif test="atype=='h'.toString()">产生互动次数</s:elseif>
					<s:elseif test="atype=='w'.toString()">产生外部次数</s:elseif>
				</th>
				<s:if test="atype!='f'.toString()">
					<th>
						<s:if test="atype=='s'.toString()">产生转发人数</s:if>
						<s:elseif test="atype=='c'.toString()">产生点击人数</s:elseif>
						<s:elseif test="atype=='g'.toString()">产生关注人数</s:elseif>
						<s:elseif test="atype=='h'.toString()">产生互动人数</s:elseif>
						<s:elseif test="atype=='w'.toString()">产生外部人数</s:elseif>
					</th>
				</s:if>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.ulist" var="u">
				<tr align="center">
					<td id="n_${u.openid}">${u.nickname}</td>
					<td id="g_${u.openid}">${u.subscribe}</td>
					<td id="s_${u.openid}">${u.sex}</td>
					<td id="a_${u.openid}">${u.area}</td>
					<td>${u.count}</td>
					<s:if test="atype!='f'.toString()">
						<td>${u.ngroups}</td>
					</s:if>
					<td>
						<a href="javascript:void(0);" onclick="showDetail('${u.openid}')">详情</a>
					</td>
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
			$("#next").attr("href", "/${oname}/data/share_user.action?atype=${atype}&starttime=${starttime}&endtime=${endtime}&pageId=" + (pageId + 1));
			$("#next").show();
		}
		if (pageId > 1) {
			$("#prev").attr("href", "/${oname}/data/share_user.action?atype=${atype}&starttime=${starttime}&endtime=${endtime}&pageId=" + (pageId - 1));
			$("#prev").show();
		}

	});

	function submit() {
		if ($("#startTime").val() == "") {
			WdatePicker({
				el : 'startTime',
				maxDate : '#F{$dp.$D(\'endTime\')}',
				dateFmt : 'yyyy-MM-dd HH:mm:ss'
			});
			return;
		}
		if ($("#endTime").val() == "") {
			WdatePicker({
				el : 'endTime',
				minDate : '#F{$dp.$D(\'startTime\')}',
				dateFmt : 'yyyy-MM-dd HH:mm:ss'
			});
			return;
		}
		$("#dateForm").submit();
	}

	function showDetail(openid) {
		layer.open({
			type : 2,
			area : [ '800px', '550px' ],
			title : '详情',
			content : "/${oname}/data/share_user_detail.action?atype=${atype}&pname=" + openid
		});
	}

	
</script>