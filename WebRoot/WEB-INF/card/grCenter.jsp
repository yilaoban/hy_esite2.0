<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<link href="/css/hudong/grCenter/index.css" rel="stylesheet" type="text/css" />
<script src="/js/hudong/grCenter/index.js"></script>
<!-- 个人中心 -->
<%@include file="/WEB-INF/card/background.jsp"%>
<script type="text/javascript">
	$(document).ready(function(){
		if("${method}" == "E"){
			return;
		}
		$.post("/${oname}/user/userJfDetail.action",
		   function(data){
		   		$('#img').attr("src",data.img);
		   		$('#nickname').html(data.nickname);
		   		$('#canuse').html(data.jf);
				$.each( data.ulist, function(i, n){
					if(i <  8){
						$("#table1").append("<tr><td><span class='table_jia'>"+n.score+"</span></td><td>"+n.createStr+"</td><td>"+n.desc+"</td></tr>");
					}
				});
		   });
		 
		  $.post("/${oname}/user/userJfAddDetail.action",
		   function(data){
				$.each( data.ulist, function(i, n){
					$("#table2").append("<tr><td><span class='table_jia'>"+n.score+"</span></td><td>"+n.createStr+"</td><td>"+n.desc+"</td></tr>");
				});
		   });
		 
		$.post("/${oname}/user/userJfSubDetail.action",
		   function(data){
				$.each( data.ulist, function(i, n){
					$("#table3").append("<tr><td><span class='table_jia'>"+n.score+"</span></td><td>"+n.createStr+"</td><td>"+n.desc+"</td></tr>");
				});
		   });
		   
	});
	
	function showCheckinData(){
		if("${method}" == "E"){
			return;
		}
		$.post("/${oname}/user/checkin_draw.action",
		   function(data){
		   		$(".tk_zi").remove();
				if(data.status == 1){
					var jf = $('#canuse').html();
					jf = jf - 0 + data.jf;
					$('#canuse').html(jf);
					$('#HBox').append("<div class='tk_zi'>签到成功!<br/>当前积分：<span>"+jf+"</span><br/>今日签到获得：<span>"+data.jf+"</span><br/>已连续签到 <span>"+data.daynum+"</span> 天</div>");
				}else{
					$('#HBox').append("<div class='tk_zi'>"+data.hydesc+"</div>");
				}
		   });
	
	}
	
</script>

<div class="top">
	<div class="top_tx"><img src="" id="img"/></div>
	<div class="top_title">
		<span id="nickname"></span>&nbsp&nbsp
		可用积分：<span id="canuse"></span>
	</div>
</div>

<s:if test='blocks[0].display=="Y"'>
<div class="demo block ${blocks[0].ctname}" status="0" style="background:#fff none repeat scroll 0 0;padding:12px 5%;${blocks[0].hyct}" hydata="${blocks[0].rid}">
	<a href="javascript:;" onclick="showCheckinData()" class="bounceIn dialog">
		<img src="${blocks[0].img}" width="100%" height="100%" />
	</a>
</div>
</s:if>
<s:if test='blocks[1].display=="Y"'>
<div class="block ${blocks[1].ctname}" status="0" style="background:#fff none repeat scroll 0 0;padding:12px 5%;${blocks[1].hyct}" hydata="${blocks[1].rid}">
	<a href="${blocks[1].link}" target="_blank">
		<img src="${blocks[1].img}" width="100%" height="100%" />
	</a>
</div>
</s:if>
<div id="HBox"></div>
		
<script>
$(function(){
	var $el = $('.dialog');
	$el.hDialog();
	
	//带标题的
	$('.demo0').hDialog({title: '测试弹框标题',height: 300});
	 
	$('.demo').hDialog({afterHide: function(){
		window.location.reload();
    }});
});
</script>

<s:if test='blocks[2].display=="Y"'>
<div class="block ${blocks[2].ctname}" status="0" style="${blocks[2].hyct};" hyblk="S" hydata="${blocks[2].rid}">
<figure class="tabBlock">
  <ul class="tabBlock-tabs">
    <li class="tabBlock-tab is-active"><img src="/images/hudong/grCenter/menu1.png"/>积分明细</li>
    <li class="tabBlock-tab"><img src="/images/hudong/grCenter/menu2.png"/>积分收入</li>
    <li class="tabBlock-tab"><img src="/images/hudong/grCenter/menu3.png"/>积分支出</li>
  </ul>
  <div class="tabBlock-content">
    <div class="tabBlock-pane">
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_bg" id="table1">
  <tr style="color:#000;">
    <td style="border-right:1px #ebebeb solid;">积分变化</td>
    <td style="border-right:1px #ebebeb solid;">日期</td>
    <td>备注</td>
  </tr>
</table>

    </div>
    <div class="tabBlock-pane">
      
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_bg" id="table2">
  <tr style="color:#000;">
    <td style="border-right:1px #ebebeb solid;">积分变化</td>
    <td style="border-right:1px #ebebeb solid;">日期</td>
    <td>备注</td>
  </tr>
</table>
      
    </div>
    
    <div class="tabBlock-pane">
      
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_bg" id="table3">
  <tr style="color:#000;">
    <td style="border-right:1px #ebebeb solid;">积分变化</td>
    <td style="border-right:1px #ebebeb solid;">日期</td>
    <td>备注</td>
  </tr>
</table>
    </div>
    
  </div>
</figure>
</div>
</s:if>





<%@include file="/WEB-INF/card/cardfile.jsp"%>
