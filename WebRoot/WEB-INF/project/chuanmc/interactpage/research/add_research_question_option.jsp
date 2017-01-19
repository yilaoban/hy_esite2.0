<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<div class="wrap_content left_module">
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="">新增调研内容</a></li>
	  </ul>
	  <a href="research_question_option.action?searchid=${searchid }&mid=10006" class="return" title="返回"></a>
	</div>
   <form action="save_research_question.action" method="post" name="myform" enctype="multipart/form-data" class="formview jNice">
<input type="hidden" name="rqlmodel.searchid" value="${searchid}">
	<dl>
		<dt>题目类型</dt>
		<dd>
				<select id="sel" onchange="changetype(this)">
					 <option value ="SGL" id="s1" name="type">单选题</option>
					 <option value ="MUP" id="s2" name="type">多选题</option>
					 <option value ="FIL" id="s4" name="type" selected="selected">填空题</option>
					 <option value ="GAD" id="s5" name="type">打分题</option>
					 <option value ="ORD" id="s6" name="type">排序题</option>
			  </select>
			  <input type="hidden" name="rqlmodel.type" id="s3" value="FIL">
			  
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
