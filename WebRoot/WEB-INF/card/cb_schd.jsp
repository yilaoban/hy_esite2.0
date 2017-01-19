<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 市场活动 -->
<link href="/css/cb/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	$(document).ready(function(){
		$.post("/${oname}/user/findActivityMatterList.action",
		   function(data){
		   		if(data.status == -1 || data.status == -2){
		   			window.location.href = data.rs;
		   		}else if(data.status == 1){
					$(".demo").remove();
					$.each( data.cbActivityList, function(i, n){
						$("#wrapper").append("<div class='proList demo'><h1>"+n.title+"</h1> <div class='hdsm'><a href='javascript:;' class='bounceIn dialog"+(i+1)+"'>说明</a></div> <div class='plTitle'><a href='#'>开始时间："+n.starttimeStr+"</a> <a href='#'>结束时间："+n.endtimeStr+"</a><em onClick='showZk("+n.id+")' id='wenzi_"+n.id+"'>展开</em></div><div class='plCon'><div class='hd_list_x'><ul id='ck_"+n.id+"' style='display: none;'></ul> </div></div> <div id='HBox"+(i+1)+"'> <p class='jianjie'>活动简介：<br/>"+n.content+"</p> <dl class='jianjie_fen'> 活动规则：<br/><dd>转发：<span>"+n.zhuanfa+"</span>分/人</dd><dd>点击:<span>"+n.click+"</span>分/人</dd><dd>关注：<span>"+n.guanzhu+"</span>分/人</dd><dd>关注时长：<span>"+n.guanzhudays+"</span>天</dd><dd>互动：<span>"+n.hudong+"</span>分/人</dd><dd>外部：<span>"+n.waibu+"</span>分/人</dd></dl> </div> </div>");
						$('.dialog' + (i+1)).hDialog({box: '#HBox' + (i + 1) });
					});
		   		}
		   });
	});
	
	function showZk(id){
		$("#ck_" + id).toggle();
		if($("#ck_" + id).is(':visible')){
			$("#wenzi_" + id).text("收起");
		}
		if($("#ck_" + id).is(':hidden')){
			$("#wenzi_" + id).text("展开");
		}
		$.post("/${oname}/user/findActivityMatterChildList.action",{"aid":id,"pageid":$("#hy_pageid").val()},
		   function(data){
		   		if(data.status == -1 || data.status == -2){
		   			window.location.href = data.rs;
		   		}else if(data.status == 1){
		   			$("#ck_" + id).html("");
		   			var tgtjpage = data.pageid;
		   			var aid = data.aid;
					$.each( data.wxPageShowList, function(i, n){
						$("#ck_" + id).append(" <li><img src='"+n.pic+"'/> <div class='hd_l_title'> <span>"+n.title+"</span><p>"+n.description+"</p></div><div class='hd_l_r'> <div class='chakan'><a href='/${oname}/user/show/"+tgtjpage+"/wxn/"+n.id+"-hy-"+aid+".html' target='_blank'>统计报告</a></div> <div class='fenxiang_list'><a href='${pageDomain }/${oname}/user/wxshow/"+n.pageid+"/t-${sessionScope.visitUser.wxUser.id}/"+n.kv+".html'>查看详情</a></div></div></li>");
					});
		   		}
		   });
		
	}
	
</script>


<%@include file="/WEB-INF/card/background.jsp"%>

<!--header开始-->
<div class="cb_header"><img src="${sessionScope.visitUser.wxUser.headimgurl}" width="60px">${sessionScope.visitUser.wxUser.nickname}</div>
<!--header结束-->

<div class="hd_box box" id="wrapper" >
	<script type="text/javascript">
	    var _system={
	        $:function(id){return document.getElementById(id);},
		   _client:function(){
			      return {w:document.documentElement.scrollWidth,h:document.documentElement.scrollHeight,bw:document.documentElement.clientWidth,bh:document.documentElement.clientHeight};
			   },
			   _scroll:function(){
			      return {x:document.documentElement.scrollLeft?document.documentElement.scrollLeft:document.body.scrollLeft,y:document.documentElement.scrollTop?document.documentElement.scrollTop:document.body.scrollTop};
			   },
			   _cover:function(show){
			      if(show){
			     this.$("cover").style.display="block";
			     this.$("cover").style.width=(this._client().bw>this._client().w?this._client().bw:this._client().w)+"px";
			     this.$("cover").style.height=(this._client().bh>this._client().h?this._client().bh:this._client().h)+"px";
			  }else{
			     this.$("cover").style.display="none";
			  }
		   },
		   _guide:function(click){
		      this._cover(true);
		      this.$("guide").style.display="block";
		      this.$("guide").style.top=(_system._scroll().y+5)+"px";
		      window.onresize=function(){_system._cover(true);_system.$("guide").style.top=(_system._scroll().y+5)+"px";};
			  if(click){_system.$("cover").onclick=function(){
			         _system._cover();
			         _system.$("guide").style.display="none";
			 _system.$("cover").onclick=null;
			 window.onresize=null;
			  };}
		   },
		   _zero:function(n){
		      return n<0?0:n;
		   }
		}
	</script>
	<div id="cover"></div>
	<div id="guide"><img src="/images/cb/guide1.png"></div>
</div>
<div style="clear:both;"></div>

<!--bottom开始-->
<div style="width:100%; float:left; height:65px;"></div>
<s:if test='blocks[0].display=="Y"'>
<div class="cb_bottom block ${blocks[0].ctname}" hyblk="S" hyct="Y" status="0" style="position:<s:if test='method=="E"'>absolute;${blocks[0].hyct}</s:if><s:else>fixed;bottom:0;</s:else>width:33.3%;left:0;" hydata="${blocks[0].rid}"><a href="${blocks[0].link}"><img src="${blocks[0].img}"/><br/>${blocks[0].title}</a></div>
</s:if>
<s:if test='blocks[1].display=="Y"'>
<div class="cb_bottom block ${blocks[1].ctname}" hyblk="S" hyct="Y" status="0" style="position:<s:if test='method=="E"'>absolute;${blocks[1].hyct}</s:if><s:else>fixed;bottom:0;</s:else>width:33.3%;left:33.3%;" hydata="${blocks[1].rid}" hydata="${blocks[1].rid}"><a href="${blocks[1].link}" style="color:#0099ff;"><img src="${blocks[1].img}"/><br/>${blocks[1].title}</a></div>
</s:if>
<s:if test='blocks[2].display=="Y"'>
<div class="cb_bottom block ${blocks[2].ctname}" hyblk="S" hyct="Y" status="0" style="position:<s:if test='method=="E"'>absolute;${blocks[2].hyct}</s:if><s:else>fixed;bottom:0;</s:else>width:33.3%;left:66.6%;" hydata="${blocks[2].rid}" hydata="${blocks[2].rid}"><a href="${blocks[2].link}"><img src="${blocks[2].img}"/><br/>${blocks[2].title}</a></div>
</s:if>
<!--bottom结束-->
<%@include file="/WEB-INF/card/cardfile.jsp"%>
