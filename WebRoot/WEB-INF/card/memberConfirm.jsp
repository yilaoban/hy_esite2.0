<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<link href="/css/setting/yanzheng.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$(document).ready(function(){
	$.post("/${oname}/user/memberConfirm.action",function(data){
		if(data){
			if(data.hyUserLevel){
				$(".member_level_text").html(data.hyUserLevel.name);
			}else if(data.balanceSet.hyname){
				$(".member_level_text").html(data.balanceSet.hyname);
			}
			$('#link').val("/${oname}/user/show/"+data.pagedto.rmxid+"/wxn.html");
		}
	});
});


function confirm(){
	var code = $('#code').val();
	$.post("/${oname}/user/updateLevelBycheckCode.action",{"code":code},function(data){
		if(data == 1){
			$.alert("恭喜您提升会员等级成功!","",function(){
				window.location.href = $('#link').val();
			});
		}else if(data == 2){
			$.alert("验证码不正确!","");
		}else if(data == 4){
			$.alert("该验证码已使用!","");
		}else if(data == 3){
			$.alert("还未开通使用验证码提升会员等级!","");
		}
	});
}
</script>
<%@include file="/WEB-INF/card/background.jsp"%>
	<div class="top0615">
		<div class="tx0615"><div class="tx"><img src="${sessionScope.visitUser.wxUser.headimgurl}" alt=""></div></div>
		<p class="name">${sessionScope.visitUser.wxUser.nickname}</p>
		<p class="level">普通会员</p>
	</div>
    <form action="">
    	<input type="hidden" value="" id="link">
    	<p class="p34"><input type="text" placeholder="点此输入验证码" id="code"></p>
    	<p class="p35"><input type="button" value="验证" onclick="confirm()"></p>
    </form>
<%@include file="/WEB-INF/card/cardfile.jsp"%>