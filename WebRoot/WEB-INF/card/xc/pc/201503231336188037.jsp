<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 微现场砸金蛋 -->
<script src="/js/firework/prefixfree.min.js"></script>
<script type="text/javascript">
var timer1;
var timer2;
function run1(){
	timer1 = window.setInterval("findot()",2000);
}
function findot(){
	$.post("/${oname}/user/xcSwitchAction.action",{"xcid":$("#hy_entity").val()},function(data){
		if(data > 0 && data != ${pageid}){
			window.location.href="/${oname}/user/show/"+data+"/pcn.html";
		}
	});
}

function run2(){
	timer2 = window.setInterval("finds()",2000);
}

var maxRecordid = 0;
function finds(){
	$.post("/${oname}/user/lottery_user_screen.action",{"lid":'<s:property value="#session.hy_page_dto['125'].lottery.id"/>',"maxRecordid":maxRecordid},function(data){
			$.each(data.list,function(index,value){
				if(value.price == 1){
					$("#oneprice").append("<li>"+value.name+"</li>");
					$("#canvas-container").show();
					new Fireworks();
				}else if(value.price == 2){
					$("#twoprice").append("<li>"+value.name+"</li>");
				}else if(value.price == 3){
					$("#threeprice").append("<li>"+value.name+"</li>");
				}else if(value.price == 4){
					$("#fourprice").append("<li>"+value.name+"</li>");
				}
			});
			maxRecordid = data.maxRecordid;
		$("#sgin_num").html(data.winnum);
	});
}

if('${method}' != "E"){
	run1();
	run2();
}
</script>
<s:if test='method=="E"'>
	<div style="position:absolute;top:0;left:0;right:0;bottom:0;background:${page.bgJson.background};background-size:100% 100%;"></div>
</s:if>
<s:if test='blocks[0].display=="Y"'>
<div style="position:absolute;left:100px;top:10px;width:149px;height:70px;${blocks[0].hyct};" hyblk="S" class="block ${blocks[0].ctname}" status="0" hyct="Y" hydata="${blocks[0].rid}">
	<img src="${blocks[0].img}" style="width:100%;height:100%;" hyvar="img" hydesc="217*72"/>
</div>
</s:if>
<s:if test='blocks[1].display=="Y"'>
<div class="block ${blocks[1].ctname}" status="0" hyct="Y" hydata="${blocks[1].rid}" style="background:rgba(${blocks[1].col},${blocks[1].tm});position:absolute;width:240px;height:50px;top:30px;right:100px;text-align:center;line-height:50px;font-size:22px;font-weight:bold;color:#fff;${blocks[1].hyct}">
	已有<span id="sgin_num"></span>人参与
</div>
</s:if>
<s:if test='blocks[1].display=="Y"'>
<div style="left:30px;${blocks[1].hyct};height:auto;background-color:rgba(${blocks[1].col},${blocks[1].tm});" hyblk="S" class="block fdiv ${blocks[1].ctname}" status="0" hyct="Y" hydata="${blocks[1].rid}">
	<ul id="oneprice">
		<li>一等奖中奖人</li>
	</ul>
</div>
</s:if>

<s:if test='blocks[2].display=="Y"'>
<div style="left:195px;${blocks[2].hyct};height:auto;background-color:rgba(${blocks[2].col},${blocks[2].tm});" hyblk="S" class="block fdiv ${blocks[2].ctname}" status="0" hyct="Y" hydata="${blocks[2].rid}">
	<ul id="twoprice">
		<li>二等奖中奖人</li>
	</ul>
</div>
</s:if>

<s:if test='blocks[3].display=="Y"'>
<div style="left:360px;${blocks[3].hyct};height:auto;background-color:rgba(${blocks[3].col},${blocks[3].tm});" hyblk="S" class="block fdiv ${blocks[3].ctname}" status="0" hyct="Y" hydata="${blocks[3].rid}">
	<ul id="threeprice">
		<li>三等奖中奖人</li>
	</ul>
</div>
</s:if>

<s:if test='blocks[4].display=="Y"'>
<div style="left:525px;${blocks[4].hyct};height:auto;background-color:rgba(${blocks[4].col},${blocks[4].tm});" hyblk="S" class="block fdiv ${blocks[4].ctname}" status="0" hyct="Y" hydata="${blocks[4].rid}">
	<ul id="fourprice">
		<li>四等奖中奖人</li>
	</ul>
</div>
</s:if>
<div id="canvas-container"></div>

  <script src="/js/firework/index.js"></script>


<style type="text/css">
	.fdiv{float:left;width:135px;min-height:135px;background-color:#00a0e9;top:200px;position:absolute;color:#fff;border-radius:10px;text-align:center;line-height:34px;font-family: "Microsoft Yahei", Arial;font-size: 20px;}
</style>
<%@include file="/WEB-INF/card/cardfile.jsp"%>