<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<link rel="stylesheet" type="text/css" href="/css/xc/mo/yq/index.css" />
<link rel="stylesheet" type="text/css" href="/css/xc/mo/yq/animations.css" />
<link rel="stylesheet" type="text/css" href="/css/xc/mo/yq/load.css" /> 
<div id="loading">
   	<div class="spinner">
		<div class="double-bounce1"></div>
		<div class="double-bounce2"></div>
	</div>
</div>
<div id="content" style="display:none">
	<div class="page page-1-1 page-current">
		<div class="wrap">
			<img class="img_1 pt-page-moveFromTop" src="/images/xc/mo/yq/sy_yqh.png"/>
			<img class="img_2 pt-page-moveCircle" src="/images/xc/mo/yq/sy1_02.png" />
			<img class="img_3 pt-page-moveIconUp" src="/images/xc/mo/yq/icon_up.png" />	
		</div>
	</div>
	<div class="page page-2-1 hide">
		<div class="wrap">
			<img class="img_1 hide pt-page-moveFromLeft" src="/images/xc/mo/yq/p2_05.png" />
			<img class="img_2 hide pt-page-moveCircle" src="/images/xc/mo/yq/p2_02.png" />
			<img class="img_3 pt-page-moveIconUp" src="/images/xc/mo/yq/icon_up.png" />
		</div>
	</div>
	
	<div class="page page-3-1 hide">
		<div class="wrap">
			<div id="special" style="height:500px;">
				<img class="img_x1 fall1 hide" src="/images/xc/mo/yq/j_1.png" />
				<img class="img_x2 fall2 hide" src="/images/xc/mo/yq/j_2.png" />
				<img class="img_x3 fall3 hide" src="/images/xc/mo/yq/j_3.png" />
				<img class="img_x4 fall4 hide" src="/images/xc/mo/yq/j_4.png" />
				<img class="img_x5 fall5 hide" src="/images/xc/mo/yq/j_5.png" />
				<img class="img_x6 fall6 hide" src="/images/xc/mo/yq/j_6.png" />
				<img class="img_x7 fall7 hide" src="/images/xc/mo/yq/j_7.png" />
				<img class="img_x8 fall8 hide" src="/images/xc/mo/yq/j_8.png" />
				<img class="img_x9 fall9 hide" src="/images/xc/mo/yq/j_9.png" />
				<img class="img_5 hide pt-page-moveFromLeft1" src="/images/xc/mo/yq/j_02.png" /> 
			</div>
			<img class="img_1 hide pt-page-moveFromBottom1" src="/images/xc/mo/yq/j_04.png" />
			<img class="img_9 hide pt-page-moveIconUp" src="/images/xc/mo/yq/icon_up.png" />				
		</div>
	</div>
	<div class="page page-4-1 hide">
		<div class="wrap">
			<img class="img_1 hide pt-page-moveFromLeft" src="/images/xc/mo/yq/3_02.png" /> 
			<img class="img_3 pt-page-moveFromBottom hide" src="/images/xc/mo/yq/3_10.png" />
			<div class="leida"></div>
				<img class="img_4 hide pt-page-moveIconUp" src="/images/xc/mo/yq/icon_up.png" /> 
			</div>
		</div>
		<div class="page page-5-1 hide">
			<div class="wrap"  id="div1">
				<img class="img_1 hide pt-page-moveFromLeft" src="/images/xc/mo/yq/x4_06.png" /> 
				<img class="img_2 pt-page-moveFromRight hide" src="/images/xc/mo/yq/x4_03.png" />	
			</div>
			<div class="wrap"  id="div2" style="display: none">
				<img class="img_2 pt-page-moveFromRight hide" src="/images/xc/mo/yq/fail.png" />	
			</div>
		</div>        
   	</div>
	<div id="audiocontainer"></div>
	<div id="textsuper"><div id="textsub"><img id="fontimg" /></div></div>
   	<div id="share" onClick="closeDialog()">
   	<div class="shareImg"></div>
</div>
<script type="text/javascript">
	gSound = '/images/xc/mo/yq/1.mp3'; 
	</script>
       <script src="/js/xc/mo/yq/zepto.min.js"></script>
	<script src="/js/xc/mo/yq/touch.js"></script>
	<script src="/js/xc/mo/yq/index.js"></script>
       <script type="text/javascript">
	document.onreadystatechange = loading; 
	function loading(){
		if(document.readyState == "complete")
		{ 
			$("#loading").hide();
			$("#content").show();
			playbksound();
		}
	}
	function shareDialog(){
		 $("#share").show();			
	}
	function closeDialog(){
		$("#share").hide();
	}
</script>
<script type="text/javascript">
 		$(document).ready(function(){
 		$.ajax({
		url : "/${oname}/user/wxjssdk.action",
		type : "post",
		data : {
			'url' : window.location.href.split('#')[0]
		},
		success : function(data) {
			wx.config({
				debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				appId : data.appid, // 必填，公众号的唯一标识
				timestamp : data.timestamp, // 必填，生成签名的时间戳
				nonceStr : data.noncestr, // 必填，生成签名的随机串
				signature : data.signature,// 必填，签名，见附录1
				jsApiList : [// 必填，需要使用的JS接口列表，所有JS接口列表见附录2
					'onMenuShareAppMessage',
					'onMenuShareTimeline',
					'onMenuShareQQ',
					'onMenuShareWeibo' ]
			});
		},
		error : function(request) {
			alert("Connection error");
		}
	});
		var xcid= ${result["1"].xc.id };
		var pageid=${pageid};
		$.post("/${oname}/user/checkTeyue.action",{"hypage":pageid,"xcid":xcid},function(data){
			if(data!=-1){
				$("#div1").show();
				$("#div2").hide();
			}else{
				$("#div1").hide();
				$("#div2").show();
			}
		});
	});
	
wx.ready(function() {
	var title = '联想2015媒体新春大爬梯';
	var desc = '高管耍宝，鲜肉卖萌，糙爷们跳大绳，这酸爽！想想还有点小兴奋呢~';
	var link = ' ${pageDomain }/user/wxshowpage/106/wxn.html';
	var imgUrl = 'http://img.huiyee.com/xiaotu.jpg';

	// 2.1 监听“分享给朋友”，按钮点击、自定义分享内容及分享结果接口
	wx.onMenuShareAppMessage({
		title : title,
		desc : desc,
		link : link,
		imgUrl : imgUrl,
		success : function(res) {
		},
		cancel : function(res) {
		}
	});

	// 2.2 监听“分享到朋友圈”按钮点击、自定义分享内容及分享结果接口
	wx.onMenuShareTimeline({
		title : title,
		link : link,
		imgUrl : imgUrl,
		success : function(res) {
		},
		cancel : function(res) {
		}
	});

	// 2.3 监听“分享到QQ”按钮点击、自定义分享内容及分享结果接口
	wx.onMenuShareQQ({
		title : title,
		desc : desc,
		link : link,
		imgUrl : imgUrl,
		success : function(res) {
		},
		cancel : function(res) {
		}
	});

	// 2.4 监听“分享到微博”按钮点击、自定义分享内容及分享结果接口
	wx.onMenuShareWeibo({
		title : title,
		desc : desc,
		link : link,
		imgUrl : imgUrl,
		success : function(res) {
		},
		cancel : function(res) {
		}
	});
});
	$(document).ready(function(){
		var abc = $(window).width();
		$("#special").css("height",abc*1.5)
	})
 </script>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
