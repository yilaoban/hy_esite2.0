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
<form action="/${oname }/cd-news/editSub.action" id="form1" enctype="multipart/form-data" method="post" class="formview jNice">
<input type="hidden" name="news.id" value="${news.id }"/>
<input type="hidden" name="news.catid" value="${news.catid }"/>
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="">编辑新闻</a></li>
	  </ul>
	  <a href="/${oname }/cd-news/index.action" class="return" title="返回"></a>
	</div>
	<span id="errrSpan" style="color:red"><strong style="color:red"><s:fielderror theme="" fieldName="error"/></strong> </span>
	<div id="contentTag">
		<script type="text/javascript">
		$("#contentTag").load('/${oname}/cd-news/tags.action?pid=${news.id}&type=N');	
		</script>
	</div>
	<dl>
	 	<dt><span id="title_" class="must">*</span>标题</dt>
		<dd>
			<input type="text" name="news.title" class="text-long" value="${news.title }" id="title"/>
		</dd>
	</dl>
	<dl>
	 	<dt>作者</dt>
		<dd>
			<input type="text" class="text-long" name="news.author" id="author" value="${news.author }"/>
		</dd>
	</dl>
	<dl>
	 	<dt>来源</dt>
		<dd>
			<input type="text" class="text-long" name="news.source" id="source" value="${news.source }"/>
		</dd>
	</dl>
	<dl>
	 	<dt>发布时间</dt>
		<dd>
			<input id="publishtime" type="text" name="publishtime" class="Wdate" value="<s:date name="news.publishtime" format="yyyy-MM-dd HH:mm:ss"/>" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" " />
		</dd>
	</dl>
	<dl>
	 	<dt>摘要</dt>
		<dd>
			<textarea rows="" cols="" name="news.shortdesc" style="width:50%;height:140px;">${news.shortdesc}</textarea>
		</dd>
	</dl>
	<dl>
	 	<dt>有效期</dt>
		<dd>
			<input id="startTime" type="text" value="<s:date name="news.startTime" format="yyyy-MM-dd HH:mm:ss"/>" name="startTime" class="Wdate" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly"  />
			<span id="startTime_"></span>
			至<input id="endTime" type="text" value="<s:date name="news.endTime" format="yyyy-MM-dd HH:mm:ss"/>" name="endTime" class="Wdate"	onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly"  />
			<span id="endTime_"></span>
		</dd>
	</dl>
	<dl>
	 	<dt>列表小图</dt>
		<dd>
			<span id="hsimg" style="display: none"><input type="file" name="simg" id="fsimg" /></span>
			<s:if test='news.simgurl==null'><img id="simg" src="/images/nopic.png" height="80"></s:if>
			<s:else>
				<img id="simg" src="${imgDomain }${news.simgurl }" height="80">
			</s:else>
			<input type="hidden" name="news.simgurl" value="${news.simgurl }">
		</dd>
		<dd>
			<a id="simgtext" href='javascript:imgchange("simg")'>我要上传</a>
		</dd>
	</dl>
	<dl>
	 	<dt>列表大图</dt>
		<dd>

			<span id="hbimg" style="display: none"><input type="file" name="bimg" id="fbimg" /></span>
			<s:if test='news.bimgurl==null'>
				<img id="bimg" height="100" src="/images/nopic.png">
			</s:if>
			<s:else>
				<img id="bimg" src="${imgDomain }${news.bimgurl }" height="100">
			</s:else>
			<input type="hidden" name="news.bimgurl" value="${news.bimgurl }">
		</dd>
		<dd>
			<a id="bimgtext" href='javascript:imgchange("bimg")'>我要上传</a>
		</dd>
	</dl>
	<dl>
	 	<dt>内容</dt>
		<dd>
			<input type="radio" id="radio1" name="news.islink" value="Y" <s:if test='news.islink=="Y"'>checked="checked"</s:if>>链接地址<input type="radio" id="radio2" name="news.islink" value="N" onclick="changevalue()" <s:if test='news.islink=="N"'>checked="checked"</s:if>>内容
		</dd>
	</dl>
	<dl  id="p1" <s:if test='news.islink=="N"'>style="display:none;"</s:if>>
		<dt></dt>
		<dd >
			<input type="text" class="text-long" id="linkurl" name="news.linkurl" value="${news.linkurl}" />
		</dd>
	</dl>
	<dl id="p2" <s:if test='news.islink=="Y"'>style="display:none;"</s:if>>
		<dt></dt>
		<dd >
			<script type="text/plain" id="myEditor" name="news.content" style="width:716px;height:200px;">${news.content }</script>
		</dd>
	</dl>
	<dl>
	 	<dt>新闻初始值</dt>
		<dd>
			阅读量<input type="text" name="news.initialRead" value="${news.initialRead }" id="initialRead" onkeyup="this.value = this.value.replace(/\D/g,'');"/>
			点赞量<input type="text" name="news.initialZan" value="${news.initialZan }" id="initialZan" onkeyup="this.value = this.value.replace(/\D/g,'');"/>
		</dd>
	</dl>
	<dl>
	 	<dt>提交审核</dt>
		<dd>
			<input type="radio" name="news.status" value="DSH" <s:if test='news.status=="DSH"'>checked="checked"</s:if>>是&nbsp;
			<input type="radio" name="news.status" value="EDT" <s:if test='news.status=="EDT"'>checked="checked"</s:if>>下次编辑&nbsp;
			<input type="radio" name="news.status" value="CMP" <s:if test='news.status=="CMP"'>checked="checked"</s:if>>直接发布
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