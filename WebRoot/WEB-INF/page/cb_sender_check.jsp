<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function senderSub(){
		$.ajax({
	        cache: true,
	        type: "POST",
	        url:"/${oname}/interact/cbSenderSub.action",
	        data:$('#form1').serialize(),// 你的formid
	        async: false,
	        error: function(request) {
	            layer.alert("Connection error",0);
	        },
	        success: function(data) {
	        	 if(data>0){
		            	layer.msg('操作成功！请稍等……', {icon: 6, time: 1500}, function(){
							window.parent.location.reload();
						}); 
		          }else{
		            	layer.msg('操作失败！', {icon: 5, time: 2000});
		          }
	        }
	    });
		
	}

</script>
<table width="100%" border="1" cellspacing="1" cellpadding="1" class="tb_normal">
	<tbody>
			<s:iterator value="dto.sender.record.maps" var="m" status="st">
				<tr align="center">
					<td>${m.name}</td><td>${dto.sender.record.list[st.index] }</td>
				</tr>
			</s:iterator>
	</tbody>
	</table>
	<form action="" method="post" id="form1" class="formview jNice">
	<!-- 
	 -->
		<input type="hidden" name="rid" value="${dto.sender.record.id }"/>
		<dl>
			<input type="radio" name="status" value="CMP" <s:if test='dto.sender.status=="CMP"'>checked="checked"</s:if>/>批准成为合伙人 
			<input type="radio" name="status" value="ERR" <s:if test='dto.sender.status=="ERR"'>checked="checked"</s:if>/>忽略此申请
		<s:if test='dto.sender.status=="CMP"'>
			<input type="radio" name="status" value="FAL" <s:if test='dto.sender.status=="FAL"'>checked="checked"</s:if>/>撤销合伙人资格
		</s:if>
		</dl>
		<dl>
			<div>理由</div>
			<textarea name="reason">${dto.sender.reason }</textarea>
		</dl>
		<dl>
			<dt></dt>
			<dd>
				<input type="button" class="btn btn-primary" value="保存" onclick="senderSub();"/>
				<input type="button" class="btn" value="取消" onclick="closeFrame()"/>
			</dd>
		</dl>
	</form>
