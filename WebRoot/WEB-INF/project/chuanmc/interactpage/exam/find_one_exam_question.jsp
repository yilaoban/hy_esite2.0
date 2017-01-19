<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
function addExamOption(){
	var type='${rqmodel.type}';
	var html='';
	if(type=="SGL"||type=="MUP"){
		html+='<div ><dl id="Btext"><dt>选项</dt><dd style="position:relative;"><input type="hidden" name="rqomodel.id"><input type="text" class="text-medium" name="rqomodel.content" size="30" onblur="checkQoption()"><span id="options_"></span><a href="javascript:void(0);" onclick="removeResearch(this)" style="position:absolute;right:0;top:0;"><img src="/images/close.png" /></a></dd><dl>';
		html+='<div ><dl id="Btext"><dt>分数</dt><dd style="position:relative;"><input type="text" class="text-medium" name="rqomodel.score" size="20" onkeyup="this.value = this.value.replace(/\\D/g,\'\');"></dd><dl>';
		html+='<dl id="Bfile"><dt style="font-weight:normal ">选项图片</dt><dd><input type="file" name="rqomodel.img"><input type="hidden" name="rqomodel.pic"></dd></dl></div>'	
	}else if(type=="ORD"){
		html+='<div ><dl id="Btext"><dt>选项</dt><dd style="position:relative;"><input type="hidden" name="rqomodel.id"><input type="text" class="text-medium" name="rqomodel.content" size="30" onblur="checkQoption()"><span id="options_"></span><a href="javascript:void(0);" onclick="removeResearch(this)" style="position:absolute;right:0;top:0;"><img src="/images/close.png" /></a></dd><dl>';
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
	  <li class="selected"><a href="">编辑测评内容</a></li>
	  </ul>
	  <a href="exam_question_option.action?searchid=${rqmodel.searchid }&mid=10015" class="return" title="返回"></a>
	</div>
<form action="update_exam_question.action" method="post" name="myform" enctype="multipart/form-data" class="formview jNice" >
	<dl>
		<dt>题目类型</dt>
		<dd>
			<s:if test='rqmodel.type=="SGL"' >单选题</s:if><s:if test='rqmodel.type=="MUP"'>多选题</s:if><s:if test='rqmodel.type=="FIL"'>填空题</s:if><s:if test='rqmodel.type=="GAD"'>打分题</s:if><s:if test='rqmodel.type=="ORD"'>排序题</s:if>
		</dd>
	</dl>
	<dl>
		<dt>题目名称</dt>
		<dd>
			<input type="text" value="${rqmodel.title }" class="text-medium" name="rqlmodel.title" id="title" onblur="checkQtitle()"><span id="title_"></span>
		</dd>
	</dl>
	<dl>
		<dt>是否必填</dt>
		<dd>
			<input type="radio" name="rqlmodel.isreq" value="Y" <s:if test='rqmodel.isreq=="Y"'>checked="checked"</s:if>>是
			<input type="radio" name="rqlmodel.isreq" value="N" <s:if test='rqmodel.isreq=="N"'>checked="checked"</s:if>>否
		</dd>
	</dl>
	<dl>
		<dt style="font-weight:normal ">标题图片</dt>
		<dd>
			<s:if test='rqmodel.pic==""'>
			<img src="/images/error.gif" width="100" height="80">
			</s:if>
			<s:else>
			<img src="${imgDomain }${rqmodel.pic }" width="100" height="80">
			</s:else>
		</dd>
		<dd>
			<input type="file" name="rqlmodel.img" id="img">
			<input type="hidden" name="rqlmodel.pic" value="${ rqmodel.pic}">
		</dd>
	</dl>
	<div id="content_block">
	<s:iterator value="dto.list" var="a">
	<div style="border:1px dashed #ccc;margin-top:10px;">
		<input type="hidden" value="${a.id }" name="rqomodel.id">
		<dl>
			<dt>选项</dt>
			<dd style="position:relative;">
				<input type="text" value="${a.content }" class="text-medium" name="rqomodel.content" onblur="checkQanswer()">
				<span name="answer_"></span>
				<s:if test='#a.content!=null'>
				<a href="javascript:void(0);" onclick="del_options(${a.id })" style="position:absolute;right:0;top:0;"><img src="/images/close.png" /></a>
				</s:if>
			</dd>
		</dl>
		<s:if test='rqmodel.type=="SGL"||rqmodel.type=="MUP"' >
		<dl>
			<dt>分数</dt>
			<dd style="position:relative;">
				<input type="text" value="${a.score }" class="text-medium" name="rqomodel.score" onkeyup="this.value = this.value.replace(/\D/g,'');">
			</dd>
		</dl>
		</s:if>
		<dl>
			<dt style="font-weight:normal ">选项图片</dt>
			<dd>
				<s:if test='#a.photo==""'>
				<input type="file" name="rqomodel.img" id="img">
				<input type="hidden" name="rqomodel.pic" >
				</s:if>
				<s:else>
				<input type="file" name="rqomodel.img" id="img">
				<input type="hidden" name="rqomodel.pic" value="${a.photo }">
				</s:else>
			</dd>
			<dd>
				<s:if test='#a.photo==""'>
				<img src="/images/error.gif" width="100" height="80">
				</s:if>
				<s:else>
				<img src="${imgDomain }${a.photo }" width="100" height="80">
				</s:else>
			</dd>
		</dl>
	</div>
	</s:iterator>
	</div>
	<dl>
		<dt></dt>
		<dd>
			<input type="hidden" name="id" value="${rqmodel.id }"/>
			<input type="hidden" name="questionid" value="${rqmodel.id }">
			<input type="hidden" name="searchid" value="${rqmodel.searchid }">
			<input type="button" onclick="RcheckForm()" value="提交">
			<s:if test='rqmodel.type!="FIL" && rqmodel.type!="GAD"'>
			<input type="button" value="增加一项" onclick="addExamOption()">
			</s:if>
		</dd>
	</dl>

</form>
</div>
