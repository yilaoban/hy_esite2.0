<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div>
	<div class="toolbar pb20">
	<form action="" method="post">
	<div class="left">
				开始时间:<input type="text" name="starttime" value="${starttime }" placeholder="请选择开始时间"  id="start" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'end\')}',dateFmt:'yyyy-MM-dd HH:mm:ss',lang:'zh-cn'})" class="Wdate">
				结束时间:<input type="text" name="endtime" value="${endtime }" placeholder="请选择结束时间"  id="end" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'start\')}',dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:function(){endchange()},lang:'zh-cn'})" class="Wdate">
				</div>
				<input type="submit" class="btn btn-primary btn-sm right" value="查询"/>
	</form>
	</div>
	县级渠道:${dto.dl.name }
	<table width="100%"  class="tb_normal" border="1" cellspacing="1" cellpadding="1" >
			<tr>
				<td>下线名称</td>
				<td>发展时间</td>
			</tr>
		<s:iterator value="dto.cpzs" var="r">
			<tr>
				<td>${r.name }</td>
				<td><s:date name="#r.createtime" format="yyyy-MM-dd"/></td>
			</tr>
		</s:iterator>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>