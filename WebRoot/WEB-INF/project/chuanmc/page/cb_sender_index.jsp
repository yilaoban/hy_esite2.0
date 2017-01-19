<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
		function cbsenderAcp(cbid,rid){
			var srcString = '/${oname}/interact/cbSenderAccept.action?cbid='+cbid+'&rid='+rid;
			var	title="申请审核";
			layer.open({
					type : 2,
					area : ['450px','400px'],
					title : [title,true],
					content: srcString
				});
		}
		
		$(document).ready(function(){
	 		$("input:radio[name='pizhun']").bind("change",function(){
				var status=$("input:radio[name='pizhun']:checked").val();
				$.post("/${oname}/interact/senderPizhun.action",{"status":status},function(data){
					layer.msg('操作成功！请稍等……', {icon: 6, time: 1500}); 
				});
	 		});
		});
</script>
<div class="wrap_content">
	<div class="toolbar pb10">
		<form action="" class="left">
		<!-- 
			<input type="hidden" name="cbid" value="${cbid }"/>
		 -->
			<label><input type="radio" name="sendType" value="0"  <s:if test="sendType==0">checked="checked"</s:if> onchange="this.form.submit()">全部</label>
			<label><input type="radio" name="sendType" value="1"  <s:if test="sendType==1">checked="checked"</s:if> onchange="this.form.submit()">新增</label>
			<label><input type="radio" name="sendType" value="2"  <s:if test="sendType==2">checked="checked"</s:if> onchange="this.form.submit()">已批准</label>
			<label><input type="radio" name="sendType" value="3"  <s:if test="sendType==3">checked="checked"</s:if> onchange="this.form.submit()">已拒绝</label>
			<label><input type="radio" name="sendType" value="4"  <s:if test="sendType==4">checked="checked"</s:if> onchange="this.form.submit()">取消资格</label>
		</form>
		是否需要批准:
		<label><input type="radio" name="pizhun" value="0" <s:if test="dto.cb.senderAccept==0">checked="checked"</s:if>/>是 </label>
		<label><input type="radio" name="pizhun" value="1" <s:if test="dto.cb.senderAccept==1">checked="checked"</s:if>/>否</label>
	</div>
	<table width="100%" cellspacing="1" cellpadding="1" border="1" class="tb_normal">
		<tr>
			<td>姓名</td><td>性别</td><td>手机</td><td>昵称</td><td>申请时间</td><td>当前状态</td><td>操作</td><td>详情</td>
		</tr>
		<s:iterator value="dto.list" var="s"> 
			<tr>
				<td>${s.record.name }</td>
				<td>${s.record.gender }</td>
				<td>${s.record.phone }</td>
				<td>${s.record.nickname }</td>
				<td><s:date name="record.createtime" format="yyyy-MM-dd HH:mm:ss"></s:date></td>
				<td><s:if test="status==null">新增</s:if><s:elseif test='status=="CMP"'>已批准</s:elseif><s:elseif test='status=="FAL"'>取消资格</s:elseif><s:elseif test='status=="ERR"'>已拒绝</s:elseif></td>
				<td><a href="javascript:cbsenderAcp('${s.owner }','${s.record.id }')">编辑</a></td>
				<td><a href="javascript:showAptDetail('${oname }','${s.record.id}')">查看</a></td>
			</tr>
		</s:iterator>
	</table>
	<%@include file="/WEB-INF/pagechip/pageBar.jsp" %>
</div>