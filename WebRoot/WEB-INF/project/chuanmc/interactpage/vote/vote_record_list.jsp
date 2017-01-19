<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
  	<div class="toolbar pb10">
  	  <ul class="c_switch">
	  <li <s:if test='type==0'>class="selected"</s:if>><a href="/${oname }/interact/vote_record_list.action?voteid=${voteid}&mid=${mid }&type=0&redirectXc=${redirectXc}">微博数据</a></li>
	  <li <s:if test='type==1'>class="selected"</s:if>><a href="/${oname }/interact/vote_record_list.action?voteid=${voteid}&mid=${mid }&type=1&redirectXc=${redirectXc}">微信数据</a></li>
	  <li <s:if test='type==2'>class="selected"</s:if>><a href="/${oname }/interact/vote_record_list.action?voteid=${voteid}&mid=${mid }&type=2&redirectXc=${redirectXc}">用户数据</a></li>
	  <!-- 
	  <li <s:if test='type==-1'>class="selected"</s:if>><a href="/${oname }/interact/vote_record_list.action?voteid=${voteid}&mid=${mid }&type=-1&redirectXc=${redirectXc}">匿名数据</a></li>
	   -->
	  <li><a href="/${oname }/interact/vote_option_data.action?voteid=${voteid}&mid=${mid }&redirectXc=${redirectXc}">投票结果</a></li>
	  </ul>
	  <s:if test='type==1'>
	  	<a href="/${oname}/interact/voteMoveToGroup.action?voteid=${voteid}&gz_i=1" class="button">移动到组</a>
	  </s:if>
	  <s:if test="redirectXc!=0">
		<a href="/${oname }/interact/edit_xcLottery.action?xcid=${redirectXc}" class="return" title="返回"></a>
	  </s:if>
	  <s:else>
		  <a href="/${oname }/interact/index.action?mid=${mid }" class="return" title="返回"></a>
	  </s:else>
	</div>
        <%-- 
        <form id="nickname" name="nickname" method="get">
		 	<input type="hidden" name="type" value="${type}">
			<input name="queryDto.nickname" value="${queryDto.nickname}"  placeholder="输入昵称筛选">
			<input type="hidden" name="mid" value="${mid }"><br>
			<input type="hidden" name="voteid" value="${voteid}">
			投票时间 <input id="starttime" type="text"
					name="queryDto.startTime" class="Wdate"
					value="${queryDto.startTime }"
					onFocus="WdatePicker({startDate:'%y-%M-%d 00:00:01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
					readonly="readonly" />至 <input id="endtime" type="text"
					name="queryDto.endTime" class="Wdate" value="${queryDto.endTime }"
					onFocus="WdatePicker({startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
					readonly="readonly" />
			<input type="submit" value="查询" /> 
		</form>
		--%>
	<div>
    共${dto.pager.totalCount }条
		<table class="tb_normal" width="100%" border="1" cellspacing="0"
			cellpadding="0">
			<tr>
				<th>用户名</th>
				<s:if test="type == 0">
					<th>微博昵称</th>
				</s:if>
				<s:elseif test="type == 1">
					<th>微信昵称</th>
				</s:elseif>
				<s:elseif test="type == 2">
					<th>微博昵称</th>
					<th>微信昵称</th>
				</s:elseif>
				<th>
					IP
				</th>
				<th>
					投票时间
				</th>
				<th>
					投票结果
				</th>
			</tr>
			<s:iterator value="dto.list" var="l" status="s">
				<tr>
					<td align="center">${l.username }</td>
					<s:if test="type == 0 || type == 1">
						<th>${l.nickname }</th>
					</s:if>
					<s:elseif test="type == 2">
						<th>${l.nickname }</th>
						<th>${l.wxnickname }</th>
					</s:elseif>
					<td align="center">
						${l.ip }
					</td>
					<td align="center">
						<s:date name="createtime" format="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td align="center">
						${l.content}
					</td>
				</tr>
			</s:iterator>
		</table>
		<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
	</div>
</div>