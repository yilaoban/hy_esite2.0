<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link rel="stylesheet" href="/css/xc/mo/qd/qd.css" type="text/css"></link>
<!-- xc签到 -->
<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
<div hyblk="S" class="block ${blocks[0].ctname}" status="0" hyct="Y" hydata="${blocks[0].rid}" style="padding:5% 0 0 5%;width:50%;${blocks[0].hyct};"><img hyvar="img1" src="${blocks[0].img}" width="100%"/></div>
</s:if>
<s:if test='blocks[1].display=="Y"'>
<div class="block ${blocks[1].ctname}" hyblk="S" status="0" hyct="Y" style="${blocks[1].hyct}" hydata="${blocks[1].rid}">
	<div style="display:none" id="checkin" class="notice"><img hyvar="img1" src="${blocks[1].img1}" /><p><a hyvar="link" href="${blocks[1].link}">${blocks[1].content1}</a></p></div>
	<div style="display:none" id="scheckin" class="notice"><img hyvar="img2" src="${blocks[1].img2}" /><p>${blocks[1].content2}</p></div>
</div>
</s:if>
<script type="text/javascript">
	$(document).ready(function(){ 
	if('${method}'=="E"){
		$("#checkin").show();
		$("#scheckin").hide();
		return;
	} 
		var xcid= $("#hy_entity").val();
		if(xcid == 0){
			$("#checkin").show();
			$("#scheckin").hide();
		}else{
			$.post("/${oname}/user/updatecheckin.action",{"xcid":xcid},function(data){
				if(data.status==1 ){
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

<style type="text/css">
	#checkin a{ text-decoration:none} 
</style>