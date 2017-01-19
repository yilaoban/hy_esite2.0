<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 佣金明细 -->
<link href="/css/cb/partner_1.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	$(document).ready(function(){
		var hyuid = '${sessionScope.visitUser.hyUser.id}';
		$(".mingxi").remove();
		$.post("/${oname}/user/userSolrDetail.action",{"hyuid":hyuid,"solrtype":'CHR'},function(data){
			if(data.ulist.length > 0){
				$.each(data.ulist,function(i, n){
					var html = '<div class="mingxi">';
					html += '<p class="p_223">'+n.createStr+'</p>';
					html += '<ul class="mx_1">';
					$.each(n.nlist,function(i2,n2){
						console.log(n2.wxUser);
						if(n2.wxUser){
					    	html += '<li><span class="left">'+n2.wxUser.nickname+'</span><span class="center">'+n2.stypename+'</span><span class="center">'+n2.hhmm+'</span><span class="blue">'+n2.score/100+'元</span></li>';
						}else{
							html += '<li><span class="left">匿名</span><span class="center">'+n2.stypename+'</span><span class="center">'+n2.hhmm+'</span><span class="blue">'+n2.score/100+'元</span></li>';
						}
					});
					html += '</ul>';   
				    html += '</div>'; 
					$(".main0526_4").append(html);
					$(".main0526_4").show();
				});
			}else{
				$(".main0526_3").show();
			}
		});
		
		$.post("/${oname}/user/myHuobi.action",function(data){
			if(data){
				$('#total').html(data.cbSender.hbtotal/100);
			}
		});
	});
</script>


<%@include file="/WEB-INF/card/background.jsp"%>

<div class="top0526_3">
	<div class="tx"><i class="tx_5"><img src="${sessionScope.visitUser.wxUser.headimgurl}" alt=""></i>
	    <div class="tx_2"><p class="tx_2_1">${sessionScope.visitUser.wxUser.nickname}</p><p class="tx_2_2">总佣金：<span id="total">0</span>元</p></div>
	</div>
</div>
<div class="main0526_4" style="display: none">
    <h1><span>佣金明细</span></h1>
    <div class="mingxi">
        <p class="p_223">2016年2月23日</p>
    	   <ul class="mx_1">
    	   	  <li><span class="left">某某活动tyjt速度恢复为偶偶</span><span class="center">商品成交</span><span class="center">16：23</span><span class="blue">+8.80元</span></li>
    	   	  <li><span class="left">某某活动tyjt速度恢复为偶偶</span><span class="center">成为潜客</span><span class="center">16：23</span><span class="blue">+1.80元</span></li>
    	   	  <li><span class="left">某某活动tyjt速度恢复为偶偶</span><span class="center">商品成交</span><span class="center">16：23</span><span class="blue">+1.80元</span></li>
    	   </ul>
    </div>
    <div class="mingxi">
       <p class="p_223">2016年2月23日</p>
    	   <ul class="mx_1">
    	   	  <li><span class="left">某某活动tyjt速度恢复为偶偶</span><span class="center">商品成交</span><span class="center">16：23</span><span class="blue">+1.80元</span></li>
    	   	  <li><span class="left">某某活动tyjt速度恢复为偶偶</span><span class="center">商品成交</span><span class="center">16：23</span><span class="blue">+4474.80元</span></li>
    	   	  <li><span class="left">某某活动tyjt速度恢复为偶偶</span><span class="center">商品成交</span><span class="center">16：23</span><span class="blue">+1.80元</span></li>
    	   </ul>
    </div>
</div>
<div class="main0526_3" style="display: none">
            <div class="main0526_bg2"></div>
            <p>您还没有佣金，赶紧分享赚钱吧！</p>   
</div>
<div class="bottom0526 block" status="0" hyct="Y" hydata="${blocks[0].rid}" <s:if test='method != "E"'>style="position:fixed;"</s:if>>
	<ul>
		<li class="wxz1"><a href="${blocks[0].link1}"><span>${blocks[0].title1}</span></a></li>
		<li class="xz2"><a href="${blocks[0].link2}"><span>${blocks[0].title2}</span></a></li>
		<li class="wxz3"><a href="${blocks[0].link3}"><span>${blocks[0].title3}</span></a></li>
	</ul>
</div>
<%@include file="/WEB-INF/card/cardfile.jsp"%>