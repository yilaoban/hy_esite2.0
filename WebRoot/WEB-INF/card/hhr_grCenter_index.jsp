<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 个人中心-首页 -->
<link href="/css/cb/partner.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
	$(document).ready(function(){
		$.post("/${oname}/user/cbUserCenter.action",function(data){
			if(data.cbSender){
				$('#total').html(data.cbSender.hbtotal/100 + "元");
				$('#unused_').val((data.cbSender.hbtotal - data.cbSender.hbused)/100);
				$('#unused').html((data.cbSender.hbtotal - data.cbSender.hbused)/100 + "元");
				$('#used').html(data.cbSender.hbused/100 + "元");
			}
		});
	});
	
	function tixian(){
		var rmb = parseFloat($('#rmb').val().trim());
		var unused = parseFloat($('#unused_').val());
		if(rmb > unused){
			$(".bg0526_3").hide();
           	$(".tk0527").hide();
			$.toast("可提取佣金不足", "cancel");
			return;
		}
		if(rmb < 1){
			$(".bg0526_3").hide();
           	$(".tk0527").hide();
			$.toast("提现金额少于1元", "cancel");
			return;
		}
		$.post("/${oname}/user/cbtixian.action",{"rmb":rmb * 100},function(data){
			if(data > 0){
				$.toast("操作成功");
				$(".bg0526_3").hide();
            	$(".tk0527").hide();
			}
		});
	}
	
</script>
<div class="top0608">
	<div class="touxiang"><img src="${sessionScope.visitUser.wxUser.headimgurl}" alt=""></div>
	<p class="name">${sessionScope.visitUser.wxUser.nickname}</p>
</div>
<div class="top0608_2">
	<div class="zs">
	<ul>
		<li><p>总佣金</p><p><span id="total">0元</span></p></li>
		<input type="hidden" id="unused_" value="0">
		<li><p>可提取佣金</p><p><span id="unused">0元</span></p></li>
		<li><p>已提取佣金</p><p><span id="used">0元</span></p></li>
	</ul>
	</div>
</div>
<div class="main0526_2 block" style="${blocks[1].hyct}" status="0" hyct="Y" hydata="${blocks[1].rid}">
	<ul>
		<li><a href="${blocks[1].link1}">积分明细</a></li>
		<li><a href="${blocks[1].link2}">佣金明细</a></li>
		<li><a href="${blocks[1].link3}">提现记录</a></li>
		<li><a href="${blocks[1].link4}">我的伙伴</a></li>
	</ul>
	<div class="tixian0527"><span>申请提现</span></div>
</div>
<div class="bottom0526 block" status="0" hyct="Y" hydata="${blocks[0].rid}" <s:if test='method != "E"'>style="position:fixed;"</s:if>>
	<ul>
		<li class="wxz1"><a href="${blocks[0].link1}"><span>${blocks[0].title1}</span></a></li>
		<li class="xz2"><a href="${blocks[0].link2}"><span>${blocks[0].title2}</span></a></li>
		<li class="wxz3"><a href="${blocks[0].link3}"><span>${blocks[0].title3}</span></a></li>
	</ul>
</div>
<div class="bg0526_3" style="display: none;"></div>
<div class="tk0527" style="display: none;">
    <i></i>
	<p class="tk_1">提现方式：<span>微信红包</span></p>
    <p class="tk_1">请输入提现金额：</p>
    <form action="">
    	<p class="tk_2"><input type="text" placeholder="不低于一元" id="rmb" onkeyup="if(this.value==this.value2)return;if(this.value.search(/^\d*(?:\.\d{0,2})?$/)==-1)this.value=(this.value2)?this.value2:'';else this.value2=this.value;">元</p>
    	<p class="tk_3"><input type="button" value="提交" onclick="tixian()"></p>
    </form>
</div>
<script>
$(".bg0526_3").hide();
$(".tk0527").hide();
$(".tixian0527 span").click(function(){
	$(".bg0526_3").show();
	$(".tk0527").show();
	$(".tk0527 i").click(function(){
		$(".bg0526_3").hide();
          	$(".tk0527").hide();	
	})
})


</script>
<%@include file="/WEB-INF/card/cardfile.jsp"%>