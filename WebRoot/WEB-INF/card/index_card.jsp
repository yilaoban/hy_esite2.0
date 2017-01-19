<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<script type="text/javascript" src="/js/qrcode.js"></script>
<link href="/css/mycards/index_card.css" rel="stylesheet" type="text/css" />
<link href="https://res.wx.qq.com/open/libs/weui/0.4.0/weui.css" rel="stylesheet"/>
		<link href="/js/jquery-weui/css/jquery-weui.css" rel="stylesheet"/>
		<script src="/js/jquery-weui/js/jquery-weui.js" type="text/javascript"></script>
<!-- 我的卡券 -->
<script type="text/javascript">
document.title='NO.${sessionScope.visitUser.hyUser.id}';

$(document).ready(function(){
	$.post("/${oname}/user/grCenter.action",function(data){
		if(data){
			$('#img').attr("src",data.balanceSet.hykimg);
			$('#cz').html(data.rmb/100);
			$('#jf').html(data.jf);
			$('#kq').html(data.kqCount);
			if(data.hyUserLevel){
				$(".member_level_text").html(data.hyUserLevel.name);
			}else if(data.balanceSet.hyname){
				$(".member_level_text").html(data.balanceSet.hyname);
			}
			$(".middle0503").find("ul").find("li").find("a").eq(0).attr("onclick","myChuZhi("+data.pagedto.rmyid+")");
			$(".middle0503").find("ul").find("li").find("a").eq(1).attr("href","/${oname}/user/show/"+data.pagedto.rmjid+"/wxn.html");
			$(".middle0503").find("ul").find("li").find("a").eq(2).attr("href","/${oname}/user/showKQList.action");
			$(".middle0503").find("ul").find("li").find("a").eq(3).attr("href","/${oname}/user/vip_info.action?pageid=${pageid}");
			$(".middle0503").find("ul").find("li").find("a").eq(4).attr("href","/${oname }/user/vipRmbRecord.action?solrtype=RMB");
		}else{
			$('#img').attr("src","/images/mycards/pic_291.png");
		}
	});
});
	
	function myChuZhi(rmyid) {
		$.post("/${oname}/user/vipInfoAjax.action",function(data){
			if(data){
				if(data.telphone){
					window.location.href = "/${oname}/user/show/"+rmyid+"/wxn.html";
				}else{
					$.modal({
						  title: "",
						  text: "为保证你的储值安全，请先完善您的个人资料",
						  buttons: [
						    { text: "完善资料", onClick: function(){ 
						    	window.location.href = "/${oname}/user/vip_info.action?pageid=" + rmyid;
						    } },
						    { text: "取消", className: "default", onClick: function(){ console.log(3)} },
						  ]
						});
				}
			}
		})
	}
	
</script>
<%@include file="/WEB-INF/card/background.jsp"%>
<div class="top0429">
	<img src="" style="width:80%;margin:auto;padding:20px 0 0;height:100%;display: block;" id="img">
	<input type="hidden" value="${sessionScope.visitUser.hyUser.id}">
	<img class="member_level" src="/images/mycards/label.png">
	<span class="member_level_text">普通</span>
</div>
<div class="middle0503">
	<ul>
		<li><a href="javascript:void(0)"  onclick=""><span><img src="/images/mycards/pic_293.png" alt="">我的储值</span><i>￥<em id="cz">0.00</em><big class="big_shouye"></big></i></a></li>
		<li><a href="#"><span><img src="/images/mycards/pic_294.png" alt="">我的积分</span><i><em id="jf">0</em>分<big class="big_shouye"></big></i></a></li>
		<li><a href="#"><span><img src="/images/mycards/pic_295.png" alt="">我的卡券</span><i><big class="num" id="kq">1</big><small>张</small><big class="big_shouye"></big></i></a></li>
	</ul>
    <ul>
		<li><a href="#"><span><img src="/images/mycards/pic_296.png" alt="">个人信息</span><i><b>完善</b><big class="big_shouye"></big></i></a></li>
		<li><a href="#"><span><img src="/images/mycards/pic_297.png" alt="">账单</span><i><big class="big_shouye"></big></i></a></li>
	</ul>
	<ul class="middle0503ul block">
		<li><a href="javascript:void(0);" onclick="showUrl()"><span><img src="/images/mycards/pic_298.png" alt="">会员支付</span><i><big class="big_shouye"></big></i></a></li>
	</ul>
</div>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
	<div id="qrcode" style="display: none;" ></div>
	<script type="text/javascript">
		function showUrl(){
			$.showLoading();
			$.post("/${oname}/user/uway.action",function(data){
				if(data.status == 1){
					qrcode.makeCode(data.url);
		  			setTimeout(function() {
			          	$.hideLoading();
						erweima(data.uway);
			        }, 1500);
		       		$("#qrcode img").css("margin","0 auto");
				}else{
					$.alert(data.hydesc);
				}
			});
		
		}
		var run = false;
		function erweima(uway){
	  		  $.modal({
		          title: "扫描二维码",
		          text: $('#qrcode').html(),
		          buttons: [
		            { text: "取消", className: "default",onClick: function(){run = false }},
		          ]
		        });
	  		run = true;
	  		check(uway);
	  	}
	
		function check(uway){
	  		if(!run) return;
	  		$.post("/${oname}/user/checkuwayused.action",{"uwayid":uway},function(data){
	  			if(data == 1){
	  				run = false;
	  				$.closeModal();
	  				setTimeout(function(){
	  					window.location.reload();
	  				},2000);
	  			}else{
		  			setTimeout("check("+uway+")",1000);
	  			}
	  		});
	  	}
		
	
	var qrcode = new QRCode(document.getElementById("qrcode"), {
		width : 200,
		height : 200
	});
	</script>