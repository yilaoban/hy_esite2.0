<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	function updateJfRule(){
		$('#updateform').submit();
	}
	
</script>
<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
  <h4 class="modal-title" id="myModalLabel">修改积分规则</h4>
</div>
<div class="modal-body"> 
	<form action="/${sessionScope.account.owner.entity}/bbs/updateJfRule.action" method="post" id="updateform">
		<input type="hidden" name="ruleid" value="${ruleid }"/>
		<p>
			动作名称： 
			<select name="action" id="action" disabled="disabled">
				<option value ="0">请选择动作名称</option>
				<option value ="1" <s:if test="dto.jfRule.action == 1">selected="selected"</s:if>>发表主题</option>
				<option value ="2" <s:if test="dto.jfRule.action == 2">selected="selected"</s:if>>发表回复</option>
				<option value ="3" <s:if test="dto.jfRule.action == 3">selected="selected"</s:if>>顶</option>
				<option value ="4" <s:if test="dto.jfRule.action == 4">selected="selected"</s:if>>踩</option>
				<option value ="5" <s:if test="dto.jfRule.action == 5">selected="selected"</s:if>>主题被置顶</option>
			</select>
		</p>
		<p>
			增加积分：<input type="text" name="jifen" id="jifen" onkeyup="this.value = this.value.replace(/\D/g,'');" value="${dto.jfRule.jifen}"/>分
		</p>
	</form>
</div>
<div class="modal-footer">
  <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
  <button type="button" class="btn btn-primary" onclick="updateJfRule()" data-dismiss="modal">保存</button>
</div>