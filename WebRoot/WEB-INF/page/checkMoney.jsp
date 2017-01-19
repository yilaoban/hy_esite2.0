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

<script type="text/javascript">
	function checkMoneySubmit(){
		var total = $('#total').val();
		$('#total_1').val(total * 100);
		$('#myform').submit();
	}
</script>
<form action="money_check_sub.action"  enctype="multipart/form-data" method="post" class="formview jNice" id="myform">
	<input type="hidden" name="aid" value="${aid }">
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
			<input id="total" type="text" class="text-medium"  value="${totalshow}"  onkeyup="if(this.value==this.value2)return;if(this.value.search(/^\d*(?:\.\d{0,2})?$/)==-1)this.value=(this.value2)?this.value2:'';else this.value2=this.value;"/>元
			<input type="hidden" name="total" id="total_1">
		</dd>
	</dl>
	<dl>
		<dt>原因</dt>
		<dd>
			<textarea name="reason" style="width:200px;">${hongbao.reason }</textarea>
		</dd>
	</dl>
	<!-- 
		<dl>
			<dt>公众号</dt>
			<dd>
				<select name="mpid">
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
			<input type="button" value="提交审核" class="btn btn-primary" onclick="checkMoneySubmit()">
			<input type="button" value="取消" class="btn" onclick="closeFrame()">
		</dd>
	</dl>
</form>

