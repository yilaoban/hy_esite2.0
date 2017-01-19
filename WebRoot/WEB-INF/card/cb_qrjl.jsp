<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<script src="/js/jquery.swipe.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#leftcontent").html("");$("#click").html("");$("#zhuanfa").html("");$("#guanzhu").html("");$("#hudong").html("");
		$("#leftcontent").append("<tr><td>时间</td></tr>");
		
		$.post("/${oname}/user/findCbActivityJlRecord.action",
		   function(data){
		   		if(data.status == -1 || data.status == -2){
		   			window.location.href = data.rs;
		   		}else if(data.status == 1){
					$("#pageId").val(data.pageId);
					$.each( data.jlRecordList, function(i, n){
						$("#leftcontent").append("<tr><td class='table_left'>"+n.datadayStr+"</td></tr>");
						$("#click").append("<p>赚"+n.clicktotal+"分,点击"+n.clicknumtotal+"次</p>");
						$("#zhuanfa").append("<p>赚"+n.zhuanfatotal+"分,点击"+n.zhuanfanumtotal+"次</p>");
						$("#guanzhu").append("<p>赚"+n.guanzhutotal+"分,点击"+n.guanzhunumtotal+"次</p>");
						$("#hudong").append("<p>赚"+n.hudongtotal+"分,点击"+n.hudongnumtotal+"次</p>");
					});
		   		}
		   });
	});
</script>

<!-- 确认记录 -->
<link href="/css/cb/index.css" rel="stylesheet" type="text/css" />

<%@include file="/WEB-INF/card/background.jsp"%>

<div class="cb_header"><img src="${sessionScope.visitUser.wxUser.headimgurl}" width="60px">${sessionScope.visitUser.wxUser.nickname}</div>

<div class="box_qr" id="wrapper">

<input type="hidden" id="pageId" value="">
<table width="90%" border="1" cellspacing="0" cellpadding="0" class="box_table" >
	<tr style="background:#c6dbf1;">
		<td width="30%" style="color:#666;">
			<table width="100%" border="1" cellspacing="0" cellpadding="0" style="margin-top:0; float:left;" id="leftcontent">
			</table>
		</td>
    	<td width="70%">
     		<figure class="tabBlock">
				<ul class="tabBlock-tabs">
				    <li class="tabBlock-tab is-active">点击</li>
				    <li class="tabBlock-tab">转发</li>
				    <li class="tabBlock-tab">关注</li>
				    <li class="tabBlock-tab">互动</li>
				</ul>
				<div class="tabBlock-content">
					<div class="tabBlock-pane" id="click">
					</div>
				    <div class="tabBlock-pane" id="zhuanfa">
				    </div>
				    <div class="tabBlock-pane" id="guanzhu">
				    </div>
				    <div class="tabBlock-pane" id="hudong">
				    </div>
				 </div>
			</figure>
			<script src="/js/cb/index.js"></script>
    	</td>
  	</tr>
</table>

</div>


<!--bottom开始-->
<div style="width:100%; float:left; height:65px;"></div>
<s:if test='blocks[0].display=="Y"'>
<div class="cb_bottom block ${blocks[0].ctname}" hyblk="S" hyct="Y" status="0" style="position:<s:if test='method=="E"'>absolute;${blocks[0].hyct}</s:if><s:else>fixed;bottom:0;</s:else>width:33.3%;left:0;" hydata="${blocks[0].rid}" hydata="${blocks[0].rid}"><a href="${blocks[0].link}" style="color:#0099ff;"><img src="${blocks[0].img}"/><br/>${blocks[0].title}</a></div>
</s:if>
<s:if test='blocks[1].display=="Y"'>
<div class="cb_bottom block ${blocks[1].ctname}" hyblk="S" hyct="Y" status="0" style="position:<s:if test='method=="E"'>absolute;${blocks[1].hyct}</s:if><s:else>fixed;bottom:0;</s:else>width:33.3%;left:33.3%;" hydata="${blocks[1].rid}" hydata="${blocks[1].rid}"><a href="${blocks[1].link}"><img src="${blocks[1].img}"/><br/>${blocks[1].title}</a></div>
</s:if>
<s:if test='blocks[2].display=="Y"'>
<div class="cb_bottom block ${blocks[2].ctname}" hyblk="S" hyct="Y" status="0" style="position:<s:if test='method=="E"'>absolute;${blocks[2].hyct}</s:if><s:else>fixed;bottom:0;</s:else>width:33.3%;left:66.6%;" hydata="${blocks[2].rid}" hydata="${blocks[2].rid}"><a href="${blocks[2].link}"><img src="${blocks[2].img}"/><br/>${blocks[2].title}</a></div>
</s:if>
<!--bottom结束-->

<%@include file="/WEB-INF/card/cardfile.jsp"%>
<script type="text/javascript">
			$('#wrapper').swipe({
				up: function(){
					$("#leftcontent").html("");$("#click").html("");$("#zhuanfa").html("");$("#guanzhu").html("");$("#hudong").html("");
					$("#leftcontent").append("<tr><td>时间</td></tr>");
					var pageid = $('#pageId').val();
					pageid = pageid + 1;
					$.post("/${oname}/user/findCbActivityJlRecord.action",{"pageid":pageid},
					   function(data){
					   		if(data.status == -1 || data.status == -2){
					   			window.location.href = data.rs;
					   		}else if(data.status == 1){
								$("#pageId").val(data.pageId);
								$.each( data.jlRecordList, function(i, n){
									$("#leftcontent").append("<tr><td class='table_left'>"+n.datadayStr+"</td></tr>");
									$("#click").append("<p>赚"+n.clicktotal+"分,点击"+n.clicknumtotal+"次</p>");
									$("#zhuanfa").append("<p>赚"+n.zhuanfatotal+"分,点击"+n.zhuanfanumtotal+"次</p>");
									$("#guanzhu").append("<p>赚"+n.guanzhutotal+"分,点击"+n.guanzhunumtotal+"次</p>");
									$("#hudong").append("<p>赚"+n.hudongtotal+"分,点击"+n.hudongnumtotal+"次</p>");
								});
					   		}
		   });
				},
				down: function(){
					$("#leftcontent").html("");$("#click").html("");$("#zhuanfa").html("");$("#guanzhu").html("");$("#hudong").html("");
					$("#leftcontent").append("<tr><td>时间</td></tr>");
					var pageid = $('#pageId').val();
					pageid = pageid - 1;
					if(pageid == 0){
						pageid = 1;
					}
					$.post("/${oname}/user/findCbActivityJlRecord.action",{"pageid":pageid},
					   function(data){
					   		if(data.status == -1 || data.status == -2){
					   			window.location.href = data.rs;
					   		}else if(data.status == 1){
								$("#pageId").val(data.pageId);
								$.each( data.jlRecordList, function(i, n){
									$("#leftcontent").append("<tr><td class='table_left'>"+n.datadayStr+"</td></tr>");
									$("#click").append("<p>赚"+n.clicktotal+"分,点击"+n.clicknumtotal+"次</p>");
									$("#zhuanfa").append("<p>赚"+n.zhuanfatotal+"分,点击"+n.zhuanfanumtotal+"次</p>");
									$("#guanzhu").append("<p>赚"+n.guanzhutotal+"分,点击"+n.guanzhunumtotal+"次</p>");
									$("#hudong").append("<p>赚"+n.hudongtotal+"分,点击"+n.hudongnumtotal+"次</p>");
								});
					   		}
		   });
				}
			});
		</script>