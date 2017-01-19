<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
function addExamOption(){
	var type=$("#sel").val();
	var html='';
	if(type=="SGL"||type=="MUP"){
		html+='<div ><dl id="Btext"><dt>选项</dt><dd style="position:relative;"><input type="text" class="text-medium" name="rqomodel.content" size="30" onblur="checkQoption()"><span id="options_"></span><a href="javascript:void(0);" onclick="removeResearch(this)" style="position:absolute;right:0;top:0;"><img src="/images/close.png" /></a></dd><dl>';
		html+='<div ><dl id="Btext"><dt>分数</dt><dd style="position:relative;"><input type="text" class="text-medium" name="rqomodel.score" size="20" onkeyup="this.value = this.value.replace(/\\D/g,\'\');"></dd><dl>';
		html+='<dl id="Bfile"><dt style="font-weight:normal ">选项图片</dt><dd><input type="file" name="rqomodel.img"><input type="hidden" name="rqomodel.pic"></dd></dl></div>'	
	}else if(type=="ORD"){
		html+='<div ><dl id="Btext"><dt>选项</dt><dd style="position:relative;"><input type="text" class="text-medium" name="rqomodel.content" size="30" onblur="checkQoption()"><span id="options_"></span><a href="javascript:void(0);" onclick="removeResearch(this)" style="position:absolute;right:0;top:0;"><img src="/images/close.png" /></a></dd><dl>';
		html+='<dl id="Bfile"><dt style="font-weight:normal ">选项图片</dt><dd><input type="file" name="rqomodel.img"><input type="hidden" name="rqomodel.pic"></dd></dl></div>'
	}else{
		var optionText=$("#sel option:selected").text();
		layer.msg(optionText+"无需设置选项!", {icon:4, time: 1500});
	}
	
	$("#content_block").append(html);
}
</script>
<div class="wrap_content left_module">
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="">新增测评内容</a></li>
	  </ul>
	  <a href="exam_question_option.action?searchid=${searchid }&mid=10015" class="return" title="返回"></a>
	</div>
   <form action="save_exam_question.action" method="post" name="myform" enctype="multipart/form-data" class="formview jNice">
<input type="hidden" name="rqlmodel.searchid" value="${searchid}">
	<dl>
		<dt>题目类型</dt>
		<dd>
			<select id="sel" name="rqlmodel.type">
				 <option value ="SGL" id="s1" name="type"  selected="selected">单选题</option>
				 <option value ="MUP" id="s2" name="type">多选题</option>
				 <option value ="FIL" id="s4" name="type">填空题</option>
				 <option value ="GAD" id="s5" name="type">打分题</option>
				 <option value ="ORD" id="s6" name="type">排序题</option>
			 </select>
		</dd>
	</dl>
	<dl>
		<dt>是否必填</dt>
		<dd>
			<input type="radio" name="rqlmodel.isreq" value="Y" checked="checked">是
			<input type="radio" name="rqlmodel.isreq" value="N">否
		</dd>
	</dl>
	<dl>
		<dt>题目名称</dt>
		<dd>
			<input type="text" name="rqlmodel.title" size="30" class="text-medium" id="title" onblur="checkQtitle()">
			<span class="must" id="title_">*</span>
		</dd>
	</dl>
	<dl>
		<dt style="font-weight:normal ">标题图片</dt>
		<dd>
			<input type="file" name="rqlmodel.img" id="img"><input type="hidden" name="rqlmodel.pic">
		</dd>
	</dl>
	<dl>
		<dt style="font-weight:normal ">设置选项</dt>
		<dd>
			<a title="增加一个选项" onclick="addExamOption()" href="javascript:void(0);">
			<span class="labels">增加选项</span>
			</a>
		</dd>
	</dl>
	<div id="content_block"></div>
	<dl>
		<dt></dt>
		<dd>
			<input type="button" value="提交" onclick="RcheckForm()" id="button">
			<input type="reset" value="重置">
		</dd>
	</dl>
</form>
</div>
