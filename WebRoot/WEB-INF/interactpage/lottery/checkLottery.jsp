<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test='result != null && result != "" '>
	<s:if test='result == "Y"'>
		<script>
			parent.layer.load(2,2);
			setTimeout('parent.layer.msg("操作成功!",2,1)',2000);
			var i = parent.layer.getFrameIndex();
			setTimeout('window.top.location.reload()',4000);
		</script>
	</s:if>
	<s:else>
		<script>parent.layer.msg('${result}',2,3);</script>
	</s:else>
</s:if>
<form action="lottery_check_sub.action"  enctype="multipart/form-data" method="post" class="formview jNice">
	<input type="hidden" name="lid" value="${lid }">
	<input type="hidden" name="ckid" value="${hongbao.id}">
	<dl>
		<s:if test="hongbao != null">
			<dt>审核状态</dt>
			<dd>
				<s:if test="hongbao.status == 0">未审核</s:if>
				<s:if test="hongbao.status == 1">审核通过</s:if>
				<s:if test="hongbao.status == 2">审核不通过</s:if>
			</dd>
		</s:if>
	</dl>
	<dl>
		<dt>预算金额</dt>
		<dd>
			<input id="total" type="text" class="text-medium" name="total" value="${total}"  onkeyup="this.value = this.value.replace(/\D/g,'');"/>分
		</dd>
	</dl>
	<dl>
		<dt>原因</dt>
		<dd>
			<textarea name="reason" style="width:200px">${hongbao.reason }</textarea>
		</dd>
	</dl>
	<!-- 
		<dl>
			<dt>公众号</dt>
			<dd>
				<select name="idStr">
					<s:iterator value="wpList" var="f">
					  <option value="${f.id}" <s:if test="#f.id == hongbao.mpid">selected="selected"</s:if>>${f.nick_name}</option>
					</s:iterator>  
				</select>
			</dd>
		</dl>
	 -->
	<dl>
		<dt></dt>
		<dd>
			<input type="submit" value="提交审核">
			<input type="button" value="取消" onclick="closeFrame()">
		</dd>
	</dl>
</form>

