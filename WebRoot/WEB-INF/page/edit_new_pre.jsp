<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	 $(document).ready(function() {  
           $("#category").change();
           var um = UE.getEditor('myEditor');
			//实例化编辑器
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
<form action="edit_new_sub.action" id="form1" enctype="multipart/form-data" method="post" class="formview jNice">
<input type="hidden" name="contentId" value="${dto.cn.id }"/>
<input type="hidden" name="ccid" value="${dto.cn.catid }"/>
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="">编辑新闻</a></li>
	  </ul>
	  <a href="javascript:window.history.back()" class="return" title="返回"></a>
	</div>
	<span id="errrSpan" style="color:red"><strong style="color:red"><s:fielderror theme="" fieldName="error"/></strong> </span>

	<dl>
	 	<dt>分类</dt>
		<dd>
			<s:iterator value="dto.ccList" var="c" status="s">
				<s:iterator value="c" var="cc">
					<s:if test='use=="Y"'><input type="text" disabled="disabled" value="${cc.name }" class="text-long"/></s:if>
				</s:iterator>
				<s:if test="!#s.last">-</s:if>
			</s:iterator>
		</dd>
	</dl>
	<dl>
	 	<dt><span id="title_" class="must">*</span>标题</dt>
		<dd>
			<input type="text" name="title" class="text-long" value="${dto.cn.title }" id="title"/>
		</dd>
	</dl>
	<div id="contentTag">
		<script type="text/javascript">
			$("#contentTag").load('/${oname}/page/contentTag.action?ccid=${dto.cn.catid}&pid=${dto.cn.id}');	
		</script>
	</div>
	<dl>
	 	<dt>作者</dt>
		<dd>
			<input type="text" class="text-long" name="author" id="author" value="${dto.cn.author }"/>
		</dd>
	</dl>
	<dl>
	 	<dt>来源</dt>
		<dd>
			<input type="text" class="text-long" name="source" id="source" value="${dto.cn.source }"/>
		</dd>
	</dl>
	<dl>
	 	<dt>发布时间</dt>
		<dd>
			<input id="publishtime" type="text" name="publishtime" class="Wdate" value="<s:date name="dto.cn.publishtime" format="yyyy-MM-dd HH:mm:ss"/>" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" " />
		</dd>
	</dl>
	<dl>
	 	<dt>摘要</dt>
		<dd>
			<textarea rows="" cols="" name="shortdesc" style="width:50%;height:140px;">${dto.cn.shortdesc}</textarea>
		</dd>
	</dl>
	<dl>
	 	<dt>有效期</dt>
		<dd>
			<input id="startTime" type="text" value="<s:date name="dto.cn.startTime" format="yyyy-MM-dd HH:mm:ss"/>" name="startTime" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly"  />
			<span id="startTime_"></span>
			至<input id="endTime" type="text" value="<s:date name="dto.cn.endTime" format="yyyy-MM-dd HH:mm:ss"/>" name="endTime" class="Wdate"	onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly"  />
			<span id="endTime_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>列表小图</dt>
		<dd>
			<!-- 小图片:<img src="${dto.cp.simgurl }"> -->
			<span id="hsimg" style="display: none"><input type="file" name="simg" id="fsimg" /></span>
			<img id="simg" src="${imgDomain }${dto.cn.simgurl }" width="80" height="80">
			<input type="hidden" name="simgurl" value="${dto.cn.simgurl }">
		</dd>
		<dd>
			<a id="simgtext" href='javascript:imgchange("simg")'>我要上传</a>
		</dd>
	</dl>
	<dl>
	 	<dt>列表大图</dt>
		<dd>
			<!-- 大图片:<img src="${dto.cp.bimgurl }"> -->
			<span id="hbimg" style="display: none"><input type="file" name="bimg" id="fbimg" /></span>
			<img id="bimg" src="${imgDomain }${dto.cn.bimgurl }" width="160" height="160">
			<input type="hidden" name="bimgurl" value="${dto.cn.bimgurl }">
		</dd>
		<dd>
			<a id="bimgtext" href='javascript:imgchange("bimg")'>我要上传</a>
		</dd>
	</dl>
	<dl>
	 	<dt>内容</dt>
		<dd>
			<input type="radio" id="radio1" name="islink" value="Y" <s:if test='dto.cn.islink=="Y"'>checked="checked"</s:if>>链接地址<input type="radio" id="radio2" name="islink" value="N" onclick="changevalue()" <s:if test='dto.cn.islink=="N"'>checked="checked"</s:if>>内容
		</dd>
	</dl>
	<dl  id="p1" <s:if test='dto.cn.islink=="N"'>style="display:none;"</s:if>>
		<dt></dt>
		<dd >
			<input type="text" class="text-long" id="linkurl" name="linkurl" value="${dto.cn.linkurl}" />
		</dd>
	</dl>
	<dl id="p2" <s:if test='dto.cn.islink=="Y"'>style="display:none;"</s:if>>
		<dt></dt>
		<dd >
			<script type="text/plain" id="myEditor" name="content" style="width:716px;height:200px;">${dto.cn.content }</script>
		</dd>
	</dl>
	<dl>
	 	<dt>新闻初始值</dt>
		<dd>
			阅读量<input type="text" name="initialRead" value="${dto.cn.initialRead }" id="initialRead" onkeyup="this.value = this.value.replace(/\D/g,'');"/>
			点赞量<input type="text" name="initialZan" value="${dto.cn.initialZan }" id="initialZan" onkeyup="this.value = this.value.replace(/\D/g,'');"/>
		</dd>
	</dl>
	<dl>
	 	<dt>提交审核</dt>
		<dd>
			<input type="radio" name="status" value="DSH" <s:if test='dto.cn.status=="DSH"'>checked="checked"</s:if>>是&nbsp;
			<input type="radio" name="status" value="EDT" <s:if test='dto.cn.status=="EDT"'>checked="checked"</s:if>>下次编辑&nbsp;
			<input type="radio" name="status" value="CMP" <s:if test='dto.cn.status=="CMP"'>checked="checked"</s:if>>直接发布
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