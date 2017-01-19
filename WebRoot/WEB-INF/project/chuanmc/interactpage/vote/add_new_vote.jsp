<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content left_module">
	<div class="toolbar pb10">
		<ul class="c_switch">
			<li class="selected"><a href="javascript:void(0);" >新增投票内容</a></li>
		</ul>
		<s:if test="redirectXc!=0">
			<a href="/${oname }/interact/vote_content.action?voteid=${voteid }&mid=10002&type=${type }&redirectXc=${redirectXc }" class="return" title="返回"></a>
		</s:if>
		<s:else>
			<a href="/${oname }/interact/vote_content.action?voteid=${voteid }&mid=10002&type=${type }" class="return" title="返回"></a>
		</s:else>
	</div>
<form action="vote_content_save.action" method="post" name="myform" enctype="multipart/form-data" class="formview jNice">
	<input type="hidden" name="voteid" value="${voteid}">
	<input type="hidden" name="type" value="${type }">
	<input type="hidden" name="redirectXc" value="${redirectXc }">
	<dl>
		<dt>标题</dt>
		<dd>
			<input type="text" name="vom.content" class="text-long"/>
		</dd>
	</dl>
	<dl>
		<dt>标签</dt>
		<dd class="showinput">
			<input type="text" class="text-small" name="tags" />
			<input type="text" class="text-small" name="tags" />
			<input type="text" class="text-small" name="tags" />
			<input type="text" class="text-small" name="tags" />
			<input type="text" class="text-small" name="tags" />
		</dd>
	</dl>
	<dl>
		<dt>描述</dt>
		<dd class="pt10">
			<input type="radio" name="vom.linked" checked="checked" value="N" id="radio_content">内容 <input type="radio" name="vom.linked" value="Y" id="radio_linkurl">链接地址  
		</dd>
	</dl>
	<dl class="desc-linkurl" style="display: none">
		<dt></dt>
		<dd class="pt10">
			<input type="text" class="text-long" name="vom.linkurl">
		</dd>
	</dl>
	<dl class="desc-content">
		<dt></dt>
		<dd class="pt10">
			<script type="text/plain" id="myEditor" name="vom.description" style="width:100%;height:200px;"></script>
		</dd>
	</dl>
	<dl>
		<dt>图片</dt>
		<dd>
			<input type="file" name="vom.img" id="img"><input type="hidden" name="vom.pic">
		</dd>
	</dl>
	<dl>
		<dt>视频</dt>
		<dd>
			<label class="forradio"><input type="radio" name="vediotype" onclick="$('.type').hide();$('.type1').hide()" value="N"/>无视频</label>
			<label class="forradio"><input type="radio" name="vediotype" onclick="$('.type').show();$('.type1').hide()" value="W"/>网络视频</label>
 			<label class="forradio"><input type="radio" name="vediotype" onclick="$('.type').hide();$('.type1').show()" value="U"/>上传</label>
 			<span id="title_" class="must">${result}</span>
		</dd>
	</dl>
	<dl class="type" style="display: none">
	 	<dt>链接地址</dt>
		<dd>
			<input type="text" name="vom.vediourl" id="vediourl" />
		</dd>
	</dl>
	<dl class="type1" style="display: none">
	 	<dt>上传视频</dt>
		<dd>
			<input type="file" name="uploadvedio" id="uploadvedio"/>
			<span class="must">上传视频请小于10M</span>
		</dd>
	</dl>
	<dl>
		<dt></dt>
		<dd>
			<input type="button" value="提交" onclick="checkFile()" class="btn btn-primary">
			<input type="reset" value="重置" class="btn btn-primary">
		</dd>
	</dl>
</form>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$("#radio_content").click(function(){
			$(".desc-content").show();
			$(".desc-linkurl").hide();
		});
		$("#radio_linkurl").click(function(){
			$(".desc-content").hide();
			$(".desc-linkurl").show();
		});
	});
	
//实例化编辑器
	var um = UE.getEditor('myEditor');
	function checkFile(){
		var vediotype=$('input:radio[name="vediotype"]:checked').val();
		var vediourl = $('#vediourl').val();
		var uploadvedio = $('#uploadvedio').val();
		if(vediotype == "W"){
			if(vediourl == "" || vediourl == null){
				layer.alert("请填写链接！");
				return false;
			}
		}else if(vediotype == "U"){
			if(uploadvedio == "" || uploadvedio == null){
				layer.alert("请上传视频！");
				return false;
			}
		}
		var content=$('input:text[name="vom.content"]').val();
		if(content==""){
			layer.alert("请填写内容！");
		}else{
			document.myform.submit();
		}
	}
</script>