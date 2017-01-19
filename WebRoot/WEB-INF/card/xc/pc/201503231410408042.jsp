<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 微现场摇一摇-->
<link href="/css/wxc.css" rel="stylesheet" type="text/css" />
<script src="/js/firework/prefixfree.min.js"></script>
<script type="text/javascript">
	var timer1;
	var timer;
	var djs;
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
		timer = window.setInterval("start()",2000);
	}
	
	function start() {
			$.ajax({
				url : "/${oname}/user/xcStartAction.action",
				type : "post",
				dataType : "json",
				data : {
					"xcid" : $("#hy_entity").val(),
					"random" : Math.random()
				},
				cache : false,
				async : false,
				success : function(data) {
					if (!isNaN(data)) {
						// -1-没有此活动，0-活动未开始，1-活动开始，2-活动已结束
						var num = parseInt(data);
						if (num != 1) {
						}  else if (num == 1) {
							daoshu();
							window.clearInterval(timer);
						} 
					} else {
						alert(data);
					}
				}
			});
		};

		function end() {
			$.ajax({
				url : "/${oname}/user/xcResultAction.action",
				type : "get",
				dataType : "json",
				data : {
					"xcid" : $("#hy_entity").val()
				},
				success : function(data) {
					var size = data.length;
					var html = "";
					for(var i=0;i<size;i++){
						html = html + "<div class='singlePrize fadeIn2'><img src="+data[i].headimgurl+"/><span>"+data[i].nickname+"<br>"+data[i].privilege+"</span></div>";
						
					}
					$("#signPost").html(html);
				}
			});
		};
	
	function daoshu(){
		$("#ddstart").hide();
		$("#ddbg").show();
		setTimeout('ks2ds()',2000);
	}
	
	function ks2ds(){
		$("#dd").text(10);
		djs = window.setInterval("run();", 1000);
		$("#dd").addClass("scan");
	}
	
	function run(){
		if($("#dd").text() == 1){
			window.clearInterval(djs); 
			end();
			$("#ddbg").hide();
			setTimeout("kaijiang()",2000);
		}
		$("#dd").text($("#dd").text() * 1 - 1);
	}
	
	function kaijiang(){
		$("#signPost").show();
		$("#dd").text(10);
		ping();
		$("#canvas-container").show();
		new Fireworks();
	}
	
	if('${method}' != "E"){
		ping1();
		ping();
	}
</script>
<s:if test='method=="E"'>
	<div style="position:absolute;top:0;left:0;right:0;bottom:0;background:${page.bgJson.background};background-size:100% 100%;"></div>
</s:if>	<s:if test='blocks[0].display=="Y"'>
		<div style="position:absolute;left:100px;top:10px;width:149px;height:70px;${blocks[0].hyct};" hyblk="S" class="block ${blocks[0].ctname}" status="0" hyct="Y" hydata="${blocks[0].rid}">
			<img src="${blocks[0].img}" style="width:100%;height:100%;" hyvar="img" hydesc="217*72"/>
		</div>
	</s:if>
	<s:if test='blocks[1].display=="Y"'>
	<div id="ddstart" style="${blocks[1].hyct};" hyblk="S" class="block ${blocks[1].ctname}" status="0" hyct="Y" hydata="${blocks[1].rid}"><img src="${blocks[1].img}" /></div>
	</s:if>
	<div id="ddbg" style="display:none;">
    	<div id="dd">开始</div>
    </div>
    <div id="signDiv">
		<div id="signCon">
			<div id="signPost" class="clearfix">
		</div>
	</div>
</div>
<div id="canvas-container"></div>

  <script src="/js/firework/index.js"></script>
<%@include file="/WEB-INF/card/cardfile.jsp"%>