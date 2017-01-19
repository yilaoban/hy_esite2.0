<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 活动详情 -->
<link href="/css/cb/reset.css" rel="stylesheet" type="text/css" />
<link href="/css/cb/partner_1.css" rel="stylesheet" type="text/css" />
<%@include file="/WEB-INF/card/background.jsp"%>
<div class="zhishi"></div>
<div class="main0527_2">
  <div class="main0527_2_1">
    <s:if test='method == "E"'>
      <div class="left_img">
        <img src="/images/cb/pic0526_23.png" alt="">
      </div>
      <div class="right_con">
        <div class="ck">
          <a href="#">查看详情</a>
        </div>
        <p class="con_1">名称：奇趣童真，欢乐无限违法未tdyiy</p>
        <p class="con_1">时间：2016.05.25 ~ 2016.09.25</p>
        <p class="con_2">
          <i>介绍：</i>
          亲子活动是指，父母陪着孩子在假期参加一些社团或者企业组织的一些有益成长的活动一些有益成长的活动
        </p>
      </div>
    </s:if>
  </div>
  <div class="main0527_2_2">
    <h2>激励规则</h2>
    <s:if test='method == "E"'>
      <p>
        <span>1</span>
        <i>凡持中百集团会员卡的用户，均可参加会员积分活动。</i>
      </p>
      <p>
        <span>2</span>
        <i>登陆本网站后，即可以自助服务中查到自己的积分、积点情况，以及相关明细。</i>
      </p>
      <p>
        <span>3</span>
        <i>会员积分可在网站积分商城兑换相应的礼品</i>
      </p>
    </s:if>
  </div>
</div>
<div class="main0527_3">
  <p class="p_2">长按保存下图【二维码】发送给好友</p>
  <p>
    <i id="qrcode" class="ewm">
      <s:if test='method == "E"'>
        <img src="/images/cb/pic0526_25.png" alt="">
      </s:if>
    </i>
  </p>
</div>
<input type="hidden" id="cb_link" />
<%@include file="/WEB-INF/card/cardfile.jsp"%>

<script type="text/javascript" src="/js/qrcode.js"></script>
<script type="text/javascript" src="/js/mustache.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		if ("${method}" == "E") {
			return;
		}

		var qrcode = new QRCode(document.getElementById("qrcode"), {
			width : 180,
			height : 180
		});

		var source = "${sessionScope.visitUser.source}";
		var kv = $("#hy_kv").val();
		$.ajax({
			url : "/${oname}/user/cb_activity.action",
			type : "get",
			data : {
				"activity.id" : kv
			},
			cache : false,
			success : function(res) {
				if (res.status == 0) {
					var activity = res.activity;
					var type = activity.type;
					var enid = activity.enid;
					var url = "${pageDomain}/${oname}/user/wxshow/";
					if (type == 0) {
						url += enid + "/" + source + ".html";
					} else if (type == 1) {
						url += activity.npageid + "/" + source + "/n-" + enid + ".html";
					}
					$("#cb_link").val(url);

					var html = '';
					html += '<div class="left_img"><img src="${imgDomain}{{img}}"></div>';
					html += '<div class="right_con">';
					html += '  <div class="ck"><a href="'+url+'">查看详情</a></div>';
					html += '  <p class="con_1">名称：{{title}}</p>';
					html += '  <p class="con_1">';
					html += '    <i>时间：</i>';
					html += '    {{starttimeStr}} ~ <br/>{{endtimeStr}}';
					html += '  </p>';
					html += '  <p class="con_2">';
					html += '    <i>介绍：</i>';
					html += '    <span>{{content}}</span>';
					html += '  </p>';
					html += '</div>';
					$(".main0527_2_1").html(Mustache.render(html, activity));

					var i = 1;
					html = '';
					if (activity.click > 0 || activity.clickjf > 0) {
						html += '<p>';
						html += '  <span>' + i++ + '</span>';
						html += '  <i>带来一次有效点击获得 ';
						if (activity.click > 0) {
							html += activity.click / 100 + ' 元';
						}
						if (activity.click > 0 && activity.clickjf > 0) {
							html += ' / ';
						}
						if (activity.clickjf > 0) {
							html += activity.clickjf + ' 积分';
						}
						html += '  </i>';
						html += '</p>';
					}
					if (activity.zhuanfa > 0 || activity.zhuanfajf > 0) {
						html += '<p>';
						html += '  <span>' + i++ + '</span>';
						html += '  <i>带来一次有效转发获得 ';
						if (activity.zhuanfa > 0) {
							html += activity.zhuanfa / 100 + ' 元';
						}
						if (activity.zhuanfa > 0 && activity.zhuanfajf > 0) {
							html += ' / ';
						}
						if (activity.zhuanfajf > 0) {
							html += activity.zhuanfajf + ' 积分';
						}
						html += '  </i>';
						html += '</p>';
					}
					if (activity.guanzhu > 0 || activity.guanzhujf > 0) {
						html += '<p>';
						html += '  <span>' + i++ + '</span>';
						html += '  <i>带来一次有效关注获得 ';
						if (activity.guanzhu > 0) {
							html += activity.guanzhu / 100 + ' 元';
						}
						if (activity.guanzhu > 0 && activity.guanzhujf > 0) {
							html += ' / ';
						}
						if (activity.guanzhujf > 0) {
							html += activity.guanzhujf + ' 积分';
						}
						html += '  </i>';
						html += '</p>';
					}
					if (activity.hudong > 0 || activity.hudongjf > 0) {
						html += '<p>';
						html += '  <span>' + i++ + '</span>';
						html += '  <i>带来一次有效互动获得 ';
						if (activity.hudong > 0) {
							html += activity.hudong / 100 + ' 元';
						}
						if (activity.hudong > 0 && activity.hudongjf > 0) {
							html += ' / ';
						}
						if (activity.hudongjf > 0) {
							html += activity.hudongjf + ' 积分';
						}
						html += '  </i>';
						html += '</p>';
					}
					if (activity.waibu > 0 || activity.waibujf > 0) {
						html += '<p>';
						html += '  <span>' + i++ + '</span>';
						html += '  <i>带来一次有效外部事件获得 ';
						if (activity.waibu > 0) {
							html += activity.waibu / 100 + ' 元';
						}
						if (activity.waibu > 0 && activity.waibujf > 0) {
							html += ' / ';
						}
						if (activity.waibujf > 0) {
							html += activity.waibujf + ' 积分';
						}
						html += '  </i>';
						html += '</p>';
					}
					$(".main0527_2_2").append(html);

					qrcode.clear();
					qrcode.makeCode(url);
				} else {
					window.location.href = res.rs;
				}
			}
		});

	});

	
</script>