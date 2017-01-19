<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="tipCon_t">
<form id="" method="post" action="">
	<div>
		<span>跳转设置</span>
	</div>
	<!-- 
	<table id="jump_table">
	
		<tbody>
			<tr class="jumpconstraint">
				<td>如果本题选项选中</td>
				<td>
					<select id="xzBg" name="xzbg" onchange="selisTar()">
						<option value="请选择"selected="selected">请选择</option>
						<s:iterator value="dto.rqo" var="r">
							<option value="${r.id }">${r.content }</option>
						</s:iterator>
					</select>
				</td>
				<td>则跳转到</td>
				<td>
					<select id="xzTo" name="xzto">
						<option value="请选择"selected="selected">请选择</option>
						<s:iterator value="dto.list" var="l">
							<option value="${l.id }">${l.title }</option>
						</s:iterator>
					</select>
				</td>
				<td id="yytarget"></td>
			</tr>
		</tbody>
	</table>
	 -->

	<table id="jump_table">
		<tbody>
		<s:iterator value="dto.rqo" var="r" status="st">
			<tr class="jumpconstraint">
				<td>选项：</td>
				<td>
					<p>${r.content }</p>
				</td>
				<td>&nbsp&nbsp&nbsp&nbsp&nbsp跳转到：</td>
				<td>
					<select id="xzTo${st.count}" name="xzto${st.count}" onchange="inTarget2(${r.id},${st.count})">
						<option value="请选择" selected="selected">请选择</option>
						<s:iterator value="dto.list" var="l">
							<option value="${l.id }" <s:if test="#l.id == #r.target">selected="selected"</s:if>>${l.title }</option>
						</s:iterator>
					</select>
				</td>
				<td id="yytarget"></td>
			</tr>
		</s:iterator>
		</tbody>
	</table>

	<div>
		<a href="javascript:void(0)" onclick="closeFrame()" class="button"/>关闭</a>
	</div>
</form>
</div>
<script type="text/javascript">
function inTarget(){
		var id = $("#xzBg").val();
		var target = $("#xzTo").val();
		if(id =="请选择"){
			window.location.reload();
		}else{
			if(target =="请选择"){
				target = -1;
			}
			$.post("/interact/intarget.action",{"id":parseInt(id),"target":parseInt(target)},function(data){
				if(data==1){
					layer.msg('保存成功!',2,1);
					window.location.reload();
				}else{
					layer.msg('保存失败!',2,3);
				}
			});
		}
}

function inTarget2(id,count){
	var target = $("#xzTo" +count).val();
	if(target =="请选择"){
		layer.msg('请选择!',2,3);
		return;
	}
	$.post("/interact/intarget.action",{"id":parseInt(id),"target":parseInt(target)},function(data){
		if(data==1){
			layer.msg('保存成功!',2,1);
			window.location.reload();
		}else{
			layer.msg('保存失败!',2,3);
		}
	});
}

function selisTar(){
	var id = $("#xzBg").val();
	if(id =="请选择"){
			window.location.reload();
	}else{
		$.post("/interact/selistar.action",{"id":parseInt(id)},function(data){
			if(data==0){
				$("#yytarget").html("");
			}else{
				$("#yytarget").html("已链接到"+data);
			}
		});
	}
}
</script>