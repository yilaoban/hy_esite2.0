<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
$(document).ready(function(){
	$("#saveBtn").click(function(){
		var name=$("#name").val();
		var acid=$("#acid").val();
		if(name==""){
			layer.msg('请填写渠道名称！', {icon: 5, time: 2000});
			return ;
		}
		if(acid<=0){
			layer.msg('请选择渠道经理！', {icon: 5, time: 2000});
			return ;
		}
		$("#form1").submit();
	});
	
	
});
</script>
<div class="wrap_content left_module">
	<form action="saveWay.action" id="form1" class="formview jNice" method="post">
	<input type="hidden" name="way.caid" value="${caid }"/>
	<div class="toolbar pb10">
	  	<ul class="c_switch">
			<li class="selected"><a href="#">新增渠道</a></li>
		</ul>
		<a href="javascript:window.history.back()" class="return" title="返回"></a>
	</div>
	<div>
		<dl>
		 	<dt>名称</dt>
			<dd>
				<input type="text" name="way.name" id="name"/>
			</dd>
		</dl>
		<dl>
		 	<dt>简介</dt>
			<dd>
				<textarea  maxlength="200" name="way.hydesc" id="hydesc"></textarea>
			</dd>
		</dl>
		<dl>
		 	<dt>渠道经理</dt>
			<dd>
				<select name="way.acid" id="acid">
						<option value="-1">-请选择-</option>
					<s:iterator value="accounts" var="a">
						<option value="${a.id }">${a.username }</option>
					</s:iterator>
				</select>
			</dd>
		</dl>
		<dl>
		 	<dt>联系人</dt>
			<dd>
				<input name="way.realname" id="realname"></input>
			</dd>
		</dl>
		<dl>
		 	<dt>联系邮箱</dt>
			<dd>
				<input type="text" name="way.email" id="email"/>
			</dd>
		</dl>
		<dl>
		 	<dt>联系电话</dt>
			<dd>
				<input  maxlength="11" name="way.telphone" id="telphone"></input>
			</dd>
		</dl>
		<dl>
		 	<dt>地址</dt>
			<dd>
				<textarea  maxlength="200" name="way.address" id="address"></textarea>
			</dd>
		</dl>
	</div>
	<dl>
		<dt></dt>
		<dd>
			<button type="button" class="btn btn-primary" id="saveBtn">保存</button>
		</dd>
	</dl>
	</form>
</div>