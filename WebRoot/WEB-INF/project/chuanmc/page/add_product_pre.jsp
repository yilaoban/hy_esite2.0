<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	//实例化编辑器

	 $(document).ready(function() {  
           $("#category").change();
			var um = UE.getEditor('myEditor');
			var um2 = UE.getEditor('myEditor1');
        });
        
      function checkSub(){
      	var title = $.trim($('#title').val());
     	if(title == "" || title == null){
     		$("#title_").html("请输入名称").css("color", "RED");
     		window.location.hash="maodian";
			return false;
     	}
		$("#form1").submit();
	} 
</script>
<a name="maodian"></a> 
<div  class="wrap_content">
<form action="add_product_sub.action" method="post" enctype="multipart/form-data" class="formview jNice" id="form1">
<span id="errrSpan" style="color:red"><strong style="color:red"><s:fielderror theme="" fieldName="error"/></strong> </span>
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="">新增产品</a></li>
	  </ul>
	  <a href="/${oname }/content/content_index.action?ccid=${ccid }" class="return" title="返回"></a>
	</div>
	

 	<dl>
	 	<dt>分类</dt>
		<dd>
			<s:iterator value="dto.contentcategory" var="c">
				<input type="hidden" name="ccid" value="${c.id }"/>
				<input type="text" class="text-medium" value="${c.name }" readonly="readonly"/>
			</s:iterator>
		</dd>
	</dl>
	<div id="contentTag">
		<script type="text/javascript">
			$("#contentTag").load('/${oname}/page/contentTag.action?ccid=${ccid}');	
		</script>
	</div>
	<dl>
	 	<dt><span id="title_" class="must">*</span>名称</dt>
		<dd>
			<input type="text" class="text-long" name="name" id="title"/>
		</dd>
	</dl>
	<dl>
	 	<dt>小图片</dt>
		<dd>
			<input type="file" name="simg"/>
		</dd>
	</dl>
	<dl>
	 	<dt>大图片</dt>
		<dd>
			<input type="file" name="bimg"/>
		</dd>
	</dl>
	<dl>
	 	<dt>链接地址</dt>
		<dd>
			<input type="text" class="text-long" name="linkurl" placeholder="地址请以http://开头"/>
		</dd>
	</dl>
	<dl>
	 	<dt>原价</dt>
		<dd>
			<input type="text" class="text-medium" name="price" onkeyup="if(this.value==this.value2)return;if(this.value.search(/^\d*(?:\.\d{0,2})?$/)==-1)this.value=(this.value2)?this.value2:'';else this.value2=this.value;">
		</dd>
	</dl>
	<dl>
	 	<dt>促销价</dt>
		<dd>
			<input type="text" class="text-medium" name="salesprice" onkeyup="if(this.value==this.value2)return;if(this.value.search(/^\d*(?:\.\d{0,2})?$/)==-1)this.value=(this.value2)?this.value2:'';else this.value2=this.value;">
		</dd>
	</dl>
	<dl>
	 	<dt>产品总数</dt>
		<dd>
			<input type="text" class="text-medium" name="total" onkeyup="this.value = this.value.replace(/\D/g,'');">
		</dd>
	</dl>
	<dl>
	 	<dt>已使用数</dt>
		<dd>
			<input type="text" class="text-medium" name="used" onkeyup="this.value = this.value.replace(/\D/g,'');">
		</dd>
	</dl>
	<dl>
	 	<dt>展示使用数</dt>
		<dd>
			<input type="text" class="text-medium" name="shownum" onkeyup="this.value = this.value.replace(/\D/g,'');">
		</dd>
	</dl>
	<dl>
	 	<dt>摘要</dt>
		<dd>
			<script type="text/plain" id="myEditor" name="tag" style="width:100%;height:200px;"></script>
		</dd>
	</dl>
	<dl>
	 	<dt>详情</dt>
		<dd>
			<script type="text/plain" id="myEditor1" name="detail" style="width:100%;height:200px;"></script>
		</dd>
	</dl>
	<dl style="display: none">
	 	<dt>提交审核</dt>
		<dd>
			<label class="forradio"><input type="radio" name="status" value="DSH">是</label><label class="forradio"><input type="radio" name="status" value="EDT">下次编辑</label><label class="forradio"><input type="radio" name="status" value="CMP" checked="checked">直接发布</label>
		</dd>
	</dl>
	<dl>
	 	<dt></dt>
		<dd>
			<input type="button" class="btn btn-primary" onclick="checkSub()" value="确定"/>
		</dd>
	</dl>
</form>
</div>