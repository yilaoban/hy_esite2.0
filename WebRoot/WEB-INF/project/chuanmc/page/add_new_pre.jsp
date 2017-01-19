<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
//实例化编辑器
	 $(document).ready(function() {  
           $("#category").change();
			var um = UE.getEditor('myEditor');
        }); 
         
	function checkSub(){
		var title = $.trim($('#title').val());
     	if(title == ""){
     		$("#title_").html("请输入标题").css("color", "RED");
     		window.location.hash="maodian";
			return false;
     	}
		$("#form1").submit();
	}
	$(function(){
		$("#radio1").click(function(){
			$("#p1").show();
			$("#p2").hide();
		})
		$("#radio2").click(function(){
			$("#p2").show();
			$("#p1").hide();
		})
	})
	function changevalue(){
		if(document.getElementById("radio1").checked){
			$("#islink").val("Y");
		}
		if(document.getElementById("radio2").checked){
			$("#islink").val("N");
		}
	}
	
</script>
<a name="maodian"></a> 
<div  class="wrap_content">
<form action="add_new_sub.action" method="post" id="form1" enctype="multipart/form-data" class="formview jNice">
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="">新增新闻</a></li>
	  </ul>
	  <a href="/${oname }/content/content_index.action?ccid=${ccid }" class="return" title="返回"></a>
	</div>
	
<span id="errrSpan" style="color:red"><strong style="color:red"><s:fielderror theme="" fieldName="error"/></strong> </span>
	
	<dl>
	 	<dt>分类</dt>
		<dd>
			<s:iterator value="dto.contentcategory" var="c">
				<input type="hidden" name="ccid" value="${c.id }"/>
				<input type="text" class="text-long" value="${c.name }" readonly="readonly"/>
			</s:iterator>
		</dd>
	</dl>
	<dl>
	 	<dt><span id="title_" class="must">*</span>标题</dt>
		<dd>
			<input type="text" class="text-long" name="title" id="title"/>
		</dd>
	</dl>
	<div id="contentTag">
		<script type="text/javascript">
			$("#contentTag").load('/${oname}/page/contentTag.action?ccid=${ccid}');	
		</script>
	</div>
	<dl>
	 	<dt>作者</dt>
		<dd>
			<input type="text" class="text-long" name="author" id="author"/>
		</dd>
	</dl>
	<dl>
	 	<dt>来源</dt>
		<dd>
			<input type="text" class="text-long" name="source" id="source"/>
		</dd>
	</dl>
	<dl>
	 	<dt>发布时间</dt>
		<dd>
			<input id="publishtime" type="text" name="publishtime" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" " />
		</dd>
	</dl>
	<dl>
	 	<dt>摘要</dt>
		<dd>
			<textarea rows="" cols="" name="shortdesc" style="width:50%;height:140px;"></textarea>
		</dd>
	</dl>
	<dl>
	 	<dt>有效期</dt>
		<dd>
			<input id="startTime" type="text" name="startTime" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" " />
			<span id="startTime_"></span>
			至<input id="endTime" type="text" name="endTime" class="Wdate"	onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" " />
			<span id="endTime_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>列表小图</dt>
		<dd>
			<input type="file" name="simg"/>
		</dd>
	</dl>
	<dl>
	 	<dt>列表大图</dt>
		<dd>
			<input type="file" name="bimg"/>
		</dd>
	</dl>
	<dl>
	 	<dt>内容</dt>
	 	<dd>
			<input type="hidden" name="islink"  id="islink" value="N">
			<label class="forradio"><input type="radio" name="radio" id="radio2" checked onclick="changevalue()">内容</label>
			<label class="forradio"><input type="radio" name="radio" id="radio1" onclick="changevalue()">链接地址</label> 
		</dd> 
		<dd id="p1" style="display:none">
			<input type="text" class="text-long" name="linkurl" id="linkurl"/>
		</dd>
		<dd  id="p2">
			<script type="text/plain" id="myEditor" name="content" style="width:100%;height:200px;">${dto.cn.content }</script>
		</dd>
	</dl>
	<dl>
	 	<dt>新闻初始值</dt>
		<dd>
			阅读量<input type="text" name="initialRead" id="initialRead" onkeyup="this.value = this.value.replace(/\D/g,'');"/>
			点赞量<input type="text" name="initialZan" id="initialZan" onkeyup="this.value = this.value.replace(/\D/g,'');"/>
		</dd>
	</dl>
	<dl>
	 	<dt>提交审核</dt>
		<dd>
			<label class="forradio"><input type="radio" name="status" value="DSH">是</label>
			<label class="forradio"><input type="radio" name="status" value="EDT">下次编辑</label>
			<label class="forradio"><input type="radio" name="status" value="CMP" checked="checked">直接发布</label>
		</dd>
	</dl>
	<dl>
	 	<dt></dt>
		<dd>
			<input type="button" onclick="checkSub()" class="btn btn-primary" value="确定"/>
		</dd>
	</dl>
</form>
</div>