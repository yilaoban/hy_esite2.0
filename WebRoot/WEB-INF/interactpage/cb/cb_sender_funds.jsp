<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content left_module">
  <ul class="c_switch clearfix">
	  <li><a href="cbhbConfig.action">提现设置</a></li>
	  <li><a href="cbFundsRecord.action">提现记录</a></li>
	  <li class="selected"><a href="cbSenderFunds.action">资金帐号</a></li>
  </ul>
   <div class="toolbar pb10" style="text-align:left;">
  	<form class="formview jNice">
  		姓名:<input type="text" name="name" value="${name }" class="text-medium"/>	
		<input type="submit" class="btn btn-info btn-sm" value="搜索"/>
  	</form>
  	</div>
	<table width="100%" cellspacing="1" cellpadding="1" class="tb_normal">
		<tr><td>姓名</td><td>总金额</td><td>已提取金额</td><td>可提取金额</td><td>查看</td></tr>
		<tr>
			<s:iterator value="dto.list" var="r">
			<tr>
				<td>${r.record.name }</td>
				<td><s:property value="fenToyuan(#r.hbtotal)"/>元</td></td>
				<td><s:property value="fenToyuan(#r.hbused)"/>元</td>
				<td><s:property value="fenToyuan(#r.hbtotal-#r.hbused)"/>元</td>	
				<td><a href="/${oname }/interact/cbFundsRecord.action?sender=${r.hyuid }">提现记录</a></td>	
			</tr>
			</s:iterator>
		</tr>  
  	</table>
  	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>