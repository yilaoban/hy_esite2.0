<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 微站投票 -->
<link href="/css/wxc.css" rel="stylesheet" type="text/css" />
<link href="/css/xc/mo/tp/index.css" rel="stylesheet" type="text/css" />
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
	timer2 = window.setInterval("begin()",8000);
}
function begin(){
	var pageid= $('#hy_pageid').val();
	$.post("/${oname}/user/voteOptionCount.action",{"voteid":'<s:property value="#session.hy_page_dto['123'].interactVote.id"/>'},function(data){
				if(data != -1){
					$("#votePost").empty();
					var html = "<ul class='voteData clearfix'>";
					var maxcount = 0;
					for(var i=0; i<data.length; i++){
						if(data[i].count > maxcount){
							maxcount = data[i].count;
						}
					 }
					 for(var i=0; i<data.length; i++){
					 	var count= i%5+1;
					 	var imgDomain='${imgDomain}';
							html = html +  "<li style='width:"+1/data.length*100+"%'><div class='toupiao_150327_fenzi style"+count+"' style='height: "+(data[i].count/maxcount*100)+"%;'><span class='span2'>"+data[i].count + "</span></div><div class='toupiao_150327_prize'><span class='span1 fstyle"+count+"'>"+data[i].content+"</span><img src='"+imgDomain+data[i].pic+"'></div></li>";
					 }
					 html = html + "</ul>";
					 $("#votePost").html(html);
				}
	});
}
begin();
if('${method}' != "E"){
	ping1();
	ping();
}
</script>
<s:if test='method=="E"'>
	<div style="position:absolute;top:0;left:0;right:0;bottom:0;background:${page.bgJson.background};background-size:100% 100%;"></div>
</s:if>

<s:if test='blocks[0].display=="Y"'>
<div style="position:absolute;left:100px;top:10px;width:149px;height:70px;${blocks[0].hyct}" hyblk="S" class="block ${blocks[0].ctname}" status="0" hyct="Y" hydata="${blocks[0].rid}">
	<img src="${blocks[0].img}" style="width:100%;height:100%;" hyvar="img" hydesc="217*72"/>
</div>
</s:if>
<s:if test='blocks[1].display=="Y"'>		
<div id="voteDiv" class="block ${blocks[1].ctname}" status="0" hyct="Y" hydata="${blocks[1].rid}" style="position:absolute;margin-left:-450px;${blocks[1].hyct}">
	<div id="voteCon">
		<div id="votePost">
		</div>
	</div>
</div>
</s:if>
<%@include file="/WEB-INF/card/cardfile.jsp"%>

