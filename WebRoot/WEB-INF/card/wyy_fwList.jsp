<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 微预约服务列表 -->
<link href="/css/wyy/center_yuyue.css" rel="stylesheet" type="text/css" />
<%@include file="/WEB-INF/card/background.jsp"%>
<script type="text/javascript">
		$(document).ready(function(){
			if("${method}"=="E"){
				return;
			}
			$('#wyycontent').empty(); 
			var catid = $('#hy_kv').val();
			$.post("/${oname}/user/findYuYueService.action",{"catid":catid},function(data){
				var link1 = '/${oname}/user/show/'+data.current.fwpageid+'/pcn/'+$('#hy_kv').val()+'.html';
				var link2 = '/${oname}/user/show/'+data.current.fwzpageid+'/pcn/'+$('#hy_kv').val()+'.html';
				$('#link1').attr("href",link1);
				$('#link2').attr("href",link2);
				if(data.serviceList){
					$.each( data.serviceList, function(i, n){
						var html =  '<li>'+
						 				'<a href="/${oname}/user/show/'+data.current.fwxpageid+'/pcn/'+$('#hy_kv').val()+'-S-'+n.id+'.html"  class="details">'+
						 					'<div class="details_pic">'+
						 						'<img src="'+n.simg+'">'+
						 					'</div>'+
						 					'<h2>'+n.name+'</h2>'+
						 					'<span class="details_1">'+n.hydesc+'</span>'+
						 				'</a>'+
						 				'<p class="yuyue"><a href="/${oname}/user/show/'+data.current.xpageid+'/pcn/'+$('#hy_kv').val()+'-S-'+n.id+'.html">立即预约</a></p>'+
						 			'</li>';
						$('#wyycontent').append(html);
						
					});
				}
			});
		});

	</script>

<s:if test='blocks[0].display=="Y"'>
<div class="nav0510 block" status="0" hydata="${blocks[0].rid}" style="${blocks[0].hyct};">
	<ul>
		<li><a href="#" id="link1" class="red"><em>${blocks[0].title1}</em></a></li>
		<li><a href="#" id="link2"><em>${blocks[0].title2}</em></a></li>
	</ul>
</div>
</s:if>
<s:if test='blocks[1].display=="Y"'>
<div class="content0510 block" status="0" hydata="${blocks[1].rid}" style="${blocks[1].hyct};" >
 	<ul id="wyycontent">
 		<li>
 			<a href="#"  class="details">
 				<div class="details_pic">
 					<img src="/images/wyy/pic_05102.png">
 				</div>
 				<h2>胶原再生护理</h2>
 				<span class="details_1">原价1018元的美发套餐，现仅售680元。请于消费前3小时..</span>
 			</a>
 			<p class="yuyue"><a href="#">立即预约</a></p>
 		</li>
 	</ul>
</div>
</s:if>
<%@include file="/WEB-INF/card/cardfile.jsp"%>