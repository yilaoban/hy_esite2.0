<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content left_module">
	<form action="/${oname }/setting/updateRmbRule.action" id="form1" class="formview jNice" method="post">
	<input type="hidden" name="rule.id" value="${rule.id}">
	<div class="toolbar pb10">
  	<ul class="c_switch">
	   <li class="selected"><a href="">编辑规则</a></li>
	   </ul>
	   <a href="javascript:window.history.back()" class="return" title="返回"></a>
	</div>
	<div>
		<dl>
		 	<dt>充值金额：</dt>
			<dd>
				<input type="text" name="rule.rmb" value="${rule.rmb }" onkeyup="this.value = this.value.replace(/\D/g,'');"/>分
			</dd>
		</dl>
		<dl>
		 	<dt>赠送金额：</dt>
			<dd>
				<input type="text" name="rule.getrmb" value="${rule.getrmb }" onkeyup="this.value = this.value.replace(/\D/g,'');"/>分
			</dd>
		</dl>
		<dl>
			<dt>赠送积分：</dt>
			<dd>
				<input type="text" name="rule.getjf" value="${rule.getjf }" onkeyup="this.value = this.value.replace(/\D/g,'');"/>积分
			</dd>
		</dl>
	</div>
	<dl>
		<dt></dt>
		<dd>
			<input type="submit" class="btn btn-primary" value="保存"/>
		</dd>
	</dl>
	</form>
</div>
