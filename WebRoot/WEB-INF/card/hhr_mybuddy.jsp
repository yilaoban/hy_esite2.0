<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 我的伙伴 -->
<link href="/css/cb/partner_1.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$(document).ready(function(){
	$(".mingxi").remove();
	$.post("/${oname}/user/mySender.action",function(data){
		if(data.cbSenderList){
			$.each( data.cbSenderList, function(i, n){
				var html = '<div class="mingxi">';
				html += '<ul class="mx_2">'; 
				html += '<li><span class="left">'+n.wxUser.nickname+'</span><span>'+n.jf+'积分</span><span class="blue">'+(n.hbtotal - n.hbused)/100+'元</span></li>';
				html += '</ul>';
				html += '</div>';
				$('.main0526_4').append(html);
			});
			$(".tixian0527_2").show();
			$(".main0526_4").show();
		}else{
			$('.main0526_3').show();
		}
		if(data.cb){
			$('.tixian0527').find("span").attr("onclick","yaoqing("+data.cb.spageid+")");
			$('.tixian0527_2').find("span").attr("onclick","yaoqing("+data.cb.spageid+")");
		}
		
	});
});

function yaoqing(pageid){
	window.location.href = "/${oname}/user/cb_buddy_invite.action?pageid="+pageid+"&source=wxn";
}
</script>
<%@include file="/WEB-INF/card/background.jsp"%>

<div class="top0526_3">
	<div class="tx"><i class="tx_4"><img src="${sessionScope.visitUser.wxUser.headimgurl}" alt=""></i>
	    <div class="tx_2"><p class="tx_2_1">${sessionScope.visitUser.wxUser.nickname}</p></div>
	</div>
</div>
<div class="main0526_4" style="display: none;">
       <h1><span>我的伙伴</span></h1>
       <div class="mingxi">
           <h2>2016年2月23日</h2>
           <ul class="mx_3">
              <li><span class="left">微信名称</span><span class="blue">+12.35元</span></li>
              <li><span class="left">微信名称</span><span class="blue">+12.35元</span></li>
              <li><span class="left">微信名称</span><span class="blue">+12.35元</span></li>
           </ul>
       </div>
       <div class="mingxi">
           <h2>2016年2月23日</h2>
           <ul class="mx_3">
              <li><span class="left">微信名称</span><span class="blue">+12.35元</span></li>
              <li><span class="left">微信名称</span><span class="blue">+12.35元</span></li>
              <li><span class="left">微信名称</span><span class="blue">+12.35元</span></li>
           </ul>
       </div>           
</div>
<div class="main0526_3" style="display: none;">
       <div class="main0526_bg4"></div>
       <p>您还没有伙伴，赶紧去邀请吧！</p>
       <div class="tixian0527"><span>邀请伙伴</span></div>   
</div>
<div class="tixian0527_2" style="display: none;"><span>邀请伙伴</span></div>
<div class="bottom0526 block" status="0" hyct="Y" hydata="${blocks[0].rid}" <s:if test='method != "E"'>style="position:fixed;"</s:if>>
  <ul>
    <li class="wxz1"><a href="${blocks[0].link1}"><span>${blocks[0].title1}</span></a></li>
    <li class="xz2"><a href="${blocks[0].link2}"><span>${blocks[0].title2}</span></a></li>
    <li class="wxz3"><a href="${blocks[0].link3}"><span>${blocks[0].title3}</span></a></li>
  </ul>
</div>
<%@include file="/WEB-INF/card/cardfile.jsp"%>