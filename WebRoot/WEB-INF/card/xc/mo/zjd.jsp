<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 移动砸金蛋 -->
<link rel="stylesheet" href="/css/xc/mo/zjd/index.css" type="text/css"></link>
<link rel="stylesheet" href="/css/xc/mo/zjd/zjd_ld.css" type="text/css"></link>


<style type="text/css" class_data="hy2015050743701">
.egg{height:200px;}
.egg ul li{}
.eggList{padding-top:58px;position:relative;}
.eggList li{float:left;background:url(/images/xc/mo/zjd/egg.png) no-repeat -48px -60px;width:96px;height:132px;cursor:pointer;position:relative;}
.eggList li.curr{background:url(/images/xc/mo/zjd/egg.png) no-repeat -161px -54px;cursor:default;z-index:300;}
.hammer{background:url(/images/xc/mo/zjd/hammer.png) no-repeat;width:74px;height:87px;position:absolute; text-indent:-9999px;left:260px;top:55px;}
.tip{width:auto;padding:0 10px;position:absolute;top:80px; background:black;color:white;z-index:1000;overflow:hidden;display:none;}
.tipImg{width:60px;}
.tipButton{margin:15px 0;}
.mask{display:none;position:fixed;left:0;top:0;width:100%;height:100%;z-index:999;background:rgba(0,0,0,0.2);}
</style>


<%@include file="/WEB-INF/card/background.jsp"%>

	<div class="zjd_141117_jd block ${blocks[0].ctname }" hyblk="S" status="0" style="position: relative;${blocks[0].hyct }" hydata="${blocks[0].rid}" class_data="hy2015050743786">
  	<div class="mask"></div>
  	<div class="egg" class_data="hy2015050743515">
		<ul class="eggList" class_data="hy2015050743539">
			<li></li>
			<li></li>
			<li></li>
			<p class="hammer" id="hammer"></p>
		</ul>
	</div>
	<s:set name="obj" value="#session.hy_page_dto['125'].lottery" />
  </div>
  	<div class="tip" style="text-align:center;padding:20px;" class_data="hy2015050743497">
  		<p><img class="tipImg" src="/images/hudong/zajindan2/8.png" style="width:100px;" /></p>
  		<span class_data="hy2015050743114">亲，继续努力哦</span>
  		<p><button class="tipButton">再砸一次</button></p>
  	</div>
  
  


<s:if test='method != "E"'>
<%@include file="/WEB-INF/card/zjdfile.jsp"%>
</s:if>
   
<%@include file="/WEB-INF/card/cardfile.jsp"%>