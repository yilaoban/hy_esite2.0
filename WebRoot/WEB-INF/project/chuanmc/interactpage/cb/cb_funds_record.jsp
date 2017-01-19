<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
function hbCheck(id,status) {
	var srcString = '/${oname}/interact/cbHongBaoSendCheck.action?cbSenderid='+id +"&stat=" + status;
	var	title="审核";
	layer.open({
			type : 2,
			area : ['450px','235px'],
			title : [title,true],
			content: srcString
		});
}
</script>

<div class="wrap_content">
  <ul class="c_switch clearfix">
	  <li><a href="cbhbConfig.action">提现设置</a></li>
	  <li  class="selected"><a href="cbFundsRecord.action">提现记录</a></li>
	  <li><a href="cbSenderFunds.action">资金帐号</a></li>
  </ul>
  <div class="toolbar pb10" style="text-align:left;">
  	<form class="formview jNice">
  		姓名:<input type="text" name="name" value="${name }" class="text-medium"/>	
  			提现时间段:
	  	<input type="text" placeholder="请选择开始时间" id="start" value="${starttime }" name="starttime" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'end\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate">
			至
		<input type="text" placeholder="请选择结束时间" id="end" value="${endtime }"  name="endtime" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'start\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate">
		<input type="submit" class="btn btn-info btn-sm" value="搜索"/>
  	</form>
  	</div>
	<table width="100%" cellspacing="1" cellpadding="1" class="tb_normal">
		<tr><td>姓名</td><td>提现时间</td><td>提现金额</td><td>操作</td></tr>
			<s:iterator value="dto.hbRecord" var="r">
			<tr>
			<td>${r.senderName }</td>
			<td><s:date name="#r.createtime" format="yyyy-MM-dd HH:mm:ss"/></td>
			<td><s:property value="fenToyuan(#r.num)"/>元</td>
			<td>
			<a href="javascript:void(0);" onclick="hbCheck(${r.id},${r.status})">
				<s:if test="#r.status == 0">未审核</s:if>
				<s:if test="#r.status == 1">审核通过</s:if>
				<s:if test="#r.status == -1">审核不通过</s:if>
			</a>
			</td>
			</tr>
			</s:iterator>
  	</table>
  	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>