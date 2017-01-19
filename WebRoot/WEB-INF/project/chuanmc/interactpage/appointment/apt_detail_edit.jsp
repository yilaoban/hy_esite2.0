<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!-- 可用于线下签到的申请记录,可更新 -->
<script type="text/javascript">
	function formsub(){
		$.ajax({
	        cache: true,
	        type: "POST",
	        url:'/${oname}/interact/uptAptRecord.action',
	        data:$('#form1').serialize(),// 你的formid
	        async: false,
	        error: function(request) {
	            layer.alert("Connection error",0);
	        },
	        success: function(data) {
	            if(data==1){
	            	layer.msg('更新成功!', {icon: 6, time: 2000});
	            	closeFrame();
	            }else{
	            	layer.msg('更新失败!', {icon: 5, time: 2000});
	            }
	        }
	    });
	}
</script>

<div class="wrap_content left_module">
	<ul class="nav nav-tabs" role="tablist">
    	<li role="presentation" class="active"><a href="#showTab" aria-controls="showTab" role="tab" data-toggle="tab">查看</a></li>
    	<li role="presentation"><a href="#editTab" aria-controls="editTab" role="tab" data-toggle="tab">编辑</a></li>
	</ul>
  <div class="tab-content">
    <div role="tabpanel" class="tab-pane active" id="showTab">
    	<table width="100%" border="1" cellspacing="1" cellpadding="1" class="tb_normal">
			<tbody>
				<s:iterator value="record.maps" var="m" status="st">
					<tr align="center">
						<s:if test="record.id==0">
				    		<td>${m.name}</td><td>&nbsp;</td>
				    	</s:if>
				    	<s:else>
							<s:if test='#m.stype=="I"'>
								<td>${m.name}</td><td><img src="${record.values[st.index] }" height="100px"/></td>
							</s:if>
							<s:else>
								<td>${m.name}</td><td>${record.values[st.index] }</td>
							</s:else>
				    	</s:else>
					</tr>
				</s:iterator>	    	
			</tbody>
		</table>
		<input type="button" onclick="closeFrame()" value="关闭"/>
    </div>
    <div role="tabpanel" class="tab-pane" id="editTab">
    	<s:if test="record.id==0">
    		<div>
    		<br>
    		此用户没有填写申请表单,无内容可修改
    		<input type="button" onclick="closeFrame()" value="关闭"/>
    		</div>
    	</s:if>
    	<s:else>
	    	<form action="" id="form1">
	    	<input type="hidden" name="record.id" value="${record.id }"/>
	    	<table width="100%" border="1" cellspacing="1" cellpadding="1" class="tb_normal">
				<tbody>
					<s:iterator value="record.maps" var="m" status="st">
						<tr align="center">
							<td>${m.name}</td><td><input name="record.${m.mapping }" value="${record.values[st.index] }"/></td>
						</tr>
					</s:iterator>	    	
				</tbody>
			</table>
			<input type="button" onclick="closeFrame()" value="关闭"/>
			<input type="button" onclick="formsub()" value="保存"/>
	    	</form>
    	</s:else>
    </div>
  </div>
</div>