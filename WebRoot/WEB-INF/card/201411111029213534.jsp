<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 产品列表1 -->
<link href="/css/liebiao/14/list.css" rel="stylesheet" type="text/css" />

<%@include file="/WEB-INF/card/background.jsp"%>
<div class="wdy_body">
<div class="wdy_top2_cate" >
	<s:if test="blocks[1].list.size >0">
		<s:iterator status='st' value='blocks[1].list' var='item'>
			<a href="javascript:void(0)" class="loadproduct" ccid="${item.id }" pageid=${item.pageid }>${item.name}</a>
	    </s:iterator>
	</s:if>
	<s:else>
		<a class="active" href="#">热销菜品</a><a href="#">圣诞新品</a><a href="#">咖啡</a><a href="#">茶饮</a><a href="#">果蔬</a>
	</s:else>
</div>

<s:if test='blocks[1].display=="Y"'>
<div  hyblk="I" class="liebiao_141110_1_box block clearfix ${blocks[1].ctname}" status="0" hyct="Y" hydata="${blocks[1].rid}" style="<%-- ${blocks[1].hyct}; --%>" class_data="hy2015050728306">
	<ul  class="liebiao_content">
		<s:if test='blocks[1].list.size == 0'>
			<li >
		       <div class="liebiao_141110_1_b_img"><img src="/images/liebiao/14/sample.png" width="100%" height="100%" hyvar="img" hydesc="200*200"/></div>
		       <p class="liebiao_141110_1_b_tiaoti" hyvar="name" >腐竹白果薏米竹白果薏米竹白果薏米糖水</p>
		       <p class="liebiao_141110_1_b_j" hyvar="desc">白果和薏米都有清热去薏米都有清热去薏米都有清热去薏米薏米糖水吃... </p>
		       <p class="liebiao_141110_1_jg">￥74.0<span>/普通装</span></p>
		       <a href="#"><img class="addCart" src="/images/liebiao/14/cart.png"></a>
		     </li>
		     <li >
		       <div class="liebiao_141110_1_b_img" ><img src="/images/liebiao/14/sample.png" width="auto\9" height="auto" style="max-width: 100%;" hyvar="img" hydesc="200*200"/></div>
		       <p class="liebiao_141110_1_b_tiaoti" hyvar="name" >莲子百合红豆沙</p>
		       <p class="liebiao_141110_1_b_j" hyvar="desc" >百合能补中益气，温肺止咳;鲜品有镇咳、止慢性支气管炎、疗肺病止咯血。 </p>
		       <p class="liebiao_141110_1_jg">￥74.0<span>/普通装</span></p>
		       <a href="#"><img class="addCart" src="/images/liebiao/14/cart.png"></a>
		     </li>
		     <li >
		       <div class="liebiao_141110_1_b_img" ><img src="/images/liebiao/14/sample.png" width="auto\9" height="auto" style="max-width: 100%;" hyvar="img" hydesc="200*200"/></div>
		       <p class="liebiao_141110_1_b_tiaoti" hyvar="name" >水果西米露</p>
		       <p class="liebiao_141110_1_b_j" hyvar="desc" >西米、牛奶(或者椰奶)、水果(草莓、西瓜、苹果或者你喜欢的水果)。 </p>
		       <p class="liebiao_141110_1_jg">￥74.0<span>/普通装</span></p>
		       <a href="#"><img class="addCart" src="/images/liebiao/14/cart.png"></a>
		     </li>
	    </s:if>
	</ul>
	<div style="display: none" class="jzgd">
		加载更多↓↓
	</div>
</div>
</s:if> 
<div class="wdy_bottom2_fixedmenu block ${blocks[2].ctname}" status="0" hyct="Y" hydata="${blocks[2].rid}"  style="display:none;">
	<ul>
		<li class="active"><a href="${blocks[2].link}"><span class="home"></span>${blocks[2].title}</a></li>
		<li class=""><a href="#"><span class="cart"></span>购物车</a></li>
		<li class=""><a href="#"><span class="order"></span>我的订单</a></li>
	</ul>
</div>
</div>
<script type="text/javascript">
var start = 1;
$(document).ready(function(){
	$(".loadproduct").click(function(){
		var ccid = $(this).attr("ccid");
		$(".liebiao_content").empty();
		$(".jzgd").hide();
		start = 1;
		load(ccid);
	});
	var id = '${blocks[1].list[0].id}';
	if(id>0){
		load(id);
	}
	$(".jzgd").click(function(){
		var ccid = $(".active").attr("ccid");
		load(ccid);
	});
	
});
function load(ccid){
	$(".wdy_top2_cate .active").removeClass("active");
	$("[ccid='"+ccid+"']").addClass("active");
	$.post("/${oname}/user/loadcontent.action",{"ccid":ccid,"pageId":start,"size":3},function(data){
		$.each(data.product,function(index,value){
			var linkurl;
			if(value.linkurl != ""){
				linkurl = value.linkurl;
			}else{
				var pageid = '${blocks[1].pageid}';
				linkurl = "/${oname}/user/show/"+pageid+"/pcn/ctt-hy-"+value.id+".html";
			}
			var imgurl = "";
			if(value.simgurl){
				imgurl = '${imgDomain}'+value.simgurl;
			}
			var tag = "";
			if(value.tag){
				tag = value.tag;
			}
			var html = '<li ><div class="liebiao_141110_1_b_img"><a href="'+linkurl+'"><img src="'+imgurl+'" width="100%" height="100%"/></a></div>'
		    +'<p class="liebiao_141110_1_b_tiaoti" >'+value.name+'</p>'
		    +'<span class="liebiao_141110_1_b_j">'+tag+'</span>'
		    +'<p class="liebiao_141110_1_jg">￥'+value.salesprice+'<span>/<del>￥'+value.price+'</del></span></p>'
		    +'<a href="javascript:void(0);" onclick="addShoppingCart('+value.id+')"><img class="addCart" src="/images/liebiao/14/cart.png"></a></li>';
			$(".liebiao_content").append(html);
		});
		if(data.product.length >= 3){
			start++;
			$(".jzgd").show();
		}else{
			$(".jzgd").hide();
		}
	});
}

</script>
<%@include file="/WEB-INF/card/cardfile.jsp"%>