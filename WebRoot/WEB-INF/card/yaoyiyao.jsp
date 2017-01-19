<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 摇一摇 -->
<link rel="stylesheet" href="/css/hudong/yaoyiyao/index.css" type="text/css"></link>
<script src="/css/hudong/yaoyiyao/shake.js"></script>
<%@include file="/WEB-INF/card/background.jsp"%>

<s:if test='blocks[0].display=="Y"'>
  	<div class="yaoyiyao_141118_center block"  status="0" style="${blocks[0].hyct }" hydata="${blocks[0].rid}"><img src="/images/hudong/yaoyiyao/2_03.png"  width="100%" height="100%" hyvar="img" hydesc="285*285" class="hand-animate"/></div>
	<s:set name="obj" value="blocks[0].obj" />
</s:if>

<div class="dialog_form" style="display:none;">
	<table class="info">
		<tr><td style="width:50px;">姓名</td><td><input class="weui-prompt-input" type="text" style="width:90%" id="username"/></td></tr>
		<tr><td style="width:50px;">电话</td><td><input id="userphone" class="weui-prompt-input" type="tel" style="width:90%" /></td></tr>
		<tr><td style="width:50px;">地址</td><td><input class="weui-prompt-input" type="text" style="width:90%" id="userlocation" ></td></tr>
	</table>
</div>
<script type="text/javascript">
var isyyy=true;
var lurid;
$(document).ready(function() {
	var oid='${blocks[0].obj.id}';
	if(oid<1){
        return;
    }
	var audioElement = document.createElement('audio');
	audioElement.setAttribute('src', 'http://img.huiyee.com/egg/yaoyiyao.wav');
      var myShakeEvent = new Shake({
       
        threshold: 15
    });
    myShakeEvent.start();
	window.addEventListener('shake', shakeEventDidOccur, false);
    function shakeEventDidOccur () {
        if(!isyyy){
                 return;
        }
        isyyy = false;
        var rid = '${blocks[0].rid}';
    	audioElement.play();
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
            	if(status==0){
            		common("啊噢，<br>再来一次吧，加油！");
    			}else if(status==1){
    				common("恭喜获得"+data.lotteryPrize.jf+"积分");
    			}else if(status==2){
    				var sb="";
    				if(data.lotteryPrizeCode.password==""){
    					sb = "恭喜您<br>获得"+data.lotteryPrize.name+"<br>券号："+data.lotteryPrizeCode.code;
    				}else{
    					sb = "恭喜您<br>获得"+data.lotteryPrize.name+"<br>券号："+data.lotteryPrizeCode.code+"<br>密码："+data.lotteryPrizeCode.password;
    				}
    				common(sb);
    			}else if(status==3){
    				lurid=data.lotteryUserRecordid;
    				var html = $(".dialog_form").html();
					$(".dialog_form").empty();
					$.alert2("恭喜您中了"+data.lotteryPrize.name,"",function(){
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
    			}else if(status==4){
    				common("恭喜您<br>获得"+data.lotteryPrize.hprice+"元红包");
    			}else if(status==5){
    				common(hydesc);
    			}else if(status==6){
    				common("恭喜您中奖!请留意微信信息!");
    			}else if(status == -10006){
					common("嗨！您的机会已用完啦！");
				}else{
					common(hydesc);
				}
            }
    	});
    }
    
    function common(desc){
    	$.alert(desc,"",function(){
			isyyy = true;
			if(hyurl != null && hyurl != "" && hyurl != 0){
				window.location.href=hyurl;
		}});
    }
    
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
				common('保存成功!');
			}
		});
	}
    

});
</script>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
