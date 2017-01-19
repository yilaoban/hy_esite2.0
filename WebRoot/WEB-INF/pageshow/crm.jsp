<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" type="text/css" href="/css/global.css">
<title>${news.title}</title>
<style>
body {
	background: #fff;
	font-size: 17px;
	color: #000;
	margin: 15px;
}

img {
	width: 100%
}

p {
	white-space: normal;
}

.title {
	font-weight: 700;
	color: #28292d;
	font-size: 21px;
	line-height: 31px;
}

.author {
	font-size: 13px;
	color: #8d8d8d;
	margin: 10px 0 20px;
}

.content {
	overflow-x: hidden;
}
</style>
<script src="/js/jquery-1.11.1.min.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
	var mpid = '${mpid}';
	var title = '${news.title}';
	var desc = '${news.digest}';
	var imgUrl = '${news.img}';
	var link = window.location.href.split('#')[0];
	$(document).ready(function() {
		if (mpid != '0') {
			$.ajax({
				url : "/weixin/jssdk.action",
				type : "get",
				data : {
					'url' : link,
					'mpid' : mpid
				},
				success : function(data) {
					wx.config({
						debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
						appId : data.appid, // 必填，公众号的唯一标识
						timestamp : data.timestamp, // 必填，生成签名的时间戳
						nonceStr : data.noncestr, // 必填，生成签名的随机串
						signature : data.signature,// 必填，签名，见附录1
						jsApiList : [// 必填，需要使用的JS接口列表，所有JS接口列表见附录2
						'onMenuShareAppMessage', 'onMenuShareTimeline', 'onMenuShareQQ', 'onMenuShareWeibo' ]
					});
				},
				error : function(request) {
				}
			});
		}
	});

	wx.ready(function() {

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
</script>
</head>
<body>
  <div class="title">${news.title}</div>
  <div class="author">${news.author}</div>
  <div class="content">${news.content}</div>
</body>
</html>
