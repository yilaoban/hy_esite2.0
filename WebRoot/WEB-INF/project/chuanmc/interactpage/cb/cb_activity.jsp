<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta content="width=320, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" name="viewport" />
<title>活动详情</title>
<link href="https://res.wx.qq.com/open/libs/weui/0.4.0/weui.css" rel="stylesheet" />
<link href="/js/jquery-weui/css/jquery-weui.css" rel="stylesheet" />
<link href="/css/cb/reset.css" rel="stylesheet" type="text/css" />
<link href="/css/cb/partner_1.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="/js/jquery-weui/js/jquery-weui.js"></script>
<script type="text/javascript" src="/js/qrcode.js"></script>
<script type="text/javascript">
	var title = '${dto.activity.wps.title}';
	var desc = '${dto.activity.wps.description}';
	var imgUrl = '${imgDomain}${dto.activity.wps.pic}';
	var pageid = '${dto.activity.wps.pageid}';
	var source = '${source}';
	var link = '';
	var spageid = 0;

	$(document).ready(function() {
		jssdk();
	});

	function jssdk() {
		$.ajax({
			url : "/${oname}/weixin/jssdk.action",
			type : "get",
			data : {
				"url" : window.location.href.split('#')[0],
				"pageid" : pageid
			},
			success : function(data) {
				if (data) {
					if (data.wps) {
						title = data.wps.title;
						desc = data.wps.description;
						imgUrl = data.wps.pic;
						pageid = data.wps.pageid;
						spageid = data.wps.spageid;
					}
					wx.config({
						debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
						appId : data.appid, // 必填，公众号的唯一标识
						timestamp : data.timestamp, // 必填，生成签名的时间戳
						nonceStr : data.noncestr, // 必填，生成签名的随机串
						signature : data.signature,// 必填，签名，见附录1
						jsApiList : [ // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
						'onMenuShareAppMessage', //
						'onMenuShareTimeline',//
						'onMenuShareQQ', //
						'onMenuShareWeibo' ]
					});
				}
			}
		});
	}

	function wxs() {
		$.ajax({
			url : "/${oname}/user/wxs.action?aoid=" + pageid,
			type : "get",
			success : function(res) {
				if (spageid > 0) {
					window.location.href = '/${oname}/user/wxshow/' + spageid + '/' + source + '.html';
				}
			}
		});
	}

	wx.ready(function() {
		title = title || "活动详情";
		source = source || 'wxn';

		var type = '${dto.activity.type}';
		var enid = '${dto.activity.enid}';
		if (type == '0') {
			$(".ck").attr("href", "${pageDomain}/${oname}/user/show/" + pageid + "/" + source + ".html");
			link = "${pageDomain}/${oname}/user/wxshow/" + pageid + "/" + source + ".html";
		} else if (type == '1') {
			$(".ck").attr("href", "${pageDomain}/${oname}/user/show/" + pageid + "/" + source + "/n-" + enid + ".html");
			link = "${pageDomain}/${oname}/user/wxshow/" + pageid + "/" + source + "/n-" + enid + ".html";
		}
		var qrcode = new QRCode(document.getElementById("qrcode"), {
			width : 180,
			height : 180
		});
		qrcode.clear();
		qrcode.makeCode(link);

		// 2.1 监听“分享给朋友”，按钮点击、自定义分享内容及分享结果接口
		wx.onMenuShareAppMessage({
			title : title,
			desc : desc,
			link : link,
			imgUrl : imgUrl,
			success : function(res) {
				wxs();
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
				wxs();
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
  <div class="bg0526_2"></div>
  <div class="zhishi"></div>
  <div class="main0527_2">
    <div class="main0527_2_1">
      <div class="left_img">
        <img src="${imgDomain}${dto.activity.img}" alt="">
      </div>
      <div class="right_con">
        <p class="con_1">
          <span>名称：</span>
          <span class="span_1">
            <span class="span_2">${dto.activity.title}</span>
          </span>
          <a href="#" class="ck">查看详情</a>
        </p>
        <p class="con_1">时间：${dto.activity.starttimeStr} ~ ${dto.activity.endtimeStr}</p>
        <p class="con_2">
          <i>介绍：</i>
          <span>${dto.activity.content}</span>
        </p>
      </div>
    </div>
    <div class="main0527_2_2">
      <h2>激励规则</h2>
      <s:if test="dto.activity.zhuanfa>0 || dto.activity.zhuanfajf>0">
        <p>
          <span></span>
          <i>
            带来一次有效转发获得
            <s:if test="dto.activity.zhuanfa>0">${dto.activity.zhuanfa/100} 元 </s:if>
            <s:if test="dto.activity.zhuanfa>0 && dto.activity.zhuanfajf>0"> / </s:if>
            <s:if test="dto.activity.zhuanfajf>0">${dto.activity.zhuanfajf} 积分</s:if>
          </i>
        </p>
      </s:if>
      <s:if test="dto.activity.click>0 || dto.activity.clickjf>0">
        <p>
          <span></span>
          <i>
            带来一次有效点击获得
            <s:if test="dto.activity.click>0">${dto.activity.click/100} 元 </s:if>
            <s:if test="dto.activity.click>0 && dto.activity.clickjf>0"> / </s:if>
            <s:if test="dto.activity.clickjf>0">${dto.activity.clickjf} 积分</s:if>
          </i>
        </p>
      </s:if>
      <s:if test="dto.activity.guanzhu>0 || dto.activity.guanzhujf>0">
        <p>
          <span></span>
          <i>
            带来一次有效关注获得
            <s:if test="dto.activity.guanzhu>0">${dto.activity.guanzhu/100} 元 </s:if>
            <s:if test="dto.activity.guanzhu>0 && dto.activity.guanzhujf>0"> / </s:if>
            <s:if test="dto.activity.guanzhujf>0">${dto.activity.guanzhujf} 积分</s:if>
          </i>
        </p>
      </s:if>
      <s:if test="dto.activity.hudong>0 || dto.activity.hudongjf>0">
        <p>
          <span></span>
          <i>
            带来一次有效互动获得
            <s:if test="dto.activity.hudong>0">${dto.activity.hudong/100} 元 </s:if>
            <s:if test="dto.activity.hudong>0 && dto.activity.hudongjf>0"> / </s:if>
            <s:if test="dto.activity.hudongjf>0">${dto.activity.hudongjf} 积分</s:if>
          </i>
        </p>
      </s:if>
      <s:if test="dto.activity.waibu>0 || dto.activity.waibujf>0">
        <p>
          <span></span>
          <i>
            带来一次有效外部事件获得
            <s:if test="dto.activity.waibu>0">${dto.activity.waibu/100} 元 </s:if>
            <s:if test="dto.activity.waibu>0 && dto.activity.waibujf>0"> / </s:if>
            <s:if test="dto.activity.waibujf>0">${dto.activity.waibujf} 积分</s:if>
          </i>
        </p>
      </s:if>
    </div>
  </div>
  <div class="main0527_3">
    <p class="p_2">长按保存下图【二维码】发送给好友</p>
    <p>
      <i id="qrcode" class="ewm"></i>
    </p>
  </div>
</body>
</html>
