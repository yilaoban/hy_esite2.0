<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<script src="/js/cb/index.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var id = '${sessionScope.visitUser.skey}';
		var aid = '${sessionScope.visitUser.svalue}';
		$.post("/${oname}/user/activitymatterDetail.action", {
			"id" : id,
			"aid" : aid
		}, function(data) {
			if (data.status == -1 || data.status == -2) {
				window.location.href = data.rs;
			} else if (data.status == 1) {
				$("#cbcontent").html("");
				var html = "<li><img src='"+data.wxPageShow.pic +"'/>";
				html += "<div class='hd_l_title'><span>" + data.wxPageShow.title + "</span>";
				html += "<p>" + data.wxPageShow.description + "</p></div> ";
				html += "<div class='fenxiang'><a href=' ${pageDomain }/${oname}/user/wxshow/"+data.wxPageShow.pageid+"/t-${sessionScope.visitUser.wxUser.id}/"+data.wxPageShow.kv+".html'>查看详情</a></div></li>"
				$("#cbcontent").append(html);

				$("#one1").html(data.clicknum);
				$("#one2").html(data.zhuanfanum);
				$("#one3").html(data.guanzhunum);
				$("#one4").html(data.hudongnum);
				$("#seven1").html(data.sclicknum);
				$("#seven2").html(data.szhuanfanum);
				$("#seven3").html(data.sguanzhunum);
				$("#seven4").html(data.shudongnum);
				$("#all1").html(data.tclicknum);
				$("#all2").html(data.tzhuanfanum);
				$("#all3").html(data.tguanzhunum);
				$("#all4").html(data.thudongnum);
			}
		});
	});
</script>

<!-- 推广统计 -->
<link href="/css/cb/index.css" rel="stylesheet" type="text/css" />

<%@include file="/WEB-INF/card/background.jsp"%>

<!--header开始
<div class="cb_header"><img src="/images/cb/tx.jpg"/> 用户名称</div>
-->
<div class="cb_header">
	<img src="${sessionScope.visitUser.wxUser.headimgurl}" width="60px">${sessionScope.visitUser.wxUser.nickname}</div>
<!--header结束-->

<div class="hd_list">
	<div id="cbcontent">
	</div>
</div>

<div style="clear: both;"></div>

<div class="box_qr" style="margin: 0;">
	<table width="90%" border="1" cellspacing="0" cellpadding="0" class="box_table">
		<tr style="background: #c6dbf1;">
			<td width="30%">统计概况</td>
			<td width="23%">昨天</td>
			<td width="23%">7天</td>
			<td width="23%">总数</td>
		</tr>
		<tr>
			<td class="table_left">点击量</td>
			<td id="one1">0</td>
			<td id="seven1">0</>
			<td id="all1">0</td>
		</tr>
		<tr>
			<td class="table_left">转发量</td>
			<td id="one2">0</td>
			<td id="seven2">0</>
			<td id="all2">0</td>
		</tr>
		<tr>
			<td class="table_left">关注量</td>
			<td id="one3">0</td>
			<td id="seven3">0</>
			<td id="all3">0</td>
		</tr>
		<tr>
			<td class="table_left">互动量</td>
			<td id="one4">0</td>
			<td id="seven4">0</>
			<td id="all4">0</td>
		</tr>
	</table>
</div>

<!--bottom开始-->
<div style="width:100%; float:left; height:65px;"></div>
<s:if test='blocks[0].display=="Y"'>
<div class="cb_bottom block ${blocks[0].ctname}" hyblk="S" hyct="Y" status="0" style="position:<s:if test='method=="E"'>absolute;${blocks[0].hyct}</s:if><s:else>fixed;bottom:0;</s:else>width:33.3%;left:0;" hydata="${blocks[0].rid}" hydata="${blocks[0].rid}"><a href="${blocks[0].link}"><img src="${blocks[0].img}"/><br/>${blocks[0].title}</a></div>
</s:if>
<s:if test='blocks[1].display=="Y"'>
<div class="cb_bottom block ${blocks[1].ctname}" hyblk="S" hyct="Y" status="0" style="position:<s:if test='method=="E"'>absolute;${blocks[1].hyct}</s:if><s:else>fixed;bottom:0;</s:else>width:33.3%;left:33.3%;" hydata="${blocks[1].rid}" hydata="${blocks[1].rid}"><a href="${blocks[1].link}"><img src="${blocks[1].img}"/><br/>${blocks[1].title}</a></div>
</s:if>
<s:if test='blocks[2].display=="Y"'>
<div class="cb_bottom block ${blocks[2].ctname}" hyblk="S" hyct="Y" status="0" style="position:<s:if test='method=="E"'>absolute;${blocks[2].hyct}</s:if><s:else>fixed;bottom:0;</s:else>width:33.3%;left:66.6%;" hydata="${blocks[1].rid}" hydata="${blocks[2].rid}"><a href="${blocks[2].link}"><img src="${blocks[2].img}"/><br/>${blocks[2].title}</a></div>
</s:if>
<!--bottom结束-->

<%@include file="/WEB-INF/card/cardfile.jsp"%>
