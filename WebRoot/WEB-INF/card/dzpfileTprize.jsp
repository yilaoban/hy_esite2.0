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
$(function (){
	var prize,hydesc,hyurl;
	var lurid=0;
	var bRotate = true;
	var lostDeg=[30,90,150,210,270,330];   //未中奖
	var prizeDeg=[0,120,240];		//大奖
	var rotateFn = function (angles,desc,num){
		$('#rotate').stopRotate();
		$('#rotate').rotate({
			angle:0,
			animateTo:angles+1800,
			duration:8000,
			callback:function (){
				if(num == 1){
					var html = $(".dialog_form").html();
					$(".dialog_form").empty();
					$.alert2(desc,"",function(){
						$.modal({
						  title:"",
						  text: html,
						  buttons: [
						    { text: "取消", className: "default", onClick: function(){$.closeModal(); $(".dialog_form").append(html)}},
						    { text: "确认", onClick: function(){ userCheck(html); }},
						  ],
						  autoClose: false
						});
					});
				}else{
					$.alert(desc,"");
					if(hyurl != null && hyurl != "" && hyurl != 0){
						setTimeout(function(){window.location.href=hyurl;},3000); 
					}
				}
				bRotate = true;
			}
		})
	};
	$('.pointer').click(function (){
		if(!bRotate)return;
		bRotate = false;
		var rid =$(this).parents(".block").attr("hydata");
		$.ajax({
			url:"/${oname}/user/lottery_draw.action",
			dataType:"json",
			data:{	"lid":'${obj.id}',
				"pageid":'${pageid}',
				"random":Math.random(),
				"relationid":rid
			},
			success:function(data){
				if(data != null){
					prize = data.status;
					hydesc = data.hydesc;
					hyurl = data.url;
					if(prize > -1){
						if(prize == 0){   //未中奖
							rotateFn(lostDeg[Math.floor(Math.random()*lostDeg.length+1)-1],"谢谢参与!",0);
						}else if(prize == 1){   //积分
							rotateFn(prizeDeg[data.lotteryPrize.price - 1],"恭喜获得"+data.lotteryPrize.jf+"积分",0);
						}else if(prize ==2){    //优惠券
							var sb = "";
							if(data.lotteryPrizeCode.code != null && data.lotteryPrizeCode.code != ""){
								sb += "<br>券号："+data.lotteryPrizeCode.code;
								if(data.lotteryPrizeCode.password != null && data.lotteryPrizeCode.password != ""){
									sb += "<br>密码："+data.lotteryPrizeCode.password;
								}
							}
							hydesc = "恭喜您中了"+data.lotteryPrize.name+sb;
							rotateFn(prizeDeg[data.lotteryPrize.price - 1],hydesc,0);
						}else if(prize ==3){    //实物
							lurid=data.lotteryUserRecordid;
							rotateFn(prizeDeg[data.lotteryPrize.price - 1],"恭喜您中了"+data.lotteryPrize.name,1);
						}else if(prize ==4){    //微信红包
							rotateFn(prizeDeg[data.lotteryPrize.price - 1],"恭喜你转到了"+data.lotteryPrize.hprice/100+"元红包",0);
						}else if(prize ==5){    //口令红包
							rotateFn(prizeDeg[data.lotteryPrize.price - 1],data.hydesc,0);
						}else if(prize ==6){    //自提商品
							rotateFn(prizeDeg[data.lotteryPrize.price - 1],"恭喜您中奖!请留意微信信息!",0);
						}
					}else{
						$.alert(data.hydesc);
						bRotate = true;
						return;
					}
				}
			}
		})
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
				$.alert('保存成功!','');
				if(hyurl != null && hyurl != "" && hyurl != 0){
					setTimeout(function(){window.location.href=hyurl;},3000); 
				}
			}
		});
	}
});

function hdCallBack(data,successAlert){
	if(successAlert!="Y"||data.status!=1){
		$.alert(data.hydesc,"");
	}
	
	if(data.url!=null&&data.url!=""){
		window.location.href=data.url;
	}else if(data.id!=0){
		window.location.href="/${oname}/user/show/"+data.id+".html";
	}
}

$(".zjjl").click(function(){
	var lid = '${obj.id}';
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