<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div id="nav">
	<div class="portraid">
		<img src="/images/itrax.png" />
		<p>数字营销平台</p>
	</div>
	<ul id="accordions" class="accordions">
			<li>
				<div class="link nav_desc_3">微内容<i class="fa fa-chevron-down"></i></div>
				<ul class="submenu">
					<li><a href="#">微信图文</a></li>
					<li><a href="#">微博图文</a></li>
					<li><a href="/${oname}/page/website.action">微网站</a></li>
					<li><a href="/${oname}/template/index.action?type=W">H5</a></li>
					<li><a href="/${oname}/interact/base.action">微互动</a></li>
				</ul>
			</li>
			<li>
				<div class="link nav_desc_4">微传播<i class="fa fa-chevron-down"></i></div>
				<ul class="submenu">
					<li><a href="http://crm.itrax.cn/wx/index.action?ticket=${sessionScope.ticket }">微信管理</a></li>
					<li><a href="http://wb.itrax.cn/home/index?ticket=${sessionScope.ticket }">微博管理</a></li>
					<li><a href="/${sessionScope.account.owner.entity }/page/website.action">微网站</a></li>
				</ul>
			</li>
			<li>
				<div class="link nav_desc_5">微现场<i class="fa fa-chevron-down"></i></div>
				<ul class="submenu">
					<li><a href="/${oname}/page/xcSiteGroup.action">微现场管理</a></li>
				</ul>
			</li>
			<li>
				<div class="link nav_desc_6">我的应用</div>
				<ul class="submenu">
					<li class="s_on"><a class="nav_desc_2" title="微站搭建" href="/${sessionScope.account.owner.entity }/page/sitegroupList.action">微站搭建</a></li>
					<li><a class="nav_desc_3" title="用户洞察" href="http://mcrm.itrax.cn/data/index.action?ticket=${sessionScope.ticket }">用户洞察</a></li>
					<li><a class="nav_desc_6" title="微博互动" href="http://wb.itrax.cn/home/index?ticket=${sessionScope.ticket }">微博互动</a></li>
				</ul>
			</li>
			<li>
				<div class="link nav_desc_7">用户洞察<i class="fa fa-chevron-down"></i></div>
				<ul class="submenu">
					<li><a href="http://mcrm.itrax.cn/data/index.action?ticket=${sessionScope.ticket }">访问统计</a></li>
					<li><a href="http://mcrm.itrax.cn/user/index.action?ticket=${sessionScope.ticket }">用户分析</a></li>
					<li><a href="http://mcrm.itrax.cn/share/index.action?ticket=${sessionScope.ticket }">传播分享</a></li>
				</ul>
			</li>
	</ul>
</div>
<div id="hd">
	<div class="hdin clearfix">
	<div class="link">
		<span class="loginname">${sessionScope.account.owner.entity}/${sessionScope.account.username}</span>
		<a href="/${oname }/user/logout.action?url=/login.action" title="登出"><span class="logout"></span></a>
    </div>
    </div>
</div>
<div id="inside">
<%-- <div id="menu">
	<ul>
			<li <s:if test='#session.leftControl[1]=="N"'>style="display: none"</s:if> <s:if test='#session.positionId==1'>class="select"</s:if>><a title="微活动" href="/${oname}/page/sitegroupList.action"><span>微活动</span></a></li>
			<li <s:if test='#session.leftControl[3]=="N"'>style="display: none"</s:if> <s:if test='#session.positionId==3'>class="select"</s:if>><a title="微网站" href="/${oname}/page/xcSiteGroup.action"><span>微现场</span></a></li>
			<li <s:if test='#session.leftControl[2]=="N"'>style="display: none"</s:if> <s:if test='#session.positionId==2'>class="select"</s:if>><a title="微网站" href="/${oname}/page/website.action"><span>微网站</span></a></li>
			<li <s:if test='#session.leftControl[4]=="N"'>style="display: none"</s:if> <s:if test='#session.positionId==4'>class="select"</s:if>><a title="互动模块" href="/${oname}/interact/base.action"><span>微互动</span></a></li>
			<li <s:if test='#session.leftControl[5]=="N"'>style="display: none"</s:if> <s:if test='#session.positionId==5'>class="select"</s:if>><a title="微应用" href="/${oname}/page/baseAppIndex.action"><span>微应用</span></a></li>
		    <li <s:if test='#session.leftControl[6]=="N"'>style="display: none"</s:if> <s:if test='#session.positionId==6'>class="select"</s:if>><a title="内容素材" href="/${oname}/content/content_index.action"><span>内容素材</span></a></li>
	</ul>
</div> --%>
