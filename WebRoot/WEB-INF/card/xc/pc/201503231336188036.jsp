<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 微现场签到 -->
<link href="/css/wxc.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
var timer1;
var timer2;
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
	timer2 = window.setInterval("begin()",3000);
}

var start = 1;
var size = 1;
function begin(){
	var xcid=$("#hy_entity").val();
	$.post("/${oname}/user/signinlist.action",{"xcid":xcid,"start":start,"size":size},function(data){
			$.each(data.list,function(index,value){
				if(value.id > start){
					start = value.id;
				}
				$("#signPost").prepend("<div class='singleSign fadeIn'><img src="+value.headimgurl+"/><span>"+value.nickname+"</span></div>");
				setTimeout("$('.singleSign').css('position','static');$('.singleSign img').css({'width':'100px','height':'100px'});",2000);
			});
		$("#sgin_num").html(data.count);
	});
}

if('${method}' != "E"){
	ping1();
	ping();
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
	<div class="block" status="0" hyct="Y" hydata="${blocks[1].rid}" style="background:rgba(${blocks[1].col},${blocks[1].tm});position:absolute;width:240px;height:50px;top:30px;right:100px;text-align:center;line-height:50px;font-size:22px;font-weight:bold;color:#fff;background-size:100% 100%;${blocks[1].hyct}">
		${blocks[1].title}<span id="sgin_num"></span>
	</div>
	</s:if>
	<div id="signDiv" class="block" status="0" hyct="Y" hydata="${blocks[2].rid}" style="${blocks[2].hyct}">
		<div id="signCon">
			<div id="signPost">
				<s:if test='method == "E"'>
					<img src="/res/lenovo/daping/images/qiandao.png" width="700px" hyvar="img" hydesc="320*568"/>
				</s:if>
			</div>
		</div>
	</div>

<%@include file="/WEB-INF/card/cardfile.jsp"%>