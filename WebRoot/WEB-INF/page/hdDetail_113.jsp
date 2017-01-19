<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<div class="wrap_content">
<div class="switch_tab_div pb10">
    <span><a href="/data/page_hd_type.action?pid=${pid}">互动列表</a>  <i class="gt">&gt;&gt;</i> 新浪分享</span>
   
	<p class="clearfix"></p>
	<form action="/page/hdQuery.action?hdid=113&hdfid=${hdfid }" method="post">
 	访问时间段查询：<input type="text" id="startdate" name="dto.hd113Query.starttime" 
 	onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'enddate\')}',startDate:'%y-%M-%d 00:00:01',dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" 
 	readonly="readonly" value="${dto.hd113Query.starttime}">
 	至：<input type="text" id="enddate" name="dto.hd113Query.endtime" 
 	onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startdate\')}',startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" 
 	readonly="readonly" value="${dto.hd113Query.endtime}">
 	&nbsp;&nbsp;
 	终端：
 	<select name="dto.hd113Query.terminal">
 		<option value="">全部</option>
 		<option value="P" <s:if test='dto.hd113Query.terminal=="P"'> selected="selected" </s:if>>手机客户端</option>
 		<option value="C" <s:if test='dto.hd113Query.terminal=="C"'> selected="selected" </s:if>>PC客户端</option>
 		<option value="A" <s:if test='dto.hd113Query.terminal=="A"'> selected="selected" </s:if>>PAD客户端</option> 
 	</select>
 	&nbsp;&nbsp;
 	审核状态：
 	<select name="dto.hd113Query.status">
 		<option value="">全部</option>
 		<option value="EDT" <s:if test='dto.hd113Query.status=="EDT"'> selected="selected" </s:if>>待审核</option>
 		<option value="CMP" <s:if test='dto.hd113Query.status=="CMP"'> selected="selected" </s:if>>已审核</option>
 		<option value="DEL" <s:if test='dto.hd113Query.status=="DEL"'> selected="selected" </s:if>>审核不过</option>
 	</select>
 	&nbsp;&nbsp;
 	<input type="submit" value="查询" >
 	</form>
</div>
	<ul class="share-list">
		<s:if test="dto.hd113Model.size>0">
			<s:iterator value="dto.hd113Model" var="m">
				<li class="line">
					<div class='wb_face'><img src="${m.imageurl}" width='50px'/></div>
					<div class="wb_detail">
						<div class="wb_text">
							${m.nickname}：${m.content }
						</div>
						<div class="wb_media">
							<s:if test='mimg != null && mimg !=""'>
								<img src="${imgDomain }${m.mimg }" height="80px"/>
							</s:if>
						</div>
						<div style="text-align:right;">
							<span class="left">来自: <s:if test='terminal=="P"'>手机客户端</s:if><s:elseif test='terminal=="C"'>PC客户端</s:elseif><s:elseif test='terminal=="A"'>PAD客户端</s:elseif><s:else>其他客户端</s:else> <s:date name="createtime" format="yyyy-MM-dd HH:mm:ss"/></span>转发：${m.repostsCount } 评论：${m.commentsCount } 赞：${m.attitudesCount }
						</div>
					</div>
				</li>
			</s:iterator>
		</s:if>
		<s:else>
			没有分享记录
		</s:else>
	</ul>
<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>