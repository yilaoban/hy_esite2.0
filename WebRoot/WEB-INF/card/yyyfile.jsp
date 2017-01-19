<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>

<script type="text/javascript">
var hyurl,hydataid;
var music = new Audio();
music.src = "http://img.huiyee.com/egg/yaoyiyao.wav";
music.load();
$(document).ready(function() {
    var myShakeEvent = new Shake({
        threshold: 15
    });
    myShakeEvent.start();
    window.addEventListener('shake', shakeEventDidOccur, false);

    function shakeEventDidOccur () {
		if('${method}' == "E" || $("#result").html() != ""){
			return;
		}
		music.play();
		var rid ='${blocks[0].rid}';
		$.ajax({
			url:"/${oname}/user/lottery_draw.action",
			type: "post",
			dataType:"json",
			data:{
				"lid":'${blocks[0].obj.id}',
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
            	var lurid = data.lotteryUserRecordid;
            	if(status<0){
            		$("#result").html(hydesc);
            	}else if(status==0){
            		$("#result").html("亲，继续努力哦!");
				}else if(status==1){
					$("#result").html("恭喜您中了"+data.lotteryPrize.jf+"积分");
				}else if(status==2){
					$("#result").html("恭喜您中了"+data.lotteryPrize.name+"</p><p>券号："+data.lotteryPrizeCode.code+"</p></p>密码："+data.lotteryPrizeCode.password+"</p>");
				}else if(status==3){
					hyurl=data.url;
					hydataid=data.id;
					$("#result").html('<span style="float:left;"><img width=50 src="/images/hudong/yaoyiyao/jph.png" /></span><span style="float:left;display:block;width:40%;">恭喜你中了:<br />'+data.lotteryPrize.name+'</span><span onclick="yyyusermsg('+lurid+')" style="line-height:50px;border:#fff solid 1px;padding:10px;">点击领奖</span>');
				}else if(status==4){
					$("#result").html("恭喜您摇到了"+data.lotteryPrize.hprice+"元红包");
				}else if(status==5){
					$("#result").html(hydesc);
				}else if(status==6){
					$("#result").html("恭喜您中奖!请留意微信信息!");
				}
				if(status != 3){
					$("#result").addClass("result ${blocks[1].ctname}");
					setTimeout(function(){
						$("#result").html("");
						$("#result").removeClass("result ${blocks[1].ctname}");
						if(data.url != null && data.url != ""){
							window.location.href=data.url
						}else if(data.id!=0){
							window.location.href="/${oname}/user/show/"+data.id+".html";
						}
			        }, 5000);
				}
            },
            error: function(request) {
				console.log("Connection error");
            }
		});
    }

});

function yyyusermsg(lurid){
	easyDialog.open({
	  container : {
	    content : '<div class="dtable"><table style="border-collapse:separate; border-spacing:3px;"><tr><td width="25%">姓名</td><td><input type="text" style="width:90%" id="username"/></td></tr><tr><td>电话</td><td><input type="number" style="width:90%" id="userphone" /></td></tr><tr><td>地址</td><td><input type="text" style="width:90%" id="userlocation" ></td></tr><tr><td colspan="2" align="center" style="line-height:40px;"><span onclick="userCheck('+lurid+')">提交</span></td></tr></table></div>'
	  }
	});
}

function userCheck(urid){
	var username = $("#username").val();
	var userphone = $("#userphone").val();
	var userlocation = $("#userlocation").val();
	if(!/^1[3|4|5|8]\d{9}$/.test(userphone)){
		$("#userphone").css("border-color","red");
		return;
	}
	$.post("/${oname}/user/zhuanpan_winner.action",{"dto.lotteryUser.lurid":urid,"dto.lotteryUser.username":username,"dto.lotteryUser.userphone":userphone,"dto.lotteryUser.userlocation":userlocation},function(data){
		if(data=="Y"){
			$.alert('保存成功!','');
			if(hyurl != null && hyurl != "" && hyurl != 0){
				setTimeout(function(){window.location.href=hyurl;},2000); 
			}else if(hydataid!=0){
				setTimeout(function(){window.location.href="/${oname}/user/show/"+hydata.id+".html";},2000); 
			}else{
				$("#a_"+'${pcid}').load('/${oname}/user/showCard.action?pcid='+'${pcid}'+"&pageid="+'${pageid}'+'&jspstyle='+'${page.jspstyle}');
			}
		}
	});
}

</script>