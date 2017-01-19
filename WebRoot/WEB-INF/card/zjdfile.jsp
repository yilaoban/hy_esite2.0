<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>

<div class="dialog_form" style="display:none;">
	<table class="info">
		<tr><td style="width:50px;">姓名</td><td><input class="weui-prompt-input" type="text" style="width:90%" id="username"/></td></tr>
		<tr><td style="width:50px;">电话</td><td><input id="userphone" class="weui-prompt-input" type="tel" style="width:90%" /></td></tr>
		<tr><td style="width:50px;">地址</td><td><input class="weui-prompt-input" type="text" style="width:90%" id="userlocation" ></td></tr>
	</table>
</div>

<script type="text/javascript">
var hyurl;
var lurid=0;
$(document).ready(function() {
	var audioElement = document.createElement('audio');
	audioElement.setAttribute('src', 'http://img.huiyee.com/egg/tada.wav');
	
	$(".eggList li").click(function() {
		$(this).children("span").hide();
		eggClick($(this));
	});
	
	function eggClick(obj) {
		var _this = obj;
		var rid = $(obj).parents(".block").attr("hydata");
		if(_this.hasClass("curr")){
			$.alert("蛋都碎了，别砸了！","");
			return false;
		}
		
		$.ajax({
			url:"/${oname}/user/lottery_draw.action",
			type: "post",
			dataType:"json",
			data:{
				"lid":'${obj.id}',
				"pageid":'${pageid}',
				"random":Math.random(),
				"relationid":rid
			},
			cache: false,
            async: false,
            success: function(data) {
            	if(data==null || data==-10000){
            		return; 
            	}
            	
            	var status = data.status;
            	var hydesc = data.hydesc;
            	lurid = data.lotteryUserRecordid;
            	hyurl = data.url;
            	var hyfurl = data.furl;
            	if(status<0){
            		$.alert(hydesc,"");
            		if(hyfurl != null && hyfurl != ""){
						window.location.href=hyfurl;
					}else if(hyurl != null && hyurl != ""){
						window.location.href=hyurl;
					}
            		return;
            	}
            	
				audioElement.play();
				_this.addClass("curr"); //蛋碎效果
            	$(".hammer").css({"left":_this.position().left+66 });
				if(status==0){
					$.alert("亲，继续努力哦!","");
					if(hyurl != null && hyurl != ""){
						window.location.href=hyurl;
					}
				}else if(status==1){
					$.alert("恭喜您中了"+data.lotteryPrize.jf+"积分","",function(){
						if(hyurl != null && hyurl != ""){
							window.location.href=hyurl;
						}else{
							$("#a_"+'${pcid}').load('/${oname}/user/showCard.action?pcid='+'${pcid}'+"&pageid="+'${pageid}'+'&jspstyle='+'${page.jspstyle}');
						}
					});
				}else if(status==2){
					var sb = "";
					if(data.lotteryPrizeCode.code != null && data.lotteryPrizeCode.code != ""){
						sb += "<br>券号："+data.lotteryPrizeCode.code;
					}
					if(data.lotteryPrizeCode.password != null && data.lotteryPrizeCode.password != ""){
						sb += "<br>密码："+data.lotteryPrizeCode.password;
					}
					$.alert("恭喜您中了"+data.lotteryPrize.name+sb,"",function(){
						if(hyurl != null && hyurl != ""){
							window.location.href=hyurl;
						}else{
							$("#a_"+'${pcid}').load('/${oname}/user/showCard.action?pcid='+'${pcid}'+"&pageid="+'${pageid}'+'&jspstyle='+'${page.jspstyle}');
						}
					});
				}else if(status==3){
					var html = $(".dialog_form").html();
					$(".dialog_form").empty();
					$.alert("恭喜您中了"+data.lotteryPrize.name,"",function(){
						$.modal({
						  title:"",
						  text: html,
						  buttons: [
						    { text: "取消", className: "default", onClick: function(){$.closeModal(); $(".dialog_form").append(html);$("#a_"+'${pcid}').load('/${oname}/user/showCard.action?pcid='+'${pcid}'+"&pageid="+'${pageid}'+'&jspstyle='+'${page.jspstyle}');}},
						    { text: "确认", onClick: function(){ userCheck(html); }},
						  ],
						  autoClose: false
						});
					});
				}else if(status ==4){    //微信红包
					$.alert("恭喜您砸到了"+data.lotteryPrize.hprice/100+"元红包","",function(){
						if(hyurl != null && hyurl != ""){
							window.location.href=hyurl;
						}else{
							$("#a_"+'${pcid}').load('/${oname}/user/showCard.action?pcid='+'${pcid}'+"&pageid="+'${pageid}'+'&jspstyle='+'${page.jspstyle}');
						}
					});
				}else if(status ==5){    //微信红包
					$.alert(data.hydesc,"",function(){
						if(hyurl != null && hyurl != ""){
							window.location.href=hyurl;
						}else{
							$("#a_"+'${pcid}').load('/${oname}/user/showCard.action?pcid='+'${pcid}'+"&pageid="+'${pageid}'+'&jspstyle='+'${page.jspstyle}');
						}
					});
				}else if(status ==6){    //微信红包
					$.alert("恭喜您中奖!请留意微信信息!","",function(){
						if(hyurl != null && hyurl != ""){
							window.location.href=hyurl;
						}else{
							$("#a_"+'${pcid}').load('/${oname}/user/showCard.action?pcid='+'${pcid}'+"&pageid="+'${pageid}'+'&jspstyle='+'${page.jspstyle}');
						}
					});
				}
				
            },
            error: function(request) {
				console.log("Connection error");
            }
		});
	}
	
});

function userCheck(html){  //实物保存
	var username = $("#username").val();
	var userphone = $("#userphone").val();
	var userlocation = $("#userlocation").val();
	if(userphone == ""){
		$("#userphone").css("border-color","red");
		return false;
	}
	$.closeModal();
	$(".dialog_form").append(html);
	$.post("/${oname}/user/zhuanpan_winner.action",{"dto.lotteryUser.lurid":lurid,"dto.lotteryUser.username":username,"dto.lotteryUser.userphone":userphone,"dto.lotteryUser.userlocation":userlocation},function(data){
		if(data=="Y"){
			$.alert('保存成功!','',function(){
				if(hyurl != null && hyurl != ""){
					window.location.href=hyurl;
				}else{
					$("#a_"+'${pcid}').load('/${oname}/user/showCard.action?pcid='+'${pcid}'+"&pageid="+'${pageid}'+'&jspstyle='+'${page.jspstyle}');
				}
			});
		}
	});
}
		
		
$(".zjjl").click(function(){
	var lid = '${blocks[0].obj.id}';
	if(lid>0){
		$.post("/${oname}/user/lottery_prize_winner.action",{"lid":lid,"pageid":'${pageid}'},function(data){
		var html = "";
		 for(var i=0; i<data.length; i++){
			if(data[i].status == 3){
				html +=  "<div>恭喜您获得"+data[i].name + ";</div>";
			}else if(data[i].status == 2){
				html += "<div>恭喜您获得"+data[i].name +"<br/>券号:" +data[i].code + "<br/>密码："+data[i].password +"</div>";
			}else if(data[i].status == 1){
				html += "<div>恭喜您获得"+data[i].jf + "积分;</div>";
			}
			else if(data[i].status == 4){
				html += "<div>恭喜您获得微信红包："+data[i].jf + "元;</div>";
			}
		 }
		 $(".z1tabcontent3").html(html);
		});
	}
});
</script>
