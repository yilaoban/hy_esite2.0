<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html >
   <head lang="en">
	    <meta charset="UTF-8">
	    <meta content="width=320, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" name="viewport" />
		<title>我的卡券</title>
		<script type="text/javascript" src="/js/qrcode.js"></script>
		<link href="/css/shop/myorders2.css" rel="stylesheet" type="text/css">
		<link href="/css/shop/ydyx.css" rel="stylesheet" type="text/css">
		<link href="/css/reset.css" rel="stylesheet" type="text/css" />
		<link href="/css/mycards/index_wu1.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
		<link href="https://res.wx.qq.com/open/libs/weui/0.4.0/weui.css" rel="stylesheet"/>
		<link href="/js/jquery-weui/css/jquery-weui.css" rel="stylesheet"/>
		<script src="/js/jquery-weui/js/jquery-weui.js" type="text/javascript"></script>
</head>
<body>
	<div class="top">
			<a href="javascript:void(0);" onclick="window.history.back()" class="left">
			<img src="/images/shop/back.png" />&nbsp;返回</a>我的卡券　
		<a href="#" class="right"></a>
	</div>
	<div style="width:100%;height:33px;"></div>
	
	<s:if test="dto.payOrderList.size() > 0">
	<div class="main">
		<ul id="cont">
		<s:iterator value="dto.payOrderList" var="d">
			<s:iterator value="#d.goods" var="g">
		<li>
			<ul class="list">
					<li>
		            	<s:if test="#g.payApt != null">
							<a href="javascript:void(0);"><img src="${imgDomain }${g.payApt.img}"></a>
						</s:if>
						<s:else>
		                  	<a href="javascript:void(0);"><img src="${imgDomain }${g.productsimg}"></a>
						</s:else>
	               	</li>
					<div class="pname">
						${g.productname }
					</div>
					<s:if test='type == 1'>
						<p class="total"><span>${d.createtimeStr}</span>实付款：<i>￥${g.price/100}</i></p>
					 </s:if>
					 <s:else>
						<p class="total"><span>${d.createtimeStr}</span>实付款：<i>${g.price}积分</i></p>
					 </s:else>
					<p class="second">
		             	<s:if test='#g.used != "Y"'>
			             	<a href="javascript:void(0);" onclick="showOrderUrl(${g.id })" class="use" id="qsy${g.id}">去使用</a>
			 			</s:if>
			 			<s:elseif test='#g.used == "Y" '>
				 			<a href="javascript:void(0);" class="used">已使用</a>
				 		</s:elseif>
		             </p>
			</ul>
		</li>
			</s:iterator>
		</s:iterator>
		</ul>
	</div>
	</s:if>
	<s:else>
		<div class="wu0503_1">
			<span><img src="/images/mycards/pic_2921.png"></span><span>暂时没有卡券哦!</span>
		</div>
	</s:else>
	<script type="text/javascript">
	  	function showOrderUrl(poid){
	  				$.showLoading();
	  		$.post("/${oname}/user/showOrderUrl.action",{"poid":poid},function(data){
	  			if(data.status == 1){
	  				qrcode.makeCode(data.url);
			  		setTimeout(function() {
			          $.hideLoading();
		  				erweima(poid);
			        }, 1500);
	               	$("#qrcode img").css("margin","0 auto");
	  			}else{
	  				$.alert(data.hydesc);
	  			}
	  		});
	  	
	  	}
	  	var run = false;
	  	function erweima(gid){
	  		  $.modal({
		          title: "扫描二维码",
		          text: $('#qrcode').html(),
		          buttons: [
		            { text: "取消", className: "default",onClick: function(){ run = false }},
		          ]
		        });
	  		  run = true;
	  		  check(gid);
	  	}
	  	
	  	function check(gid){
	  		if(!run) return;
	  		$.post("/${oname}/user/checkused.action",{"gid":gid},function(data){
	  			if(data == "Y"){
	  				run = false;
	  				$.closeModal();
	 				$("#qsy"+gid).unbind("click").removeClass("use").addClass("used").text("已使用");
	  				$.toast("扫描成功！")
	  			}else{
		  			setTimeout("check("+gid+")",1000);
	  			}
	  		});
	  	}
	  	
	  	
	        var generatedCount = 2;
	        var url="/${oname}/user/ajaxShowKQList.action";
	        function load1() {
	             	$.post(url,{"pageId":generatedCount},function(data){
			    		if(data){
			    			if(data.payOrderList){
								$.each( data.payOrderList, function(i, n){
									$.each(n.goods,function(i2,n2){
									var html = '<li>';
											html += '<ul class="list"><li><a href="javascript:void(0);">';
										if(n2.payApt != null){
											html += '<img src="${imgDomain}'+n2.payApt.img+'"></a></li>';
										}else{
											html += '<img src="${imgDomain }'+n2.productsimg+'"></a></li>';
										}
										html += '<div class="pname">'+n2.productname+'</div>';
										if(n2.type == 1){
											html += '<p class="total"><span>'+n.createtimeStr+'</span>实付款：<i>￥'+n2.price/100+'</i></p>';
										}else{
											html += '<p class="total"><span>'+n.createtimeStr+'</span>实付款：<i>'+n2.price+'积分</i></p>';
										}
										html += '<p class="second">';
										if(n2.used == "Y"){
											html += '<a href="javascript:void(0);" onclick="showOrderUrl('+n2.id+')" class="use">去使用</a>';
										}else{
											html += '<a href="javascript:void(0);" class="used">已使用</a>';
										}
										html += '</p></ul></li>';
										$("#cont").append(html);
									});
								});
			                	++generatedCount;
			    			}
			    		}
				   });
	               
	        }
	        
			var loading = false;
	        $(document.body).infinite().on("infinite", function() {
	          if(loading) return;
	          loading = true;
	          setTimeout(function() {
	           load1();
	            loading = false;
	          }, 1000);
	        });
	  	
	</script>
	
	<div id="qrcode" style="display: none;" ></div>
	
	
	<script type="text/javascript">
	var qrcode = new QRCode(document.getElementById("qrcode"), {
		width : 200,
		height : 200
	});
	</script>
</body>
</html>