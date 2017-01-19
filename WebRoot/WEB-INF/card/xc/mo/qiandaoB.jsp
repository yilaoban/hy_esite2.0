<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link rel="stylesheet" href="/css/tuwen/xcqiandao/qd.css" type="text/css"></link>
<!-- xc签到B -->
<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div hyblk="S" class="block" hydata="${blocks[0].rid}" style="padding:5% 0 0 5%;width:50%;"><img hyvar="img1" src="${blocks[0].img}" width="100%"/></div>
</s:if>
<s:if test='blocks[1].display=="Y"'>
<div hyblk="S" class="block" hydata="${blocks[1].rid}">
	<div id="checkin" class="notice"><img hyvar="img1" src="${blocks[1].img1}" /><p>${blocks[1].content1}</p><p class="link"><a hyvar="link" href="${blocks[1].link}">${blocks[1].content2}</a></p></div>
	<div id="scheckin" class="notice"><img hyvar="img2" src="${blocks[1].img2}" /><p>${blocks[1].content3}</p></div>
</div>
</s:if>
<script type="text/javascript">
	$(document).ready(function(){
		var xcid= $("#hy_entity").val();
		if(xcid == 0){
			$("#checkin").show();
			$("#scheckin").hide();
		}else{
			$.post("/${oname}/user/updatecheckin.action",{"xcid":xcid},function(data){
				if(data.status == 1){
					$("#checkin").show();
					$("#scheckin").hide();
				}else{
					if(data.status==-1&&data.url!=""){
						window.location=data.url;
					}else{
						$("#checkin").hide();
						$("#scheckin").show();
					}
				}
			});
		}
	});
	
</script>
<%@include file="/WEB-INF/card/cardfile.jsp"%>