<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 微预约提供者列表 -->
<link href="/css/wyy/center_yuyue.css" rel="stylesheet" type="text/css" />
<%@include file="/WEB-INF/card/background.jsp"%>
<script type="text/javascript">
		$(document).ready(function(){
			if("${method}"=="E"){
				return;
			}
			var catid = $('#hy_kv').val();
			$('#wyycontent').empty();
			$.post("/${oname}/user/findYuYueServicerListBycatid.action",{"catid":catid},function(data){
				if(data.servicerList){
					var link1 = '/${oname}/user/show/'+data.current.fwpageid+'/pcn/'+$('#hy_kv').val()+'.html';
					var link2 = '/${oname}/user/show/'+data.current.fwzpageid+'/pcn/'+$('#hy_kv').val()+'.html';
					$('#link1').attr("href",link1);
					$('#link2').attr("href",link2);
					$.each( data.servicerList, function(i, n){
						var html = '<li>'+
										'<a href="/${oname}/user/show/'+data.current.fwzxpageid+'/pcn/'+$('#hy_kv').val()+'-U-'+n.id+'.html"  class="details">'+
											'<div class="details_pic"><img src="'+n.simg+'"></div>'+
											'<h2>'+n.name+'</h2>'+
											'<span class="details_1">'+n.hydesc+'</span>'+
										'</a>'+
										'<p class="yuyue"><a href="/${oname}/user/show/'+data.current.xpageid+'/pcn/'+$('#hy_kv').val()+'-U-'+n.id+'.html">立即预约</a></p>'+
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
		<li><a href="#" id="link1"><em>${blocks[0].title1}</em></a></li>
		<li><a href="#" id="link2" class="red"><em>${blocks[0].title2}</em></a></li>
	</ul>
</div>
</s:if>
<s:if test='blocks[1].display=="Y"'>
<div class="content0510 block" status="0" hydata="${blocks[1].rid}" style="${blocks[1].hyct};" >
	<ul id="wyycontent">
		<li><a href="#"  class="details"><div class="details_pic"><img src="/images/wyy/pic_05101.png"></div><h2>李凡</h2><span class="details_1">金牌美容师，2年美容师经验，技法娴熟，服务态度好...</span></a><p class="yuyue"><a href="#">立即预约</a></p></li>
		<li><a href="#"  class="details"><div class="details_pic"><img src="/images/wyy/pic_05101.png"></div><h2>李凡</h2><span class="details_1">金牌美容师，2年美容师经验，技法娴熟，服务态度好...</span></a><p class="yuyue"><a href="#">立即预约</a></p></li>
	</ul>
</div>
</s:if>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
