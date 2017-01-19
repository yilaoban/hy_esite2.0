<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content">
<div class="toolbar pb10">
	<a href="javascript:void(0);" onclick="loadmbk('${oname}','W')" class="addButton"/>+ 创建微网站</a>
</div>
<s:if test="dto.list.size>0">
	<s:iterator value="dto.list" var="s" status="sta">
	<div class="sitegroup_List sg_${s.id }" style="max-height: 150px">
		<div style="float:left">
			<img  alt="" <s:if test='#s.wps.pic != ""'>src="${s.wps.pic }"</s:if><s:else>src="/images/nopic.png"</s:else>  width="150px"/>
		</div>
		<div class="sitegroup_List_left2">
		<!-- W-微现场  J-集人气  N-普通 V-投票 G-微官网 B-微社区  L-砸金蛋  Z-大转盘 C-合伙人 -->
		   <div class="title"><b>${s.groupname}<s:if test='#s.istemplate=="Y"'><span class="glyphicon glyphicon-star" aria-hidden="true" title="网站模板"></span></s:if></b> <a href="javascript:void(0)" onclick="changeSiteName1(${s.id},'${s.groupname }')"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a></div>
			<div class="updatetime"><s:date name="updatetime" format="yyyy-MM-dd HH:mm"/> 更新</div>
	    	<div>分享标题：${s.wps.title}</div>
	    	<div>分享描述：${s.wps.description}</div>
		    <div class="operations">
		    	<s:iterator value="#s.site" var="a" status="sta">
		    		<a href="/${oname }/page/pageconfig_new.action?siteid=${a.id }&stype=${a.type }"><span title="编辑" class="glyphicon glyphicon-edit"></span></a>
		    		<i class="split">|</i>
		        </s:iterator>
		        <a href="/${oname}/data/site_chart.action?siteid=${s.site[0].id}&source=EWW"><span class="glyphicon glyphicon-stats" title="效果"></span></a>
		        <i class="split">|</i>
		        <s:if test='#s.istemplate!="Y"'><a href="javascript:void(0)" onclick="delete_site(${s.id})"><span class="glyphicon glyphicon-trash" title="删除"></span></a></s:if>
		    </div>
	    </div>
	</div>
	</s:iterator>
<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</s:if>
<s:else>
	<p style="font-size:20px;text-align:center;">点击创建站点， 让用户知道你。</p>
</s:else>
</div>