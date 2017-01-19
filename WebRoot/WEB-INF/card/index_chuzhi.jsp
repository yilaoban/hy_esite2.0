<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<link href="/css/mycards/index_chuzhi.css" rel="stylesheet" type="text/css" />
<!-- 储值 -->
<script type="text/javascript">
	$.post("/${oname}/user/findMyBalanceByHyUid.action",function(data){
		if(data){
			$(".yue0503").find("span").html(data.rmb/100);
			$('#myVip').attr("href","/${oname}/user/wxshow/" + data.pagedto.rmxid + "/wxn.html");
			$(".middle0503_2").html("").append(data.balanceSet.rmbrule);
			if(data.ruleList){
				$(".middle0503_1").empty();
				$.each(data.ruleList,function(i,n){
					var html = '<li><a href="/${oname}/user/saveBalancePay.action?ruleid='+n.id+'"><span><img src="/images/mycards/pic_2910.png" alt="">充值'+n.rmb/100+'元，送'+n.getrmb/100+'元<em>'+n.bilu+'折起</em></span><big></big></a></li>';
					$(".middle0503_1").append(html);
				});
			}
		}
	});
	
</script>
<%@include file="/WEB-INF/card/background.jsp"%>
<div class="yue0503">
	<div class="top"><a href="javascript:void(0);" onclick="window.history.back()" class="left" id="myVip"><img src="/images/shop/back.png" />&nbsp;会员中心</a>　<a href="javascript:void()" class="right"></a></div>
	<h2 style="margin-top:60px;">我的储值余额(元)<b>?</b></h2>
	<span>0.00</span>
</div>
<div class="middle0503">
<h3>储值有优惠</h3>
	<ul class="middle0503_1">
		<li><a href="#"><span><img src="/images/mycards/pic_2910.png" alt="">充值200元，送20元<em>9.1折起</em></span><big></big></a></li>
		<li><a href="#"><span><img src="/images/mycards/pic_2910.png" alt="">充值300元，送30元<em>9.1折起</em></span><big></big></a></li>
		<li><a href="#"><span><img src="/images/mycards/pic_2910.png" alt="">充值600元，送60元<em>9.1折起</em></span><big></big></a></li>
		<li><a href="#"><span><img src="/images/mycards/pic_2911.png" alt="">充值100元</span><big class="big_1"></big></a></li>
	</ul>
<h4>储值说明</h4>
    <ul class="middle0503_2">
    	<li>今度烘焙微生活可预存，预存享受返还现金奖励</li>
    	<li>预存200元，返20元</li>
    	<li>预存300元，返30元</li>
    	<li>预存600元，返60元</li>
    	<li>预存金额可在各门店消费使用</li>
    	<li>所以赠送额以储值的形式存至会员卡内。具体详情请咨询商家</li>
    </ul>
</div>
<div class="help0503"><em></em>
	 <ul>
	 	<li><h1>1.充值的支付方式有哪些？</h1><span>目前支持到店充值及微信在线充值。</span></li>
	 	<li><h1>2.充值后要多长时间到账？</h1><span>充值金额将立即到账，您可在【我的充值】中查看实时金额。</span></li>
	 	<li><h1>3.储值怎么使用？</h1><span>您在结账时向收银员出示会员卡即可使用储值。</span></li>
	 	<li><h1>4.怎么查询我的充值记录和使用记录？</h1>
	 	<span>您可在自定义菜单或【会员卡】页点击“我的账单”查看充值记录个消费使用的储值记录。</span></li>
	 	<li><h1>5.怎么查询我的充值记录和使用记录？</h1>
	 	<span>您可在自定义菜单或【会员卡】页点击“我的账单”查看充值记录个消费使用的储值记录。</span></li>
	 	<li><h1>6.怎么查询我的充值记录和使用记录？</h1>
	 	<span>您可在自定义菜单或【会员卡】页点击“我的账单”查看充值记录个消费使用的储值记录。</span></li>
	 	<li><h1>7.怎么保障我的储值使用安全？</h1>
	 	<span>您在充值是需填写您的手机号，每次使用储值将向该手机号发送验证码，为了您的资金安全，请妥善保管好您的手机。</span></li>
	 	<li><h1>8.怎么保障我的储值使用安全？</h1>
	 	<span>您在充值是需填写您的手机号，每次使用储值将向该手机号发送验证码，为了您的资金安全，请妥善保管好您的手机。</span></li>
	 	<li><h1>9.怎么保障我的储值使用安全？</h1>
	 	<span>您在充值是需填写您的手机号，每次使用储值将向该手机号发送验证码，为了您的资金安全，请妥善保管好您的手机。</span></li>
	 	<li><h1>1.充值的支付方式有哪些？</h1><span>目前支持到店充值及微信在线充值。</span></li>
	 	<li><h1>2.充值后要多长时间到账？</h1><span>充值金额将立即到账，您可在【我的充值】中查看实时金额。</span></li>
	 	<li><h1>3.储值怎么使用？</h1><span>您在结账时向收银员出示会员卡即可使用储值。</span></li>
	 	<li><h1>4.怎么查询我的充值记录和使用记录？</h1>
	 	<span>您可在自定义菜单或【会员卡】页点击“我的账单”查看充值记录个消费使用的储值记录。</span></li>
	 	<li><h1>5.怎么查询我的充值记录和使用记录？</h1>
	 	<span>您可在自定义菜单或【会员卡】页点击“我的账单”查看充值记录个消费使用的储值记录。</span></li>
	 	<li><h1>6.怎么查询我的充值记录和使用记录？</h1>
	 	<span>您可在自定义菜单或【会员卡】页点击“我的账单”查看充值记录个消费使用的储值记录。</span></li>
	 	<li><h1>7.怎么保障我的储值使用安全？</h1>
	 	<span>您在充值是需填写您的手机号，每次使用储值将向该手机号发送验证码，为了您的资金安全，请妥善保管好您的手机。</span></li>
	 	<li><h1>8.怎么保障我的储值使用安全？</h1>
	 	<span>您在充值是需填写您的手机号，每次使用储值将向该手机号发送验证码，为了您的资金安全，请妥善保管好您的手机。</span></li>
	 	<li><h1>9.怎么保障我的储值使用安全？</h1>
	 	<span>您在充值是需填写您的手机号，每次使用储值将向该手机号发送验证码，为了您的资金安全，请妥善保管好您的手机。</span></li>
	 </ul>
	 <div class="bottom" <s:if test='method=="E"'>style="position:absolute"</s:if>><a href="#">知道了</a></div>
</div>
<script type="text/javascript">
	$(".help0503").hide();
    $(".yue0503").click(function(){
             $(".help0503").show();
             $(".bottom a").click(function(){
             $(".help0503").hide();	
             })
  })
</script>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
