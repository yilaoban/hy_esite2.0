<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">

	 
	  function checkTitle(){
		if($("#title").val()==""){
			$("#title_").html("请输入标题").css("color", "RED");
			return false;
		}else{
			$("#title_").html("");
			return true;
		}
	}
	 function checkCommentForm(){
	 	if($("#title").val()==""){
			$("#title_").html("请输入标题").css("color", "RED");
			window.location.hash="maodian";
			return false;
		}
		$("#myform").submit();
	 }
</script> 
   <a name="maodian"></a>        
<div class="wrap_content left_module">
  <form action="updateCommentSub.action" method="post" id="myform" name="myform" enctype="multipart/form-data" class="formview">
  <div class="jNice">
  <input type="hidden" name="id" value="${id }">
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="">编辑微上墙</a></li>
	  </ul>
	  <a href="/${oname }/interact/commentAction.action" class="return" title="返回"></a>
	</div>
 	<dl>
	 	<dt>标题</dt>
		<dd>
			<input type="text" class="text-medium" name="xc.topic" id="title" onblur="checkTitle()"  value="${dto.xc.topic }">
			<span id="title_" class="must">*</span>
		</dd>
	</dl>
	<dl>
	 	<dt>是否需要审核</dt>
		<dd>
			<s:if test='dto.xc.needcheck=="Y"'>
				<label class="forradio"><input type="radio"  name="xc.needcheck"  value="Y" checked="checked">是</label>
				<label class="forradio"><input type="radio"  name="xc.needcheck"  value="N" >否</label>
			</s:if>
			<s:else>
				<label class="forradio"><input type="radio"  name="xc.needcheck"  value="Y" >是</label>
				<label class="forradio"><input type="radio"  name="xc.needcheck"  value="N" checked="checked">否</label>
			</s:else>
		</dd>
	</dl>
	<dl>
	 	<dt></dt>
		<dd class="mt10">
			<input type="button" class="btn btn-primary" value="保存" onclick="checkCommentForm()">
			<input type="reset" class="btn" value="取消">
		</dd>
	</dl>
  </form>
</div>
