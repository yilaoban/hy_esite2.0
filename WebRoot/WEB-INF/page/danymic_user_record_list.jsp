<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="wrap_content">
	<div>
		<form id="danymicform" name="danymicform" method="get"
			action="danymic_record.action">
			<input type="hidden" name="sgid" value="${sgid}">
			<span> 互动时间 <input id="starttime" type="text"
					name="siftDto.startTime" class="Wdate"
					value="${siftDto.startTime }"
					onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\')}',startDate:'%y-%M-%d 00:00:01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
					readonly="readonly" />至 <input id="endtime" type="text"
					name="siftDto.endTime" class="Wdate" value="${siftDto.endTime }"
					onFocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\')}',startDate:'%y-%M-%d 23:59:59',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
					readonly="readonly" /> 互动次数<input name="siftDto.dcount" value="${siftDto.dcount }" onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="8" size="14">
				互动类型 <select id="dyaction" name="siftDto.action"
					style="width: 120px;" />
					<option value="-1" >
							全部
					</option>
					<s:iterator value="dto.alist" var="l" status="s">
					    <option value="${l.action}" <s:if test="#attr.siftDto.action==#attr.l.action"> selected="selected"</s:if>>
							${l.name}
						</option>
				    </s:iterator>
				    
					
				</select> <input type="submit" value="查询" /> </span>
			<!-- <input type="button"  onclick="domainReport('FOR')" value="下载"/> -->
		</form>
		<form id="nickname" name="nickname" method="get"
			action="danymic_record_nickname.action">
		 <input type="hidden" name="sgid" value="${sgid}">
		 <input name="nickname" value="${nickname}">
		 <input type="submit" value="搜索" />
		 </form>
	</div>
	<div>
	   <p class="clearfix"></p>
    共${dto.pager.totalCount }条
		<table class="tb_normal" width="100%" border="1" cellspacing="0"
			cellpadding="0">
			<tr>
				<th>
					微博昵称
				</th>
				<th>
					最近一次互动时间
				</th>
				<th>
					互动次数
				</th>
				<th>
					互动类型
				</th>
				<th>
					互动IP
				</th>
				<th>
					互动区域
				</th>
				<th>
					互动终端
				</th>
				<th>
					操作
				</th>
			</tr>
			<s:iterator value="dto.list" var="l" status="s">
				<tr>
					<td align="center">
						<a href="http://weibo.com/u/${l.wbuid}" target="_blank" >${l.nickname }</a>
					</td>
					<td align="center">
						<s:date name="lastactiontime" format="yyyy-MM-dd HH:mm:ss"></s:date>
					</td>
					<td align="center">
						${l.dcount}
					</td>
					<td align="center">
					    ${l.actiontype}
					</td>
					<td align="center">
						${l.ip }
					</td>
					<td align="center">
						${l.area }
					</td>
					<td align="center">
						<s:if test='#l.terminal=="A"'>PAD</s:if><s:elseif test='#l.terminal=="C"'>PC</s:elseif><s:elseif test='#l.terminal=="P"'>PHONE</s:elseif>
					</td>
					<td align="center"> <a href="javascript:void(0)" onclick="danymicRecordDetail(${sgid},'${l.wbuid}',${l.siteid},'${l.action}','${siftDto.startTime}','${siftDto.endTime}')">查看详情</a></td>
				</tr>
			</s:iterator>
		</table>
		<%@include file="/WEB-INF/pagechip/pageBar.jsp"%>
	</div>
</div>