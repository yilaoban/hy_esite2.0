<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li <s:if test='mtype==0'>class="selected"</s:if>><a href="/${oname }/interact/research_record_list.action?searchid=${searchid}&mid=${mid }&mtype=0">微博数据</a></li>
	  <li <s:if test='mtype==1'>class="selected"</s:if>><a href="/${oname }/interact/research_record_list.action?searchid=${searchid}&mid=${mid }&mtype=1">微信数据</a></li>
	  <li <s:if test='mtype==2'>class="selected"</s:if>><a href="/${oname }/interact/research_record_list.action?searchid=${searchid}&mid=${mid }&mtype=2">用户数据</a></li>
	  <li <s:if test='mtype==-1'>class="selected"</s:if>><a href="/${oname }/interact/research_record_list.action?searchid=${searchid}&mid=${mid }&mtype=-1">匿名数据</a></li>
	  <li ><a href="/${oname }/interact/research_record_data.action?searchid=${searchid}&mid=${mid }">调研结果</a></li>
	  </ul>
	  <s:if test='mtype==1'>
	  	<a href="/${oname}/interact/researchMoveToGroup.action?searchid=${searchid}&gz_i=1" class="button">移动到组</a>
	  </s:if>
	  
	  <a href="/${oname }/interact/index.action?mid=10006" class="return"></a>
	</div>
   <%-- 
   <div>
   		<s:if test="mtype!=2">
	        <form id="nickname" name="nickname" method="get" action="">
			 <input type="hidden" name="searchid" value="${searchid}">
			 <input type="hidden" name="mtype" value="${mtype}">
			 <input type="hidden" name="mid" value="${mid }">
			 <input name="nickname" value="${nickname}" placeholder="昵称筛选">
			 <s:if test="mtype==1">
			 	来源：
				 <select name="source1">
				 	<option value="">不限</option>
					<s:iterator value="dto.sourceList" var="f">
					  <option value="${f.source}" <s:if test="source1==source">selected="selected"</s:if>>${f.source}</option>
					</s:iterator>  
				 </select>
			 </s:if>
			 <input type="submit" value="搜索" />
			 </form>
		 </s:if>
   </div>
   --%>
	<div>
    共${dto.pager.totalCount }条
		<table class="tb_normal" width="100%" border="1" cellspacing="0"
			cellpadding="0">
			<tr>
				<th>用户名</th>
				<s:if test="mtype == 0">
					<th>微博昵称</th>
				</s:if>
				<s:elseif test="mtype == 1">
					<th>微信昵称</th>
				</s:elseif>
				<s:elseif test="mtype == 2">
					<th>微博昵称</th>
					<th>微信昵称</th>
				</s:elseif>
				<th>
					IP
				</th>
				<th>
					调研时间
				</th>
				<th>
					调研结果
				</th>
			</tr>
			<s:iterator value="dto.list" var="l" status="s">
				<tr>
					<td align="center">
						${l.username }
					</td>
					<s:if test="mtype == 0 || mtype == 1">
						<th>${l.nickname }</th>
					</s:if>
					<s:elseif test="mtype == 2">
						<th>${l.nickname }</th>
						<th>${l.wxnickname }</th>
					</s:elseif>
					<td align="center">
						${l.ip }
					</td>
					<td align="center">
						<s:date name="createtime" format="yyyy-MM-dd HH:mm:ss"></s:date>
					</td>
					<td align="center">
						<a href="/${oname }/interact/surveyresult_list.action?recordid=${l.id}&searchid=${searchid}&mtype=${mtype}"  >查看全部问题</a>
					</td>
				</tr>
			</s:iterator>
		</table>
		<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
	</div>
