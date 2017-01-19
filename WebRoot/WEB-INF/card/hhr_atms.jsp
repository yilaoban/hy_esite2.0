<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 提现明细 -->
<link href="/css/cb/partner_1.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$(document).ready(function(){
	$('.mingxi').remove();
	$.post("/${oname}/user/tixianRecord.action",function(data){
		if(data.cbHbRecordList){
			$.each( data.cbHbRecordList, function(i, n){
				var html = '<div class="mingxi">';
				html += '<p class="p_223">'+n.createtimeStr+'</p>';
				html += '<ul class="mx_2">';
				$.each(n.rlist,function(i2,n2){
					if(n2.status == 0){
						html += '<li><span class="left">微信红包</span><span class="center">'+n2.num/100+'元</span><span class="blue">未审核</span></li>';
					}else if(n2.status == 1){
						html += '<li><span class="left">微信红包</span><span class="center">'+n2.num/100+'元</span><span>审核通过</span></li>';
					}else if(n2.status == -1){
						html += '<li><span class="left">微信红包</span><span class="center">'+n2.num/100+'元</span><span>审核不通过</span></li>';
					}
				});
				html += '</ul>';
				html += '</div>';
				$('.main0526_4').append(html);
			});
			$(".main0526_4").show();
		}else{
			$(".main0526_3").show();
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
<div class="main0526_4" style="display: none">
      <h1><span>提现明细</span></h1>
      <div class="mingxi">
          <p class="p_223">2016年2月23日</p>
      	   <ul class="mx_2">
      	   	  <li><span class="left">微信红包</span><span class="center">2225.23元</span><span class="blue">审核中</span></li>
      	   	  <li><span class="left">微信红包</span><span class="center">25.23元</span><span class="blue">审核中</span></li>
      	   	  <li><span class="left">微信红包</span><span class="center">25.23元</span><span>已到账</span></li>
      	   </ul>
      </div>
      <div class="mingxi">
          <h2>2016年2月23日</h2>
      	   <ul class="mx_2">
      	   	  <li><span class="left">微信红包</span><span class="center">25.23元</span><span>已到账</span></li>
      	   	  <li><span class="left">微信红包</span><span class="center">25.23元</span><span>已到账</span></li>
      	   	  <li><span class="left">微信红包</span><span class="center">25.23元</span><span>已到账</span></li>
      	   </ul>
      </div>
</div>
<div class="main0526_3" style="display: none">
           <div class="main0526_bg3"></div>
           <p>您还没有提现记录吧！</p>   
</div>
<div class="bottom0526 block" status="0" hyct="Y" hydata="${blocks[0].rid}" <s:if test='method != "E"'>style="position:fixed;"</s:if>>
	<ul>
		<li class="wxz1"><a href="${blocks[0].link1}"><span>${blocks[0].title1}</span></a></li>
		<li class="xz2"><a href="${blocks[0].link2}"><span>${blocks[0].title2}</span></a></li>
		<li class="wxz3"><a href="${blocks[0].link3}"><span>${blocks[0].title3}</span></a></li>
	</ul>
</div>
<%@include file="/WEB-INF/card/cardfile.jsp"%>