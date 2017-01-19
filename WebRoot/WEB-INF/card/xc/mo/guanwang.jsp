<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!-- xc官网 -->
<link href="/css/xc/mo/gw/list.css" rel="stylesheet" type="text/css">
<%@include file="/WEB-INF/card/background.jsp"%>
	<div class="jdrs_box"> 
		<div class="dzp_box" >
		 	<div class="tc_box" id="commentContent" >
		 		<ul>
		 		<s:if test='method =="E"'>
	 				<li>
	 					<div class='tc_tx'>
	 						<img src="/images/H-logo.png" width='50px' height='50px'/>
	 						<span>会易环宇</span>
	 					</div> 
	 					<div class='tc_nr'>
	 						<div class='tc_nr_left'>
	 							<img src='/res/lenovo/weizhan/images/tc_01_b.png'>
	 						</div>
	 						<div class='tc_nr_right'>你们公司还好吗?</div>
	 					</div>
	 				</li>
	 				<li>
	 					<div class='tc_tx'>
	 						<img src="/images/H-logo.png" width='50px' height='50px'/>
	 						<span>会易环宇</span>
	 					</div> 
	 					<div class='tc_nr'>
	 						<div class='tc_nr_left'>
	 							<img src='/res/lenovo/weizhan/images/tc_01_b.png'>
	 						</div>
	 						<div class='tc_nr_right'>你们公司还好吗?</div>
	 					</div>
	 				</li>
	 				<li>
	 					<div class='tc_tx'>
	 						<img src="/images/H-logo.png" width='50px' height='50px'/>
	 						<span>会易环宇</span>
	 					</div> 
	 					<div class='tc_nr'>
	 						<div class='tc_nr_left'>
	 							<img src='/res/lenovo/weizhan/images/tc_01_b.png'>
	 						</div>
	 						<div class='tc_nr_right'>你们公司还好吗?</div>
	 					</div>
	 				</li>
	 				<li>
	 					<div class='tc_tx'>
	 						<img src="/images/H-logo.png" width='50px' height='50px'/>
	 						<span>会易环宇</span>
	 					</div> 
	 					<div class='tc_nr'>
	 						<div class='tc_nr_left'>
	 							<img src='/res/lenovo/weizhan/images/tc_01_b.png'>
	 						</div>
	 						<div class='tc_nr_right'>你们公司还好吗?</div>
	 					</div>
	 				</li>
	 			</s:if>
		 		</ul>
		 	</div>
		</div>
	</div>
	<div class="tc_bottom">
		 我来说两句
	</div>
	<div id="modaltopic" style="position:fixed;top:0;right:0;bottom:0;left:0;background:#b5b5b5;opacity:.9;display:none;z-index:1001;"></div>
	<div id="topic_create" style="display:none;position:fixed;top:0;right:0;bottom:0;left:0;padding:20px;z-index:1002;">
		<textarea id="content" style="width:100%;height:70px;padding:0;"></textarea>
		 <input type="button" id="send" onclick="send()"  value="发送" style="width:100%;background:#00A0E9;color:#fff;font-size:1.2em;border-radius:8px;padding:5px 0;border:1px solid #00A0E9;margin-top:5px;">
	</div>
<script type="text/javascript">
 	var timer;
	function ping(){
		timer = window.setInterval("comment()",2000);
	}
	ping();
	var start = 0;
	function comment(){
		var xcid=$("#hy_entity").val();
		var pageid=$("#hy_pageid").val();
		if(xcid >0){
		$.post("/${oname}/user/wx_comment.action",{"xcid":xcid,"pageid":pageid,"start":start,"size":5},function(data){
			$.each(data,function(index,value){
				if(value.id > start){
					start = value.id;
				}
				$("#commentContent ul").append("<li><div class='tc_tx'><img src="+value.headimgurl+" width='50px' height='50px'/><span>"+value.nickname+"</span></div> <div class='tc_nr'><div class='tc_nr_left'><img src='/res/lenovo/weizhan/images/tc_01_b.png'></div><div class='tc_nr_right'>"+value.content+"</div></div></li>");
			});
			var abc = $("#commentContent ul li").length;
			$("#commentContent").animate({marginTop:"-"+((abc-4)*84)+"px"});
		});
			
		}
	}
	
	var sendTime = 0;
 	function send(){
 	if('${method}'=="E"){
 		return;
 	}
 			var nowTime = new Date().getTime();
			var xcid=$("#hy_entity").val();
			var content=$("#content").val();
			var pageid=$("#hy_pageid").val();
			if(content==''){
				$.alert('评论总得写点什么吧','');
				return false;
			}
			if(sendTime == 0){
				sendTime=nowTime;
					$.post("/${oname}/user/saveComment.action",{"pageid":pageid,"xcid":xcid,"content":content},function(data){
						console.log(data);
						if(data == 1){
			   				document.getElementById('content').value='';
			   				$("#modaltopic, #topic_create").hide();
						}else if(data == -10002){
			   				$.alert('次数已达上限','');
						}else if(data == 'N'){
			   				$.alert('请先签到再评论','');
						}else{
			   				$.alert('评论失败','');
						}
					});
			}else{
				var a=nowTime-sendTime;//时间差
				if(a<1500){
					$.alert('请勿频繁发送','');
				}else{
					$.post("/${oname}/user/saveComment.action",{"pageid":pageid,"xcid":xcid,"content":content},function(data){
						if(data == 1){
			   				document.getElementById('content').value='';
			   				$("#modaltopic, #topic_create").hide();
						}else if(data == -10002){
			   				$.alert('次数已达上限','');
						}else if(data == 'N'){
			   				$.alert('请先签到再评论','');
						}else{
			   				$.alert('评论失败','');
						}
					});
				}
			}
		}
	
	
	$(document).ready(function(){
		a = $(window).height();
	})
	
	$(".tc_bottom").click(function(){
		$("#modaltopic, #topic_create").show();
	})
 </script>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
