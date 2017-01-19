<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 预约已确认 -->
<link rel="stylesheet" href="/css/fn/yy/reservation.css" type="text/css">
<%@include file="/WEB-INF/card/background.jsp"%>

     <nav><i class="fenge"><a href="reservation.html">待确认</a></i><i><a href="reservation2.html" class="red">已确认</a></i></nav>
	<div class="main0419" id="main" style="display:none;">
                <div>
					<h3><b class="right">&nbsp;</b>2016-04-17</h3>
					<div class="box">
							<div class="time0419">
							        <div class="left0419">
							        	<span>09:00</span><img src="/images/fn/yy/point3.png">
							        </div>
									<div class="right0419">
											<span class="name">顾客：<i>南宫</i></span>
							                <span class="num">技师：<i>1号</i></span>
							                <span class="pro">项目：<i>美白保湿系列美白保湿系列美白保湿系列美白保湿系列美白保湿系列美白保湿系列</i></span>
							                
					                </div>
							</div>
							<div class="time0419">
							        <div class="left0419">
							        	<span>10:00</span><img src="/images/fn/yy/point3.png">
							        </div>
									<div class="right0419">
											<span class="name">顾客：<i>南宫</i></span>
							                <span class="num">技师：<i>1号</i></span>
							                <span class="pro">项目：<i>美白保湿系列</i></span>
							                
					                </div>
							</div>
							<div class="time0419">
							        <div class="left0419">
							        	<span>&nbsp;</span><img src="/images/fn/yy/point3.png">
							        </div>
									<div class="right0419">
											<span class="name">顾客：<i>南宫</i></span>
							                <span class="num">技师：<i>1号</i></span>
							                <span class="pro">项目：<i>美白保湿系列</i></span>
							                
					                </div>
							</div>
					</div>
			    </div>
			    <div>
					<h3><b class="right">&nbsp;</b>2016-04-16</h3>
					<div class="box">
							 <div class="time0419">
							        <div class="left0419">
							        	<span>08:00</span><img src="/images/fn/yy/point3.png">
							        </div>
									<div class="right0419">
											<span class="name">顾客：<i>南宫</i></span>
							                <span class="num">技师：<i>1号</i></span>
							                <span class="pro">项目：<i>美白保湿系列</i></span>
							                
					                </div>
							</div>
							<div class="time0419">
							        <div class="left0419">
							        	<span>11:00</span><img src="/images/fn/yy/point3.png">
							        </div>
									<div class="right0419">
											<span class="name">顾客：<i>南宫</i></span>
							                <span class="num">技师：<i>1号</i></span>
							                <span class="pro">项目：<i>美白保湿系列</i></span>
							                
					                </div>
							</div>
							<div class="time0419">
							        <div class="left0419">
							        	<span>&nbsp;</span><img src="/images/fn/yy/point3.png">
							        </div>
									<div class="right0419">
											<span class="name">顾客：<i>南宫</i></span>
							                <span class="num">技师：<i>1号</i></span>
							                <span class="pro">项目：<i>美白保湿系列</i></span>
							                
					                </div>
							</div>
					</div>
			    </div>
	</div>

<script type="text/javascript">
	$(document).ready(function(){
		//if("${method}" == "E"){
		//	$('#main').show();
		//	return;
		//}
	
		$.post("/${oname}/user/yyRecord.action",{"status":"CMP"}, function(data){
			if(data){
				$("#main").empty();
				$.each( data.recordList, function(i, n){
					var html = '<div>';
					html += '<h3><b class="right">&nbsp;</b>'+n.yytimeStr+'</h3>';
					html += '<div class="box">';
					if(n.recordList){
						$.each(n.recordList, function(i2, n2){
							html += '<div class="time0419">';	
							html += '<div class="left0419">';
							html += '<span>'+n2.timeStr+'</span><img src="/images/fn/yy/point3.png">';
							html += '</div>';
							html += '<div class="right0419">';
							html += '<span class="name">顾客：<i>'+n2.nickname+'</i></span>';
							html += '<span class="num">技师：<i>'+n2.sername+'</i></span>';
							html += '<span class="pro">项目：<i>'+n2.servicename+'</i></span>';
							html += '</div></div>'
						});
					}
					html += '</div></div>';
					$("#main").append(html);$("#main").show();
				});
				
				 $("h3").click(function(){
	  				var bclass=$(this).children('b').attr("class");
	  				if(bclass=="right"){
	  					$(this).children('b').removeClass("right").addClass("down");
	  					$(this).siblings('.box').show();
	  				}else{
	  					$(this).children('b').removeClass("down").addClass("right");
	  					$(this).siblings('.box').hide();
	  				}
  			});
			}
		  });
		  
		 
	});
</script>

<%@include file="/WEB-INF/card/cardfile.jsp"%>
