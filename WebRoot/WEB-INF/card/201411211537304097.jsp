<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 砸金蛋2 -->
<link rel="stylesheet" href="/css/hudong/zajindan2/index.css" type="text/css"></link>
<link rel="stylesheet" href="/css/hudong/zajindan2/zjd_ld.css" type="text/css"></link>


<style type="text/css" class_data="hy2015050743701">
	.egg{height:200px;}
	.egg ul li{}
	.eggList{padding-top:58px;position:relative;}
	.eggList li{float:left;background:url(/images/hudong/zajindan2/egg.png) no-repeat -48px -60px;width:96px;height:132px;cursor:pointer;position:relative;}
	.eggList li.curr{background:url(/images/hudong/zajindan2/egg.png) no-repeat -161px -54px;cursor:default;z-index:101;}
	.hammer{background:url(/images/hudong/zajindan2/hammer.png) no-repeat;width:74px;height:87px;position:absolute; text-indent:-9999px;left:70%;top:55px;}
	.tip{width:auto;padding:0 10px;position:absolute;top:180px; background:black;color:white;z-index:1000;overflow:hidden;display:none;}
	.tipImg{width:60px;}
	.tipButton{margin:15px 0;background-color:#474747;}
	.mask{display:none;position:fixed;left:0;top:0;width:100%;height:100%;z-index:999;background:rgba(0,0,0,0.2);}
</style>


<%@include file="/WEB-INF/card/background.jsp"%>
<s:if test='blocks[0].display=="Y"'>
	<div class="zjd_141117_jd block" hyblk="S" status="0" style="position: relative;${blocks[0].hyct }" hydata="${blocks[0].rid}" class_data="hy2015050743786">
	<s:if test='blocks[0].obj.id > 0'>
	  	<div class="mask"></div>
	  	<div class="egg" class_data="hy2015050743515">
			<ul class="eggList" class_data="hy2015050743539">
				<li></li>
				<li></li>
				<li></li>
				<p class="hammer" id="hammer"></p>
			</ul>
		</div>
		<s:set name="obj" value="blocks[0].obj" />
	</s:if>
	<s:else>
  		<img src="/images/hudong/zajindan2/za.png" style="width:100%;height:100%;" />
	</s:else>
	</div>
</s:if>

   
<%@include file="/WEB-INF/card/zjdfile.jsp"%>
<%@include file="/WEB-INF/card/cardfile.jsp"%>