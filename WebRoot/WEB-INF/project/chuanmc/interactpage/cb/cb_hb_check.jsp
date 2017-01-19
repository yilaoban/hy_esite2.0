<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function updateCbSendCheck(){
		var status=$('input:radio[name="status"]:checked').val();
		if(status){
			$.post("/${oname}/interact/updateCbSendCheck.action",{"stat":status,"cbSenderid":"${cbSenderid}"},function(data){
				if(data > 0){
					window.parent.location.reload();
				}
			});
		}
		
	}
</script>
	<form action="/${oname}/interact/updateCbSendCheck" method="post" id="myfrom2" class="formview jNice">
		<dl>
		 	<dt></dt>
			<dd>
				<label class="forradio"><input type="radio" name="status" value="1" <s:if test='stat == 1'>checked="checked"</s:if> />审核通过</label>
				<label class="forradio"><input type="radio" name="status" value="-1" <s:if test='stat == -1'>checked="checked"</s:if> />审核不通过</label>
			</dd>
		</dl>
		<dl>
		 	<dt></dt>
			<dd>
	 			<button type="button" class="btn btn-primary" onclick="updateCbSendCheck()">保存</button>
				<button type="button" class="btn btn-default" onclick="closeFrame()">关闭</button>
			</dd>
		</dl>
	</form>
	  
