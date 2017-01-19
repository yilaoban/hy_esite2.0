<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
			function formsub(){
				var layerid=layer.confirm('确定修改吗?',function(index){
					document.form1.submit();
					layer.close(index);
				});
			}
</script>
<div align="center">
		<s:if test="dto.success">
			<form action="subeditFile.action" method="post" enctype="multipart/form-data" name="form1" target="target_upload">
				<input type="hidden" name="path" value="${path }"/>
				<input type="hidden" name="dto.filetype" value="${dto.filetype }">
				<s:if test='dto.filetype=="img"'>
					<div>
							文件名:<input type="text" disabled="disabled" value="${dto.filename }"/><br>
							原图片:<img src="${dto.imgPath }" height="300" width="300"/><br>
							替换图片:<input type="file" name="file"/><!-- file不能方表单里面 会被提交两次 -->
					</div>
				</s:if>
				<s:elseif test='dto.filetype=="media"'>
					<div>
						 文件名:<input type="text" disabled="disabled" value="${dto.filename }"/><br>
						 替换文件:<input type="file" name="file"/>
					</div>
				</s:elseif>
				<s:elseif test='dto.filetype=="text"'>
					<div>
						文件名:<input type="text" disabled="disabled" value="${dto.filename }"/><br>
						代码:<textarea style="width: 100%;height: 372px" name="dto.code">${dto.code }</textarea>
					</div>
				</s:elseif>
				<s:else>
					<div>不支持的文件类型.</div>
				</s:else>
				<input  type="button" value="保存" onclick="formsub()"/>
			</form>
		</s:if>
		<s:else>
			${dto.msg }
		</s:else>
</div>
<div>
		<s:if test='resultMsg != null && resultMsg != "" '>
			<script type="text/javascript">
					alert('${resultMsg }');	
			</script>
		</s:if>
		<iframe id='target_upload' name='target_upload' src='' style="display: none"></iframe>
</div>