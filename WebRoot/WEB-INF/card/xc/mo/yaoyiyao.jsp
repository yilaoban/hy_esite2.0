<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!-- xc摇一摇 -->
<link href="/css/xc/mo/yao/list.css" rel="stylesheet" type="text/css">
<%@include file="/WEB-INF/card/background.jsp"%>
<div id="audiocontainer"></div>

		<s:if test='blocks[0].display=="Y"'>
		<div class="yyy_top block ${blocks[0].ctname}" status="0" hyct="Y" style="${blocks[0].hyct};" hydata="${blocks[0].rid}" hyblk="S">
			<img src="${blocks[0].img}">
		</div>
		</s:if>
		<s:if test='blocks[1].display=="Y"'>
		<div class="yyy_center block ${blocks[1].ctname}" status="0" hyct="Y" style="${blocks[1].hyct};" hydata="${blocks[1].rid}" hyblk="S">
			<img src="${blocks[1].img}">
		</div>
		</s:if>
		<s:if test='blocks[2].display=="Y"'>
		<div hydata="${blocks[2].rid}" hyct="Y" status="0" class="yyy_cishu block  ${blocks[2].ctname}" style="line-height:40px;font-weight:600;font-size:16px;position:absolute;padding:8px;${blocks[2].hyct}">
			<div style="width:100%;height:100%;text-align:center;background:rgba(${blocks[2].col },${blocks[2].tm });border-radius:${blocks[2].bdradius}px;color:${blocks[2].fontcol};">
				您一共摇了<span id="result">0</span>次
			</div>
		</div>
		</s:if>
<script type="text/javascript">
var gSound = "http://img.huiyee.com/egg/yaoyiyao.mp3";
	$(document).ready(function() {
		var audiocontainer = document.getElementById('audiocontainer');
		if (audiocontainer != undefined) {
			audiocontainer.innerHTML = '<audio id="bgsound"> <source src="' + gSound + '" /> </audio>';
		}
		function playbksound() {
			var audio = document.getElementById('bgsound');
			audio.play();
		}
		
		var last_update = 0;
		var x = y = z = last_x = last_y = last_z = 0;
		var SHAKE_THRESHOLD = 1000;
		if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {
			SHAKE_THRESHOLD = 3000;
		} else if (/(Android)/i.test(navigator.userAgent)) {
			SHAKE_THRESHOLD = 1000;
		}

		if (window.DeviceMotionEvent) {
			window.addEventListener('devicemotion', deviceMotionHandler, false);
		} else {
			$.alert('not support mobile event','');
		}

		function deviceMotionHandler(eventData) {
			var acceleration = eventData.accelerationIncludingGravity;
			var curTime = new Date().getTime();
			if ((curTime - last_update) > 100) {
				var diffTime = curTime - last_update;
				last_update = curTime;
				x = acceleration.x;
				y = acceleration.y;
				z = acceleration.z;

				var speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;
				if (speed > SHAKE_THRESHOLD) {
					playbksound();
					$.ajax({
						url : "/${oname}/user/xcAction.action",
						type : "post",
						dataType : "json",
						data : {
							"xcid" : $("#hy_entity").val(),
							"random" : Math.random()
						},
						cache : false,
						async : false,
						success : function(data) {
							if (!isNaN(data)) {
								//0-活动未开始，-1-不在活动时间，-2-活动已结束，-3-用户为null，-4-用户id为0，-5-用户不符合活动要求
								var num = parseInt(data);
								if (num > 0) {
									$("#result").html(num);
								} else if (num == 0) {
									$.alert("活动未开始","");
								} else if (num == -1) {
									$.alert("活动未开始","");
								} else if (num == -2) {
									$.alert("活动已结束,请留意大屏幕中奖名单","");
								} else if (num == -3) {
									$.alert("用户不存在","");
								} else if (num == -4) {
									$.alert("异常用户","");
								} else if (num == -5) {
									$.alert("用户不符合活动要求","");
								}
							} else {
								$.alert(data,"");
							}
						},
						error : function(request) {
							$.alert("Connection error","");
						}
					});
				}
				last_x = x;
				last_y = y;
				last_z = z;
			}
		}

	});
</script>		
<%@include file="/WEB-INF/card/cardfile.jsp"%>