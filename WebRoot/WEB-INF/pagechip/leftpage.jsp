<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div id="nav">
	<ul>
		<li><a class="nav_desc_1" title="首页" href="${cloudDomain}/index.action">首页</a></li>
		<li class="s_on"><a class="nav_desc_2" title="微站搭建" href="/${sessionScope.account.owner.entity }/page/sitegroupList.action">微站搭建</a></li>
		<li><a class="nav_desc_3" title="用户洞察" href="${mcrmDomain}/data/index.action?ticket=${sessionScope.ticket }">用户洞察</a></li>
		<li><a class="nav_desc_4" title="微信互动" href="${crmDomain }/wx/index.action?ticket=${sessionScope.ticket }">微信互动</a></li>
		<li><a class="nav_desc_5" title="邮件营销" <s:if test="#session.eyLicence==1">href="${yiyouDomain}/markting/markting_index.action?ticket=${sessionScope.ticket }"</s:if><s:else>href="javascript:void(0);" onclick="notAvailable('yy');"</s:else>>邮件营销</a></li>
		<li><a class="nav_desc_6" title="微博互动" <s:if test="#session.ebLicence==1">href="${yiboDomain}/home/index?ticket=${sessionScope.ticket }" </s:if><s:else>href="javascript:void(0);" onclick="notAvailable('yb');"</s:else>>微博互动</a></li>
	</ul>
</div>
<div id="hd">
	<div id="logo"><a target="_blank" href="javascript:void(0);" title="会易官网"><img src="/images/logo.png" style="width:200px;" border="0"/></a></div>
	<div class="link">
		<span class="loginname">登录账户：${sessionScope.account.owner.entity}/${sessionScope.account.username}</span>
		<a href="/${oname }/user/logout.action?url=/login.action" title="登出"><span class="logout"></span></a>
       </div>
</div>
<div id="inside">
<div id="menu">
<!-- 
	<ul>
			<li <s:if test='#session.leftControl[1]=="N"'>style="display: none"</s:if> <s:if test='positionId==1'>class="select"</s:if>><a title="站点信息" href="/${oname}/page/sitegroupList.action"><span>站点信息</span></a></li>
			<li <s:if test='#session.leftControl[2]=="N"'>style="display: none"</s:if> <s:if test='positionId==3'>class="select"</s:if>><a title="内容管理" href="/${oname}/content/content_index.action"><span>内容管理</span></a></li>
			<li <s:if test='#session.leftControl[3]=="N"'>style="display: none"</s:if> <s:if test='positionId==4'>class="select"</s:if>><a title="互动模块" href="/${oname}/interact/index.action"><span>互动模块</span></a></li>
			<li <s:if test='#session.leftControl[4]=="N"'>style="display: none"</s:if> <s:if test='positionId==7'>class="select"</s:if>><a title="微应用" href="/${oname}/page/baseAppIndex.action"><span>微应用</span></a></li>
			<li <s:if test='#session.leftControl[5]=="N"'>style="display: none"</s:if> <s:if test='positionId==2'>class="select"</s:if>><a title="访问统计" href="/${oname}/data/index.action"><span>访问统计</span></a></li>
			<li <s:if test='#session.leftControl[6]=="N"'>style="display: none"</s:if> <s:if test='positionId==5'>class="select"</s:if>><a title="模板库" href="/${oname}/template/index.action?type=C"><span>模板库</span></a></li>
		    <li <s:if test='#session.leftControl[7]=="N"'>style="display: none"</s:if> <s:if test='positionId==6'>class="select"</s:if>><a title="素材库" href="/${oname}/material/index.action?type=Z"><span>素材库</span></a></li>
	</ul>
 -->	
	<ul>
			<li <s:if test='#session.leftControl[1]=="N"'>style="display: none"</s:if> <s:if test='#session.positionId==1'>class="select"</s:if>><a title="微活动" href="/${oname}/page/sitegroupList.action"><span>微活动</span></a></li>
			<li <s:if test='#session.leftControl[3]=="N"'>style="display: none"</s:if> <s:if test='#session.positionId==3'>class="select"</s:if>><a title="微网站" href="/${oname}/page/xcSiteGroup.action"><span>微现场</span></a></li>
			<li <s:if test='#session.leftControl[2]=="N"'>style="display: none"</s:if> <s:if test='#session.positionId==2'>class="select"</s:if>><a title="微网站" href="/${oname}/page/website.action"><span>微网站</span></a></li>
			<li <s:if test='#session.leftControl[4]=="N"'>style="display: none"</s:if> <s:if test='#session.positionId==4'>class="select"</s:if>><a title="互动模块" href="/${oname}/interact/base.action"><span>微互动</span></a></li>
			<li <s:if test='#session.leftControl[5]=="N"'>style="display: none"</s:if> <s:if test='#session.positionId==5'>class="select"</s:if>><a title="微应用" href="/${oname}/page/baseAppIndex.action"><span>微应用</span></a></li>
		    <li <s:if test='#session.leftControl[6]=="N"'>style="display: none"</s:if> <s:if test='#session.positionId==6'>class="select"</s:if>><a title="内容素材" href="/${oname}/content/content_index.action"><span>内容素材</span></a></li>
	</ul>
</div>
