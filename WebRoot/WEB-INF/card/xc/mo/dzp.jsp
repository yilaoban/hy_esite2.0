<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- xc大转盘 -->
<link href="/css/xc/mo/dzp/index.css" rel="stylesheet" type="text/css" />
<link href="/css/xc/mo/dzp/list.css" rel="stylesheet" type="text/css" />

<%@include file="/WEB-INF/card/background.jsp"%>

<s:if test='blocks[0].display=="Y"'>
<div class="wdy_top block ${blocks[0].ctname}" status="0" hyct="Y" style="${blocks[0].hyct}" hyblk="S" hydata="${blocks[0].rid}" class_data="hy20150507111858">
	<img src="${blocks[0].img}" style="width:100%;height:100%;" hyvar="img" hydesc="640*350"/>
</div>
</s:if>

<s:if test='blocks[1].display=="Y"'>
<div class="wdy_center_dazhuanpan1_141113_1 ${blocks[1].ctname}" status="0" hyct="Y" style="${blocks[1].hyct}" hyblk="I" hydata="${blocks[1].rid}" class_data="hy20150507111635">
	<div class="outercont" class_data="hy20150507111508">
		<div class="outer-cont" class_data="hy20150507111868">
			<div id="outer${pcid }" class="outer" class_data="hy20150507111338"><img src="/images/xc/mo/dzp/dzp_bimg.png" /></div>
		</div>
		<div class="inner-cont" class_data="hy20150507111533">
			<div id="inner${pcid }" class="inner" class_data="hy20150507111560"><img width="81%" src="/images/xc/mo/dzp/dzp_simg.png"/></div>
		</div>
	</div>
	<s:set name="obj" value="#session.hy_page_dto['136'].lottery" />
</div>
</s:if>
<%@include file="/WEB-INF/card/dzpfileTprize.jsp"%>
<%@include file="/WEB-INF/card/cardfile.jsp"%>
