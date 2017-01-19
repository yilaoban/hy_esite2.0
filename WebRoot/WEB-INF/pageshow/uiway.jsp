<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="format-detection" content="telephone=no">
<meta name="format-detection" content="email=no">
<link href="https://res.wx.qq.com/open/libs/weui/0.4.0/weui.css" rel="stylesheet" />
<link href="/js/jquery-weui/css/jquery-weui.css" rel="stylesheet" />
<script src="/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="/js/jquery-weui/js/jquery-weui.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="/css/cashier/reset.css" />
<link rel="stylesheet" type="text/css" href="/css/cashier/index.css" />
<script type="text/javascript">
	var kv = '${kv}';
	var kk = kv.split("-");
	var id = kk[0];
	var endtime = kk[1];
	$(document).ready(function() {
		$.post("/${oname}/user/findUserWay.action",{"id":id,"endtime":endtime},function(data){
			if(data.wxUser){
				$('#img').attr("src",data.wxUser.headimgurl);
				$('#nickname').html(data.wxUser.nickname);
				$('#level').html("会员等级：" + data.hyUserLevel.name);
				$('#hyuid').html("会员卡号：NO." + data.hyUser.id);
				$('#balance').val("会员卡余额："+data.rmb/100+"元");
				$('#bal').val(data.rmb);
				$('#rmb').attr("onblur","zk("+data.hyUser.levelid+")");
				if(data.xfDescList){
					$.each( data.xfDescList, function(i, n){
						var html = "";
						if(i == 0){
							 html = '<option selected="selected" value="'+n.id+'">'+n.name+'</option>';
						}else{
							 html = '<option value="'+n.id+'">'+n.name+'</option>';
						}
						$('#hydesc').append(html);
					});
				}
			}else{
				window.location.href = "/${oname}/user/scan.action";
			}
		});
	});
	
	function confirm(){
		var xfid = $('#hydesc').val();
		var rmb = $('#rmb').val() * 100;
		var bal = $('#bal').val();
		if(xfid == 0){
			$.alert("请选择消费备注");
			return;
		}
		if(bal >= rmb){
			$.post("/${oname}/user/sm.action",{"key":'${kv}',"rmb":rmb,"xfid":xfid},function(data){
				if(data.status == 1){
					window.location.href = "/${oname}/user/showUserWay.action?id="+ id +"&endtime=" + endtime + "&hydesc="+rmb;
				}else{
					$.alert(data.hydesc);
				}
			});
		}else{
			$.alert("会员卡余额不足");
		}
	}
	
	$(function(){ 
		document.onkeydown = function(event){
		  switch(event.keyCode) {
		    case 13:
		      if (!window.buttonIsFocused) confirm();
		      break; // enter 键
		  }
		};
	}) 
	
	function zk(levelid){
		var xfid = $('#hydesc').val();
		var rmb = $('#rmb').val() * 100;
		if(xfid == 0){
			$.alert("请选择消费备注");
			return;
		}
		$("/${oname}/user/showzkrmb.action",{"levelid":levelid,"xfid":xfid,"rmb":rmb},function(data){
			if(data){
				$('#conf').attr("value","确定("+data/100+"元)");
			}
		})
	}
	
</script>

<div class="pcs_img_bg">
		<div class="pcs_white">
		<div class="pcs_blue">
			<div class="pcs_main">
				<img src="" class="pcs_head" id="img">
				<div class="pcs_text">
					<p id="nickname"></p>
					<p id="level"></p>
					<p id="hyuid"></p>
				</div>
			</div>
		</div>
		<div class="pcs_main">
			<div class="pcs_input">
				<input type="text" id="balance" value="会员卡余额：元" readonly>
				<input type="hidden" id="bal" value="">
			</div>
			<div class="pcs_input">
				<select id="hydesc">
					<option value="0" >消费备注</option>
				</select>
			</div>
			<div class="pcs_input">
				<input type="text" value="" placeholder="点此输入消费金额(元)" id="rmb" onblur="" onkeyup="if(this.value==this.value2)return;if(this.value.search(/^\d*(?:\.\d{0,2})?$/)==-1)this.value=(this.value2)?this.value2:'';else this.value2=this.value;">
			</div>
			<div class="pcs_button">
				<input type="button" value="确定" onclick="confirm()" id="conf" onfocus="window.buttonIsFocused=true;" onblur="window.buttonIsFocused=false;">
			</div>
		</div>
		</div>
	</div>
