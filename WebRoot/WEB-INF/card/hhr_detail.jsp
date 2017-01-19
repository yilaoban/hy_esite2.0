<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 积分明细 -->
<link href="/css/cb/partner_1.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	$(document).ready(function(){
		var hyuid = '${sessionScope.visitUser.hyUser.id}';
		$(".mingxi").remove();
		$.post("/${oname}/user/userSolrDetail.action",{"hyuid":hyuid,"solrtype":'CHB'},function(data){
			if(data.ulist.length > 0){
				$.each(data.ulist,function(i, n){
					var html = '<div class="mingxi">';
					html += '<p class="p_223">'+n.createStr+'</h2>';
					html += '<ul class="mx_1">';
					$.each(n.nlist,function(i2,n2){
						if(n2.wxUser){
					    	html += '<li><span class="left">'+n2.wxUser.nickname+'</span><span class="center">'+n2.stypename+'</span><span class="center">'+n2.hhmm+'</span><span class="blue">'+n2.score+'积分</span></li>';
						}else{
							html += '<li><span class="left">匿名</span><span class="center">'+n2.stypename+'</span><span class="center">'+n2.hhmm+'</span><span class="blue">'+n2.score+'积分</span></li>';
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
				$('#total').html(data.cbSender.jf);
			}
		});
	});
</script>
<%@include file="/WEB-INF/card/background.jsp"%>

<div class="top0526_3">
		<div class="tx"><i class="tx_5"><img src="${sessionScope.visitUser.wxUser.headimgurl}" alt=""></i>
		    <div class="tx_2"><p class="tx_2_1">${sessionScope.visitUser.wxUser.nickname}</p><p class="tx_2_2">总积分：<span id="total">0</span></p></div>
	</div>
</div>
<div class="main0526_4" style="display: none">
    <h1><span>积分明细</span></h1>
    <div class="mingxi">
        <p class="p_223">2016年2月23日</p>
    	   <ul class="mx_1">
    	   	  <li><span class="left">某某活动tyjt速度恢复为偶偶</span><span class="center">带来互动</span><span class="center">16：23</span><span class="blue">+12222积分</span></li>
    	   	  <li><span class="left">某某活动tyjt速度恢复为偶偶</span><span class="center">带来转发</span><span class="center">16：23</span><span class="blue">+1积分</span></li>
    	   	  <li><span class="left">某某活动tyjt速度恢复为偶偶</span><span class="center">带来转发</span><span class="center">16：23</span><span class="blue">+1积分</span></li>
    	   </ul>
    </div>
    <div class="mingxi">
        <h2>2016年2月23日</h2>
    	   <ul class="mx_1">
    	   	  <li><span class="left">某某活动tyjt速度恢复为偶偶</span><span class="center">带来转发</span><span class="center">16：23</span><span class="blue">+1积分</span></li>
    	   	  <li><span class="left">某某活动tyjt速度恢复为偶偶</span><span class="center">带来转发</span><span class="center">16：23</span><span class="blue">+1积分</span></li>
    	   	  <li><span class="left">某某活动tyjt速度恢复为偶偶</span><span class="center">带来转发</span><span class="center">16：23</span><span class="blue">+1积分</span></li>
    	   </ul>
    </div>
</div>
<div class="main0526_3" style="display: none">
            <div class="main0526_bg1"></div>
            <p>您还没有积分，赶紧分享赚积分吧！</p>   
</div>

<div class="bottom0526 block" status="0" hyct="Y" hydata="${blocks[0].rid}" <s:if test='method != "E"'>style="position:fixed;"</s:if>>
	<ul>
		<li class="wxz1"><a href="${blocks[0].link1}"><span>${blocks[0].title1}</span></a></li>
		<li class="xz2"><a href="${blocks[0].link2}"><span>${blocks[0].title2}</span></a></li>
		<li class="wxz3"><a href="${blocks[0].link3}"><span>${blocks[0].title3}</span></a></li>
	</ul>
</div>
<%@include file="/WEB-INF/card/cardfile.jsp"%>