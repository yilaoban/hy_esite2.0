<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 微预约提供者详情 -->
<link href="/css/wyy/center_details.css" rel="stylesheet" type="text/css" />
<%@include file="/WEB-INF/card/background.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	if("${method}"=="E"){
		return;
	}
	var kv = $('#hy_kv').val();
	var kk = kv.split("-");
	var catid = 0;
	var servicerid = 0;
	if(kk.length == 3){
		catid = kk[0];
		servicerid = kk[2];
	}
	$('#wyycontent').empty();
	$('#servicer').empty();
	$.post("/${oname}/user/findYuYueServicerById.action",{"catid":catid,"serid":servicerid},function(data){
		if(data.yuYueServicer){
			var html = '<img src="'+data.yuYueServicer.img+'">'+
						'<p class="details_1">'+data.yuYueServicer.hydesc+'</p>'+
						'<p class="yuyue"><a href="/${oname}/user/show/'+data.current.xpageid+'/pcn/'+$('#hy_kv').val()+'.html">立即预约</a></p>';
			$('#servicer').append(html);
			$('#wyycontent').append(data.yuYueServicer.hyldesc);

		}
	});

});
	</script>

<div class="top0510" id="servicer">
	<img src="/images/wyy/pic_05103.png">
	<p class="details_1">原价1018元的美发套餐，现仅售680元。请于消费前3小时..原价1018元的美发套餐，现仅售680元。请于消费前3小时原价1018元的美发套餐，</p>
	<p class="yuyue"><a href="#">立即预约</a></p>
</div>
<div class="space"></div>
<div class="introduce0510">
	<h1>详情</h1>
	<div class="common" id="wyycontent">
		<p>适用肤质：<span>压力性和感性肌肤适用</span></p>
		<p>服务时间：<span>90分钟</span></p>
		<p>服务流程：</p>
		<p class="kongge">洁肤-去角质-按摩-面膜护理-营养护理</p>
		<p>服务疗程：</p>
		<p class="kongge">每星期1-2次，10次一个疗程。建议可同时使用做手摸护理。</p>
		<p class="show"><img src="/images/wyy/pic_05104.png"></p>
		<p class="show"><img src="/images/wyy/pic_05105.png"></p>
	</div>
</div>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
