var zfy =new Array(
	'过年好！红包拿来！咩～',
	'过年好！还是一个人么？租个女盆友回来过年吧～',
	'过年好！还是一个人么？租个男盆友回来过年吧～',
	'万事如意，心想事成，恭喜发财！那么问题来了，你到底给多少红包？',
	'过年好！金锁都当皇帝了，你的公司融到几百块了捏？',
	'新年好！年终奖够给我包红包哇，不够就别来我家了～',
	'过年好！说好的我的女朋友呢，加油呀！',
	'过年好！说好的我的男朋友呢，加油呀！',
	'过年好！不管你带不带女神回来，我们都爱你！',
	'过年好！不管你带不带男神回来，我们都爱你！',
	'过年好！今年不要红包，我要一双滑板鞋，去广场上摩擦摩擦～',
	'新年好！首都地铁都涨价了，红包还能再鼓点吗…',
	'奔跑吧！没抢到火车票也阻挡不了你给我红包的脚步！过年好！',
	'过年好！不给红包买汽水，干吃炸鸡不开心呀～',
	'过年好！不能任性，红包给我！',
	'过年好！红包派发不够熟，送回蓝翔去重造……',
	'过年好！让我们红尘做伴活得潇潇洒洒，策马奔腾共享人世繁华，对酒当歌唱出心中喜悦，轰轰烈烈把握青春年华……',
	'过年好！银生就是一场走哪儿吃哪儿的旅行，我才起步儿，还需你的大大红包支持！'
);

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

var title = '萌宝拜年';
var desc = '快来上传萌宝照片，给七大姑八大姨他二舅们拜年吧！';
var link = 'http://page.huiyee.com/user/wxshowpage/131/wxn.html';
var imgUrl = 'http://img.huiyee.com/mjm.jpg';
var source = 'wxn';

wx.ready(function() {
		// 2.1 监听“分享给朋友”，按钮点击、自定义分享内容及分享结果接口
		wx.onMenuShareAppMessage({
			title : title,
			desc : desc,
			link : link,
			imgUrl : imgUrl,
			trigger: function (res) {
				this.link="http://page.huiyee.com/user/wxshowpage/125/"+source+'.html';
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
 	var images = {
		localId: [],
		serverId: []
	};
	document.querySelector('#chooseImage').onclick = function () {
		wx.chooseImage({
			success: function (res) {
				images.localId = res.localIds;
				if(res.localIds.length == 1){
					upload();
				}else{
					alert("只需要一张照片");
				}
			}
		});
	};
	
	 function upload() {
		wx.uploadImage({
			localId: images.localId[0],
			success: function (res) {
				$.post("/user/wxmedia.action",{"media_id":res.serverId},function(data){
					$("#tp3").attr("src",data);
					$("#tp4").attr("src",data);
					var i = Math.floor(Math.random()*zfy.length);
					var zf = $("#name").val()+" "+zfy[i];
					$("#zfy3").text(zf);
					$("#zfy4").text(zf);
					showPart(3);
				});
			},
			fail: function (res) {
				alert(JSON.stringify(res));
			}
		});
	}
	
});