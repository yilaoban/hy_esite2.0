<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 实时排行 -->
<link href="/css/cb/partner_1.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$(document).ready(function(){
	$(".mx_3").empty();
	var date = new Date().getFullYear()+"年"+(new Date().getMonth()+1)+"月"+new Date().getDate()+"日";
	$('.mingxi').find("p").html(date);
	$.post("/${oname}/user/senderSequence.action",function(data){
		if(data.cbSenderList){
			$.each( data.cbSenderList, function(i, n){
				$('.mx_3').append('<li><span class="left">'+(i+1)+'<i class="tx_6"><img src="'+n.wxUser.headimgurl+'" alt=""></i>'+n.wxUser.nickname+'</span><span class="blue">'+n.hbtotal/100+'元</span></li>');
			});
		}
	});
});

</script>
<%@include file="/WEB-INF/card/background.jsp"%>

<div class="top0526_3">
   <div class="tx"><i class="tx_4"><img src="${sessionScope.visitUser.wxUser.headimgurl}" alt=""></i>
       <div class="tx_2"><p class="tx_2_1">${sessionScope.visitUser.wxUser.nickname}</p></div>
   </div>
 </div>
<div class="main0526_4">
       <h1><span>实时排行</span></h1>
     <div class="mingxi">
         <p class="p_223">2016年2月23日 <span>00:00~11:11</span></p>
         <ul class="mx_3">
            <li><span class="left">1<i class="tx_6"><img src="/images/cb/pic0526_1.png" alt=""></i>微信名称而with为UI人</span><span class="blue">12.35元</span></li>
            <li><span class="left">2<i class="tx_6"><img src="/images/cb/pic0526_1.png" alt=""></i>微信名称而with为UI人</span><span class="blue">12.35元</span></li>
            <li><span class="left">3<i class="tx_6"><img src="/images/cb/pic0526_1.png" alt=""></i>微信名称而with为UI人</span><span class="blue">12.35元</span></li>
         </ul>
     </div>
 </div>
<div class="bottom0526 block" status="0" hyct="Y" hydata="${blocks[0].rid}" <s:if test='method != "E"'>style="position:fixed;"</s:if>>
   <ul>
     <li class="wxz1"><a href="${blocks[0].link1}"><span>${blocks[0].title1}</span></a></li>
     <li class="wxz2"><a href="${blocks[0].link2}"><span>${blocks[0].title2}</span></a></li>
     <li class="xz3"><a href="${blocks[0].link3}"><span>${blocks[0].title3}</span></a></li>
   </ul>
 </div>
<%@include file="/WEB-INF/card/cardfile.jsp"%>