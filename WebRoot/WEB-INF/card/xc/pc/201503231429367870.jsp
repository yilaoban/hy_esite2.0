<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 微现场大转盘 -->
<link href="/res/lenovo/daping/css/index.css" rel="stylesheet" type="text/css" />
<link href="/css/xc/pc/list.css" rel="stylesheet" type="text/css" />
<link href="/css/wxc.css" rel="stylesheet" type="text/css" />
<script src="/js/firework/prefixfree.min.js"></script>
<s:if test='method=="E"'>
	<div style="position:absolute;top:0;left:0;right:0;bottom:0;background:${page.bgJson.background};background-size:100% 100%;"></div>
</s:if>
<s:if test='blocks[0].display=="Y"'>
<div hyblk="S" class="block ${blocks[0].ctname}" status="0" hyct="Y" hydata="${blocks[0].rid}" style="position:absolute;left:100px;top:10px;width:149px;height:70px;${blocks[0].hyct};">
	<img src="${blocks[0].img}" style="width:100%;height:100%;" hyvar="img" hydesc="217*72"/>
</div>
</s:if>
<s:if test='blocks[6].display=="Y"'>
	<div class="block ${blocks[6].ctname}" status="0" hyct="Y" hydata="${blocks[6].rid}" style="background:rgba(${blocks[1].col},${blocks[1].tm});position:absolute;width:240px;height:50px;top:30px;right:100px;text-align:center;line-height:50px;font-size:22px;font-weight:bold;color:#fff;${blocks[1].hyct}">
		已有<span id="sgin_num"></span>人参与
	</div>
</s:if>
<div style="width:700px;margin:0 auto;">
    <div class="box_dazhuanpan1_141113_1">
		<div class="wdy_center_dazhuanpan1_141113_1 block ${blocks[1].ctname}" status="0" hyct="Y" hydata="${blocks[1].rid}" style="${blocks[1].hyct};">
			<div class="zhuanpan_dazhuanpan1_141113_1">
				<div class="dgl_1106_box">
					<div class="outercont">
						<div class="outer-cont">
							<div id="outer" class="outer"><img src="/res/lenovo/weizhan/images/dzp.png" width="310px"/></div>
						</div>
						<div class="inner-cont">
							<div id="inner" class="inner"><img src="/images/hudong/dazhuanpan1/dzp_simg.png"/></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<s:if test='blocks[2].display=="Y"'>
<div style="left:30px;${blocks[2].hyct};height:auto;background-color:rgba(${blocks[2].col},${blocks[2].tm});" hyblk="S" class="block dzpprice ${blocks[2].ctname}" status="0" hyct="Y" hydata="${blocks[2].rid}">
	<ul id="oneprice">
		<li>一等奖中奖人</li>
	</ul>
</div>
</s:if>

<s:if test='blocks[3].display=="Y"'>
<div style="left:195px;${blocks[3].hyct};height:auto;background-color:rgba(${blocks[3].col},${blocks[3].tm});" hyblk="S" class="block dzpprice ${blocks[3].ctname}" status="0" hyct="Y" hydata="${blocks[3].rid}">
	<ul id="twoprice">
		<li>二等奖中奖人</li>
	</ul>
</div>
</s:if>

<s:if test='blocks[4].display=="Y"'>
<div style="left:360px;${blocks[4].hyct};height:auto;background-color:rgba(${blocks[4].col},${blocks[4].tm});" hyblk="S" class="block dzpprice ${blocks[4].ctname}" status="0" hyct="Y" hydata="${blocks[4].rid}">
	<ul id="threeprice">
		<li>三等奖中奖人</li>
	</ul>
</div>
</s:if>

<s:if test='blocks[5].display=="Y"'>
<div style="left:525px;${blocks[5].hyct};height:auto;background-color:rgba(${blocks[5].col},${blocks[5].tm});" hyblk="S" class="block dzpprice ${blocks[5].ctname}" status="0" hyct="Y" hydata="${blocks[5].rid}">
	<ul id="fourprice">
		<li>四等奖中奖人</li>
	</ul>
</div>
</s:if>
<div id="canvas-container"></div>

  <script src="/js/firework/index.js"></script>
  
<style type="text/css">
	.dzpprice{float:left;width:135px;min-height:135px;top:450px;position:absolute;color:#fff;border-radius:10px;text-align:center;line-height:34px;font-family: "Microsoft Yahei", Arial;font-size: 20px;height:auto;}
</style>
<script type="text/javascript">
	var timer1;
	var zptimer;
	function ping1(){
		timer1 = window.setInterval("found()",2000);
	}
	function found(){
		$.post("/${oname}/user/xcSwitchAction.action",{"xcid":$("#hy_entity").val()},function(data){
			if(data > 0 && data != ${pageid}){
				window.location.href="/${oname}/user/show/"+data+"/pcn.html";
			}
		});
	}

	function ping(){
		zptimer = window.setInterval("begin()",2000);
	}
	
	var maxRecordid = 0;
	function begin(){
		$.post("/${oname}/user/lottery_user_screen.action",{"lid":'<s:property value="#session.hy_page_dto['136'].lottery.id"/>',"maxRecordid":maxRecordid},function(data){
			$.each(data.list,function(index,value){
				if(value.price == 1){
					$("#oneprice").append("<li>"+value.name+"</li>");
				}else if(value.price == 2){
					$("#twoprice").append("<li>"+value.name+"</li>");
				}else if(value.price == 3){
					$("#threeprice").append("<li>"+value.name+"</li>");
				}else if(value.price == 4){
					$("#fourprice").append("<li>"+value.name+"</li>");
				}
			});
			maxRecordid = data.maxRecordid;
			$("#sgin_num").html(data.count);
			$("#canvas-container").show();
			new Fireworks();
		});
	}
	$(function(){
		window.requestAnimFrame=(function(){
			return window.requestAnimationFrame||window.webkitRequestAnimationFrame||window.mozRequestAnimationFrame||window.oRequestAnimationFrame||window.msRequestAnimationFrame||function(callback){window.setTimeout(callback,1000/60)}})();
			var totalDeg=360*2+0;
			var steps=[];
			var lostDeg=[30,90,150,210,270,330];
			var prizeDeg=[0,120,240];
			var prize,hydesc;
			var now=0;
			var a=0.01;
			var outter,inner,timer,running=false;
			function countSteps(){
				var t=Math.sqrt(2*totalDeg/a);
				var v=a*t;
				for(var i=0;i<t;i++){
					steps.push((2*v*i-a*i*i)/2)
				}
				steps.push(totalDeg)
			}
			function step(){
				outter.style.webkitTransform='rotate('+steps[now++]+'deg)';
				outter.style.MozTransform='rotate('+steps[now++]+'deg)';
				if(now<steps.length){
					requestAnimFrame(step)
				}else{
					running=true;
				}
			}
				function start(deg){
					running=true;
					clearInterval(timer);
					totalDeg=360*5+deg;
					steps=[];
					now=0;
					countSteps();
					requestAnimFrame(step)
				}
					window.start=start;
					outter=document.getElementById('outer');
					inner=document.getElementById('inner');
					i=10;
					$("#inner").click(function(){
						if(running) return;
						timer=setInterval(function(){
							i+=0.3;
							outter.style.webkitTransform='rotate('+i+'deg)';
							outter.style.MozTransform='rotate('+i+'deg)'
							},1);
					})
		});
		
		$(document).ready(function(){
			$("#inner").click();
		})
		
		if('${method}' != "E"){
			ping1();
			ping();
		}
	</script>
	
	<%@include file="/WEB-INF/card/cardfile.jsp"%>