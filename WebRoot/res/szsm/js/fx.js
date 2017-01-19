$(document).ready(function() {
	$.ajax({
		url : "/user/wxjssdk.action",
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
					'onMenuShareWeibo',
					'uploadImage',
					'downloadImage',
					'chooseImage' ]
			});
		},
		error : function(request) {
		}
	});
});

var title = '砸金蛋,赢大奖';
var desc = '情人节到了码哥怎么能还是一个人呢,还不快来一睹码妹的芳容~更有情人节大奖等你来拿!';
var link = 'http://page.huiyee.com/user/wxshowpage/129/wxn.html';
var imgUrl = 'http://img.huiyee.com/smqk1.jpg';
var source = 'wxn';

wx.ready(function() {
		// 2.1 监听“分享给朋友”，按钮点击、自定义分享内容及分享结果接口
		wx.onMenuShareAppMessage({
			title : title,
			desc : desc,
			link : link,
			imgUrl : imgUrl,
			trigger: function (res) {
			},
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