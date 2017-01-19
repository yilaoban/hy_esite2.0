<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html>
<html >
   	<head lang="en">
	<meta charset="UTF-8">
	<meta content="width=320, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" name="viewport" />
	<title>完善资料</title>
	<link href="/css/mycards/reset.css" rel="stylesheet" type="text/css" />
	<link href="/css/mycards/index_ziliao.css" rel="stylesheet" type="text/css" />
	<link href="https://res.wx.qq.com/open/libs/weui/0.4.0/weui.css" rel="stylesheet"/>
	<link href="/js/jquery-weui/css/jquery-weui.css" rel="stylesheet"/>
	<script type="text/javascript" src="/js/jquery-1.11.1.min.js"></script>
	<%@include file="/WEB-INF/pageshow/wx_fx.jsp"%>
	<script type="text/javascript" src="/js/jquery-weui.js"></script>
	<script src="/js/wyy_ht/mobiscroll_002.js" type="text/javascript"></script>
	<script src="/js/wyy_ht/mobiscroll_004.js" type="text/javascript"></script>
	<link href="/css/wyy_ht/mobiscroll_002.css" rel="stylesheet" type="text/css">
	<link href="/css/wyy_ht/mobiscroll.css" rel="stylesheet" type="text/css">
	<script src="/js/wyy_ht/mobiscroll.js" type="text/javascript"></script>
</head>
<script type="text/javascript">
 	 $(function(){
	    var getCodeBtn = $('#getCode');//获取验证码按钮
	    //获取短信验证码的方法
	    var sendPhoneCode = function(){
		   var telphone = $('#telphone').val();//获取手机号码
	    	if(telphone == ""){
	    		$.alert("请输入手机号码");
	    		return;
	    	}
	    	var isMobile=/^(?:1\d{2})\d{5}(\d{3}|\*{3})$/;
    	    if(!isMobile.test(telphone) ){
    	    	$.alert("请输入正确的手机号码!");
    	        return;         
    	    }
	    	$.post("/${oname}/user/hyuser/getCode.action",{"telphone":telphone},function(data){

	    		
	    		if(data == "Y"){
		    		reSendCountdown();
					$.alert("发送成功，请稍后");
	    		}else{
	    			$.alert("获取验证码失败");
	    		}
			});
	    }
	     
	    //60s后重新发送手机验证码
	    var reSendCountdown = function(){
		        var count = 60;//禁用时间为60秒
		        var resend = setInterval(function(){
		        count--;
		        if (count > 0){
		        	getCodeBtn.attr("waittime", count);
		        	$(getCodeBtn).children().text(count+"秒后重新获取");
		        }else {
		        	$(getCodeBtn).children().text("获取验证码");
			        clearInterval(resend);//清除计时
			        getCodeBtn.attr("waittime", 0);
		        }
		    }, 1000);
	    }
	    //点击按钮触发获取短信验证码事件
	    getCodeBtn.click(function(){
	    	var waittime=$(this).attr("waittime");
	    	if(waittime==0){
	    		sendPhoneCode();
	    	}
	    });
	   
	    
	    
	    $("#subBtn").click(function(){
	    	var can=$(this).attr("canSub");
	    	setTimeout(function(){
	    		$("#subBtn").attr("canSub",1);
	    	},5000);
	    	if(can>0){
		    	$(this).attr("canSub",0);
		    	var telphone = $('#telphone').val().trim();
		    	var code = $('#code').val().trim();
		    	var username = $('#username').val().trim();
		    	if(telphone==""||username=="") {
		    		$.alert("请填写完整的信息！");
		    		return;
		    	}
		    	var isMobile=/^(?:1\d{2})\d{5}(\d{3}|\*{3})$/;
	    	    if(!isMobile.test(telphone) ){
	    	    	$.alert("请输入正确的手机号码!");
	    	        return false;         //返回一个错误，不向下执行
	    	    }
	    	    var titile="确认修改吗?";
	    	    if($("#birthday").attr("readonly")=="readonly"){
	    	    	titile="生日提交后不可修改!";
	    	    }
	    	    var sex=$("#sex").attr("data-values");
	    	    if(sex){
	    	    	$("#gender").val(sex);
	    	    }
	    	    
	    	    $.confirm(titile, function(){
	    	    		 $.ajax({
	    				        cache: true,
	    				        type: "POST",
	    				        url:"/${oname}/user/vip_info_sub.action",
	    				        data:$('#form1').serialize(),// 你的formid
	    				        async: false,
	    				        error: function(request) {
	    				        	$.alert("Connection error",0);
	    				        },
	    				        success: function(data) {
	    				        	if(data.status==1){
	    				        		if(data.url){
		    				        		window.location=data.url;
	    				        		}else{
	    				        			$.alert("设置成功");	    				        			
	    				        		}
	    				        	}else{
	    				        		if(data.msg){
		    				        		$.alert(data.msg);
	    				        		}else{
	    				        			$.alert("设置失败");	    				        			
	    				        		}
	    				        	}
	    				           
	    				        }
	    				    });
	    	    	});
	    	}
	    });
	    
	    
	    $(".ziliao0503 li.yzm").hide();
		$(".ziliao0503 li.shouji").click(function(){
        $(".ziliao0503 li.yzm").show();
        $(".ziliao0503 li.shouji big").removeClass("big_1").addClass("big_2");
		})

		$(function(){  
		    var opt = {  
		        preset: 'date', //日期  
		        theme: 'sense-ui', //皮肤样式  
		        display: 'modal', //显示方式   
		        mode: 'scroller', //日期选择模式  
		        dateFormat: 'yy-mm-dd', // 日期格式  
		        setText: '确定', //确认按钮名称  
		        cancelText: '取消',//取消按钮名籍我  
		        dateOrder: 'yymmdd', //面板中日期排列格式  
		        dayText: '日', monthText: '月', yearText: '年', //面板中年月日文字  
		        endYear:2020 //结束年份  
		    };  
		    $("#birthday").mobiscroll(opt).date(opt);  
		});  
      
       $("#sex").select({
        title: "选择性别",
        items: [
          {
            title: "男",
            value: "1",
          },
          {
            title: "女",
            value: "2",
          }],
        onChange: function(d) {
          console.log(this, d);
        }
      });
	});
  
  </script>
<body>
	<div class="ziliao0503">
	<form id="form1">
		<input type="hidden" name="pageid" value="${pageid }"/>
		<ul>
			<li><label><img src="/images/mycards/pic_2915.png" class="ziliao_left"><i>姓名</i><input type="text" name="username" id="username" value="${hyUser.username }"><big class="big_1"></big></label></li>
			<li><label for="sex" style="display:block"><img src="/images/mycards/pic_2913.png" class="ziliao_left"><i>性别</i><input id="sex" type="text" <s:if test="hyUser.gender==1">value="男"</s:if><s:elseif test="hyUser.gender==2">value="女"</s:elseif>><input id="gender" type="hidden" name="gender" value="${hyUser.gender }"><big class="big_1"></big></label></li>
			<s:if test="hyUser.birthday!=null">
				<li><label for="birthday" style="display:block"><img src="/images/mycards/pic_2914.png" class="ziliao_left"><i>生日</i><input type="text"  class="birth weui_input" name="birthday" readonly="readonly" value="${hyUser.birthday }"><big class="big_1"></big><em class="tip">谨慎填写，不可修改</em></label></li>
			</s:if>
			<s:else>
				<li><label for="birthday" style="display:block"><img src="/images/mycards/pic_2914.png" class="ziliao_left"><i>生日</i><input type="text" class="birth weui_input" id="birthday" name="birthday" value="${hyUser.birthday }"><big class="big_1"></big><em class="tip">谨慎填写，不可修改</em></label></li>
			</s:else>
		</ul>
		<ul>
			<li class="shouji"><label><img src="/images/mycards/pic_2912.png" class="ziliao_left"><i>手机</i><input type="tel" pattern="[0-9]{11}"  name="telphone" id="telphone" value="${hyUser.telphone }"><big class="big_1"></big></label></li>
			<li class="yzm"><label><img src="/images/mycards/pic_2916.png"  class="ziliao_left"><i>验证码</i><input type="text" class="yanzheng" name="code" id="code" maxlength="6"></label><a href="#" id="getCode" waittime="0"><em class="get">获取验证码</em></a></li>
		</ul>
		
		<span><input type="button" value="完成" id="subBtn" canSub="1"></span>
    </form>
	</div>
	<script type="text/javascript">
		
	</script>
</body>
</html>