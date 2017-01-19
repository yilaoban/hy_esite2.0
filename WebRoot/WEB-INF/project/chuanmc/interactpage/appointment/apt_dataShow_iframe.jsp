<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<script type="text/javascript">
	function formSub(){
			if(checkform()){
			$.ajax({
		        cache: true,
		        type: "POST",
		        url:"apt_dataShow_sub.action",
		        data:$('#form1').serialize(),// 你的formid
		        async: false,
		        error: function(request) {
		            layer.alert("Connection error",0);
		        },
		        success: function(data) {
		            if(data=="Y"){
		            	alert("操作成功!",0);
		            	setTimeout(window.top.location.reload(),2000);
		            	
		            }else{
		            	alert("操作失败!",0);
		            }
		        }
		    });
			}
	}
	function checkform(){
		v1=$("#s1").find("option:selected").text();
		v2=$("#s2").find("option:selected").text();
		v3=$("#s3").find("option:selected").text();
		v4=$("#s4").find("option:selected").text();
		var ary = new Array(v1,v2,v3,v4);
		var nary=ary.sort();
		for(var i=0;i<ary.length;i++){
			if (nary[i]==nary[i+1]){
				if(nary[i]!="请选择"){
					alert("有重复列："+nary[i]);
					return false;
				}
			}
		}	
		return true;	
	}
</script>
<div>

	为了方便显示您关注的字段,可以选择最多四个字段在列表中显示.
	<form action="" id="form1">
	<input type="hidden" name="aptid" value="${aptid }"/>
	<table width="80%" autocomplete="off">
		<tr>
			<td>第一列</td>
			<td>第二列</td>
			<td>第三列</td>
			<td>第四列</td>
		</tr>
		<tr>
			<td>
				<select name="mapping[0]" autocomplete="off" id="s1">
				<option>请选择</option>
				<s:iterator value="dto.mapping" var="m">
				<option value="${m.mapping }" <s:if test="dataShow==1">selected="selected"</s:if>>${m.name }</option>
				</s:iterator>
				</select>
			</td>
			<td>
				<select  name="mapping[1]" autocomplete="off" id="s2">
				<option>请选择</option>
				<s:iterator value="dto.mapping" var="m">
				<option value="${m.mapping }" <s:if test='dataShow==2'>selected="selected"</s:if>>${m.name }</option>
				</s:iterator>
				</select>
			</td>
			<td>
				<select  name="mapping[2]" autocomplete="off"  id="s3">
				<option>请选择</option>
				<s:iterator value="dto.mapping" var="m">
				<option value="${m.mapping }" <s:if test='dataShow==3'>selected="selected"</s:if>>${m.name }</option>
				</s:iterator>
				</select>
			</td>
			<td>
				<select  name="mapping[3]" autocomplete="off"  id="s4">
				<option>请选择</option>
				<s:iterator value="dto.mapping" var="m">
				<option value="${m.mapping }" <s:if test='dataShow==4'>selected="selected"</s:if>>${m.name }</option>
				</s:iterator>
				</select>
			</td>
		</tr>
	</table>
	<input type="button" class="btn btn-primary" value="保存" onclick="formSub()"/>
	</form>
</div>
