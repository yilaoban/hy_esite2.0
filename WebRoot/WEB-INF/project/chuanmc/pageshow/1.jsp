<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns:wb="http://open.weibo.com/wb">
	<head>
		<title>Clement Chabernaud</title>
		<script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js" type="text/javascript" charset="utf-8"></script>
		<s:if test='userAgent =="C"'>
			<script src="http://tjs.sjs.sinajs.cn/open/thirdpart/js/frame/appclient.js" charset="utf-8"></script>
		</s:if>
		<s:else>
			<script src="http://tjs.sjs.sinajs.cn/open/thirdpart/js/pageapp/mobile/jsapi.js" charset="utf-8"></script>
		</s:else>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="" />
		<meta http-equiv="description" content="" />
		<link href="/css/esite.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="/js/jquery-1.8.2.min.js"></script>
		<script type="text/javascript" src="/js/esite.js"></script>
		<script type="text/javascript" src="/js/jquery.popImage.js"></script>
		<script type="text/javascript" src="/js/jcarousellite.min.js"></script>
		<%-- 
		<script type="text/javascript">
		function ShowCountDown(day,hour,minute,second) { 
				var nowTime1970 = new Date().getTime();
				var startTime1970 = new Date("2014/02/07 00:00:00").getTime();
				var laterTime1970 = new Date("2014/02/07 17:00:00").getTime();
				var day = day%7;
				var dayNow = new Date().getDay();
				var hourNow = new Date().getHours(); 
				var minuteNow = new Date().getMinutes(); 
				var secondNow = new Date().getSeconds(); 
				var timeNow = dayNow*24*60*60*1000+hourNow*60*60*1000+minuteNow*60*1000+secondNow*1000;
				var time = day*24*60*60*1000+hour*60*60*1000+minute*60*1000+second*1000;
				var timeLeft = time - timeNow;
				if (timeLeft < 0) {
					timeLeft = timeLeft + 7*24*60*60*1000; 
				} 
				var secondLeft = parseInt(timeLeft/1000);
				var dayLeft = Math.floor(secondLeft/(24*60*60));
				var hourLeft = Math.floor(secondLeft%(24*60*60)/(60*60));
				var minuteLeft = Math.floor(secondLeft%(60*60)/60);
				var secondLeft = Math.floor(secondLeft%60);
				if (secondLeft < 10){
					$("#second1").text("0"); 
					$("#second2").text(secondLeft); 
				} else {
					var second2 = secondLeft%10;
					var second1 = Math.floor(secondLeft/10)
					$("#second1").text(second1); 
					$("#second2").text(second2); 
				}
				if (minuteLeft < 10){
					$("#minute1").text("0"); 
					$("#minute2").text(minuteLeft); 
				} else {
					var minute2 = minuteLeft%10;
					var minute1 = Math.floor(minuteLeft/10)
					$("#minute1").text(minute1); 
					$("#minute2").text(minute2); 
				}
				if (hourLeft < 10){
					$("#hour1").text("0"); 
					$("#hour2").text(hourLeft); 
				} else {
					var hour2 = hourLeft%10;
					var hour1 = Math.floor(hourLeft/10)
					$("#hour1").text(hour1); 
					$("#hour2").text(hour2); 
				}
				if( nowTime1970 < startTime1970 ){  /*活动还未开始*/
					var cha = startTime1970 - nowTime1970;
						cha = parseInt(cha/1000);
						dayLeft = Math.floor(cha/(24*60*60));
					$("#prizetext").text("距离活动开始时间");
				} else if(nowTime1970 > startTime1970 && nowTime1970 < laterTime1970) {  /*活动开始不在抽奖期*/
					dayLeft = dayLeft + 7;
					$("#prizetext").text("距离下次开奖时间");
				} else {
					$("#prizetext").text("距离下次开奖时间");
				}
				if (dayLeft < 10){
					$("#day1").text("0"); 
					$("#day2").text(dayLeft); 
				} else {
					var day2 = dayLeft%10;
					var day1 = Math.floor(dayLeft/10)
					$("#day1").text(day1); 
					$("#day2").text(day2); 
				}
			} 
		window.setInterval(function(){ShowCountDown(${result["1"].notice.countdownWeekday},${result["1"].notice.hour},${result["1"].notice.minute},${result["1"].notice.second});}, 1000); 
		</script> 
		--%>
	</head>
	<body>
		<%-- 
		<div class="imgblock" style="text-align:center;">
			<div style="border:1px solid #ccc;padding:20px;text-align:center;">
				<div style="font-size:26px;font-weight:bold;"><img src="/images/test1/title.png" /></div>
				<div style="margin:20px 10px 0;text-align:left;line-height:22px;">${result['1'].notice.content}</div>
				<div style="font-size:24px;font-weight:bold;padding:20px 0;" id="prizetext">全部获奖名单</div>
				<s:if test="result['8'].flist.size > 1 ">
				<div style="margin-top:5px;font-size:12px;color:#888;">
				<a href="${imgDomain }${result['8'].flist[0].imgurl}" rel="gallery"  class="image_gall" style="color:red;font-weight:bold;">英伦之旅最终大奖</a>
				<s:iterator value="result[8].flist" var="f" begin="1">
					<a href="${imgDomain }${f.imgurl}" rel="gallery"  class="image_gall" style="color:#888">${f.title }</a>
				</s:iterator>
				</div>
				</s:if>
			</div>
		</div>
		<div class="imgblock">
			<div style="position:relative;border:1px solid #ccc;padding:20px 45px;text-align:center;margin-top:30px;">
				<a href="javascript:void(0);" class="prev1"></a>
				<a href="javascript:void(0);" class="next1"></a>
				<div class="jCarousel">
				    <ul>
		    			<s:iterator value='result["2"].recordlist' var="r">
				        <li><a href="${imgDomain }${r.bigimg }" rel="gallery"  class="image_gall"><img src="${imgDomain }${r.bigimg }" alt="${r.content }" width="134" height="134" /></a></li>
					    </s:iterator>
				    </ul>
				</div>
			</div>
		</div>
		--%>
		<div class="imgblock">
			<div style="position:relative;text-align:center;">
				<a href="javascript:void(0);" class="prev1"></a>
				<a href="javascript:void(0);" class="next1"></a>
				<div class="jCarousel">
				    <ul>
		    			<s:iterator value='result["9"].flist' var="r">
				        <li style="padding:0;"><img src="${imgDomain }${r.imgurl }" /></li>
					    </s:iterator>
				    </ul>
				</div>
			</div>
		</div>
		<div class="imgblock" style="margin-top: 30px;">
			<div>
				<ul class="lst">
					<li class="tabs active">
						<a href="javascript:void(0);" onclick="showTab(1)">Clement动向</a>
					</li>
					<li class="tabs">
						<a href="javascript:void(0);" onclick="showTab(2)">Clement大片</a>
					</li>
					<li class="tabs">
						<a href="javascript:void(0);" onclick="showTab(3)">粉丝专区</a>
					</li>
					<li class="tabs">
						<a href="javascript:void(0);" onclick="showTab(4)">活动专区</a>
					</li>
					<p style="clear: both"></p>
				</ul>
			</div>
			<div style="height: 12px; background: #000"></div>
			<div
				style="border-width: 0 1px 1px 1px; border-style: solid; border-color: #ccc">
				<div id="tab1" class="tab_content" style="padding: 20px">
					<ul class="pst">
						<li>
							<div style="float:left;text-indent:30px;width:340px;line-height:28px;font-size:16px;padding:20px 0 0 20px;font-family:宋体;">${result['3'].notice.content}</div>
							<img src="${imgDomain }${result['3'].notice.imgurl}" width="380px" style="margin-left:50px;margin-top:25px;"/>
						</li>
						<li style="display:none;">
							<div style="text-align:center;"><img src="${imgDomain }${result['7'].notice.imgurl}" /></div>
							<div style="line-height:28px;font-size:16px;padding:20px 0 0 20px;text-indent:30px;text-align:left;">${result['7'].notice.content}</div>
						</li>
						<p style="clear: both;padding-bottom:20px;"></p>
					</ul>
					<p style="text-align:center;"><a class="t_prev" href="javascript:void(0);" onclick="fanye(1,'p',this)">上一页</a><a class="t_next" href="javascript:void(0);" onclick="fanye(1,'n',this)">下一页</a></p>
				</div>
				<div id="tab2" class="tab_content" style="padding: 20px;display:none;">
					<ul class="mst">
						<s:iterator value='result["4"].prolist' var="prolist" status="proliststatus">
								<s:iterator value='result["4"].product[#proliststatus.index]' var="p" status="listatus">
								<li <s:if test='#listatus.count > 9'>style="display:none"</s:if>>
									<a href="${imgDomain }${p.product.bimgurl }" rel="gallery"  class="image_gall"><img id="${p.id}" src="" data-original="${imgDomain }${p.product.simgurl }" width="255px" height="255px"/></a>
									<p class="share">
										<a href="javascript:void(0);" onclick="zan(${pageid},103,${p.id},this)"><img src="/images/test1/zan.png" /><span id="lab_zan_${p.id }">(${p.zantotal })</span></a>
										<a target="_blank" href="http://service.weibo.com/share/share.php?title=%23David+Gandy%23SELECTED+2014%D0%C2%D0%CE%CF%F3%D3%C9%C8%AB%C7%F2%B3%AC%C4%A3David+Gandy%CF%C8%C9%FA%C7%E3%C7%E9%D1%DD%D2%EF&appkey=1s78WX&pic=${imgDomain }${p.product.simgurl }&ralateUid=2012583242&language=zh_cn&url=http%3A%2F%2Fweibo.com%2Fp%2F1006062012583242%2Fapp404345799"><img src="/images/test1/wb.png" /><span>分享</span></a>
										<input type="hidden" value="${p.id }" name="zantotal"/>
									</p>
								</li>
								</s:iterator>
						</s:iterator>
						<p style="clear: both"></p>
					</ul>
					<p style="text-align:center;"><a class="t_prev" href="javascript:void(0);" onclick="fanye(9,'p',this)">上一页</a><a class="t_next" href="javascript:void(0);" onclick="fanye(9,'n',this)">下一页</a></p>
				</div>
				<div id="tab3" class="tab_content" style="display:none;">
					<wb:topic uid="2012583242" column="y" border="n" height="900" tags="Clement Chabernaud" language="zh_cn" version="base" appkey="5lwRTq" footbar="y" filter="n" ></wb:topic>
				</div>
				<div id="tab4" class="tab_content"  style="padding: 20px;display:none;">
						<p style="text-align:center;padding:20px 0 40px;font-size:18px;font-weight:bold;">活动已结束</p>
					<ul class="mst">
					<!--  -->
						<s:iterator value="result[6].record" var="record" status="listatus">
						<li <s:if test='#listatus.count > 9'>style="display:none"</s:if>>
						<a href="${imgDomain }${record.bigimg }" rel="gallery"  class="image_gall"><img src="" data-original="${imgDomain }${record.bigimg }" width="255px" height="255px" /></a>
						</li>
						</s:iterator>
						<p style="clear: both"></p>
					</ul>
					<p style="text-align:center;"><a class="t_prev" href="javascript:void(0);" onclick="fanye(9,'p',this)">上一页</a><a class="t_next" href="javascript:void(0);" onclick="fanye(9,'n',this)">下一页</a></p>
				</div>
			</div>
		</div>
		<div class="support">
			技术支持：<a target="_blank" href="http://www.huiyee.com/">会易科技</a>
		</div>
		<script type="text/javascript">
			$(function(){
			    $(".jCarousel").jCarouselLite({
			        btnNext: ".next1",
			        btnPrev: ".prev1",
			        visible: 1,
			        circular: true,
					scroll: 1
			    });
			    $("a.image_gall").popImage();
			});
		</script>
	</body>
</html>