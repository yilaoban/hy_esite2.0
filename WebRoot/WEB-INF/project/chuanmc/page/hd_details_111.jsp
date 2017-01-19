<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="frame_content">
<s:if test="dto.sinausers.size>0">
</s:if>
	<form>
		<input type="hidden" name="featureid" value="${featureid }"/>
		<input type="hidden" name="sitegroupid" value="${sitegroupid }"/>
		授权时间段：
		<input type="text" name="siftDto.starttime" id="starttime" class="Wdate half"	 onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\')}',dateFmt:'yyyy-MM-dd 00:00:00',onpicked:function(){shareEndTime.focus();},autoPickDate:true})"  readonly="readonly" value="${ siftDto.starttime}"/>
						至
		<input type="text" name="siftDto.endtime" id="endtime"	class="Wdate half"  onFocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\')}',dateFmt:'yyyy-MM-dd 23:59:59',autoPickDate:true})"  readonly="readonly" value="${siftDto.endtime }"/>
		终端：<select name="siftDto.terminal">
				<option value="-1" <s:if test='siftDto.terminal=="-1"'>selected="selected"</s:if>>全部</option>
				<option value="C" <s:if test='siftDto.terminal=="C"'>selected="selected"</s:if>>PC</option>
				<option value="A" <s:if test='siftDto.terminal=="A"'>selected="selected"</s:if>>PAD</option>
				<option value="P" <s:if test='siftDto.terminal=="P"'>selected="selected"</s:if>>PHONE</option>
			</select>
		<input type="submit" value="搜索"/>
	</form>
	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1">
		<thead>
		<tr>
			<th>微博昵称</th>
			<th>授权时间</th>
			<th>授权IP</th>
			<th>终端</th>
		</tr>
		</thead>
		<tbody>
			<s:iterator value="dto.sinausers" var="d">
			<tr>
				<td>${d.nickname }</td>
				<td><s:date name="createtime" format="yyyy-MM-dd HH:mm"/></td>
				<td>${d.ip }</td>
				<td><s:if test='terminal=="C"'>PC</s:if>
					<s:elseif test='terminal=="A"'>PAD</s:elseif>
					<s:elseif test='terminal=="P"'>PC</s:elseif>
				</td>
			</tr>	
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar2.jsp" %>
<s:else>
	没有记录!
</s:else>
</div>