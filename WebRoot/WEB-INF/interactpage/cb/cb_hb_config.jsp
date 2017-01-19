<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
function formSub(){
	$.ajax({
        cache: true,
        type: "POST",
        url:"/${oname}/interact/cbhbSub.action",
        data:$('#form1').serialize(),// 你的formid
        async: false,
        error: function(request) {
            layer.alert("Connection error",0);
        },
        success: function(data) {
        	if(data==1){
            	layer.msg('操作成功!', {icon: 6, time: 2000});
            }else{
            	layer.msg('操作失败!', {icon: 5, time: 2000});
            }
        }
    });
}
</script>
<div class="wrap_content left_module">
<div class="toolbar pb10">
  <ul class="c_switch">
	  <li class="selected"><a href="cbhbConfig.action">提现设置</a></li>
	  <li><a href="cbFundsRecord.action">提现记录</a></li>
	  <li><a href="cbSenderFunds.action">资金帐号</a></li>
  </ul>
 </div>
  <div>
	<form id="form1" class="formview jNice">
		<dl>
	 	<dt>祝福语</dt>
	 	<dd>
	 		<input type="text" class="text-medium" value="${hbc.wishing }" name="hbc.wishing"/>
		</dd>
		</dl>
		<dl>
	 	<dt>备注</dt>
	 	<dd>
	 		<input type="text" class="text-medium" value="${hbc.remark }"/ name="hbc.remark">
		</dd>
		</dl>
		<dl>
	 	<dt>活动名称</dt>
	 	<dd>
	 		<input type="text" class="text-medium" value="${hbc.act_name }" name="hbc.act_name"/>
		</dd>
		</dl>
		<dl>
			<dt></dt>
			<dd>
	  		<input type="button" class="btn btn-primary" value="保存" onclick="formSub()"/>
			</dd>
		</dl>
	</form>
</div>
</div>