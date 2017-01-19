<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="wrap_content left_module">
	<div class="toolbar pb10">
		<ul class="c_switch">
			<li class="selected"><a href="">编辑投票内容</a></li>
		</ul>
		<a href="/${oname }/interact/vote_content.action?voteid=${vo.voteid}&mid=10002&type=${type }" class="return" title="返回"></a>
	</div>
<form action="update_vote_option.action" method="post" name="myform" enctype="multipart/form-data" class="formview jNice">
	<dl>
		<dt>标题</dt>
		<dd class="pt10">
			<input type="text" name="vom.content" value="${vo.content }"/>
		</dd>
	</dl>
	<dl>
		<dt>标签</dt>
		<dd class="showinput">
			<input type="text" class="text-small" name="tags" value="${vo.tagsJson[0]}"/>
			<input type="text" class="text-small" name="tags" value="${vo.tagsJson[1]}"/>
			<input type="text" class="text-small" name="tags" value="${vo.tagsJson[2]}"/>
			<input type="text" class="text-small" name="tags" value="${vo.tagsJson[3]}"/>
			<input type="text" class="text-small" name="tags" value="${vo.tagsJson[4]}"/>
		</dd>
	</dl>
	<dl>
		<dt>描述${vo.linked }</dt>
		<dd class="pt10">
			<input type="radio" name="vom.linked" <s:if test='vo.linked=="N"'>checked="checked"</s:if>  value="N" id="radio_content">内容 <input type="radio" name="vom.linked" value="Y" <s:if test='vo.linked=="Y"'>checked="checked"</s:if>  id="radio_linkurl">链接地址  
		</dd>
	</dl>
	<dl class="desc-linkurl" <s:if test='vo.linked =="N"'>style="display: none"</s:if> >
		<dt></dt>
		<dd class="pt10">
			<input type="text" class="text-long" name="vom.linkurl" value="${vo.linkurl}">
		</dd>
	</dl>
	<dl class="desc-content" <s:if test='vo.linked =="Y"'> style="display: none"</s:if> >
		<dt></dt>
		<dd class="pt10">
			<script type="text/plain" id="myEditor" name="vom.description" style="width:100%;height:200px;">${vo.description }</script>
		</dd>
	</dl>
	<dl>
		<dt>图片</dt>
		<dd>
			<s:if test='vo.img==null'><img src="/images/error.gif" width="100" height="80"><input type="file" name="vom.img" id="img"><input type="hidden" name="vom.pic" value="${vo.img }"></s:if><s:else><img src="${imgDomain }${vo.img }" width="100" height="80"><input type="file" name="vom.img" id="img"><input type="hidden" name="vom.pic" value="${vo.img }"></s:else>
		</dd>
	</dl>
	<s:if test="vo.vediourl != ''"> 
	<dl>
		<dt>视频</dt>
		<dd>
			<embed src="${vo.vediourl }" allowFullScreen="true" quality="high" width="300" height="180" align="middle" allowScriptAccess="always" type="application/x-shockwave-flash" wmode="transparent"></embed>
			<input type="button" value="重新上传" onclick="$('.type2').show();"/>
		</dd>
	</dl>
	</s:if>
	<div class="type2" <s:if test="vo.vediourl != ''">style="display: none"</s:if>>
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
				<input type="text" name="vom.vediourl" id="vediourl"/>
			</dd>
		</dl>
		<dl class="type1" style="display: none">
		 	<dt>上传视频</dt>
			<dd>
				<input type="file" name="uploadvedio" id="uploadvedio"/><input type="hidden" name="vediourl" value="${vo.vediourl }"/>
				<span class="must">上传视频请小于10M</span>
			</dd>
		</dl>
	</div>
	<dl>
		<dt></dt>
		<dd>
			<input type="hidden" name="type" value="${type }"/>
			<input type="hidden" name="id" value="${vo.id }"/>
			<input type="hidden" name="voteid" value="${vo.voteid }">
			<input type="button" onclick="checkFile()" value="提交">
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


var um = UE.getEditor('myEditor');
	function checkFile(){
		var type = '${type}';
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
		if($("#content").val()=="" && type == "C"){
			layer.alert("请填写内容！");
		}else{
			document.myform.submit();
		}
	}
</script>
