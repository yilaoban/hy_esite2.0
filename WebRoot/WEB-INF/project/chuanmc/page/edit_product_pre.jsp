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
<form action="edit_product_sub.action" method="post" enctype="multipart/form-data" id="form1" class="formview">
<input type="hidden" value="${dto.cp.id }" name="contentId"/>
<input type="hidden" value="${dto.cp.catid }" name="ccid"/>
<span id="errrSpan" style="color:red"><strong style="color:red"><s:fielderror theme="" fieldName="error"/></strong> </span>
  <div class="toolbar pb10">
  	<ul class="c_switch">
	  <li class="selected"><a href="">产品编辑</a></li>
	  </ul>
	  <a href="javascript:window.history.back()" class="return" title="返回"></a>
	</div>
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
	<div id="contentTag">
		<script type="text/javascript">
			$("#contentTag").load('/${oname}/page/contentTag.action?ccid=${dto.cp.catid}&pid=${dto.cp.id}');	
		</script>
	</div>
	<dl>
	 	<dt><span id="title_" class="must">*</span>名称</dt>
		<dd>
			<input type="text" class="text-long" name="name" value="${dto.cp.name }" id="title"/>
		</dd>
	</dl>
	<dl>
	 	<dt>小图片</dt>
		<dd>
			<!-- 小图片:<img src="${dto.cp.simgurl }"> -->
			<s:if test="dto.cp.simgurl!=null">
				<span id="hsimg" style="display: none"><input type="file" name="simg" id="fsimg" /></span>
				<img id="simg" src="${imgDomain }${dto.cp.simgurl }" width="80" height="80">
				<input type="hidden" name="simgurl" value="${dto.cp.simgurl }">
				<a id="simgtext" href='javascript:imgchange("simg")'>我要上传</a>
			</s:if>
			<s:else>
				<input type="file" name="simg"/>
			</s:else>		
		</dd>
	</dl>
	<dl>
	 	<dt>大图片</dt>
		<dd>
			<!-- 大图片:<img src="${dto.cp.bimgurl }"> -->
			<s:if test="dto.cp.bimgurl!=null">
				<span id="hbimg" style="display: none"><input type="file" name="bimg" id="fbimg" /></span>
				<img id="bimg" src="${imgDomain }${dto.cp.bimgurl }" width="160" height="160">
				<input type="hidden" name="bimgurl" value="${dto.cp.bimgurl }">
				<a id="bimgtext" href='javascript:imgchange("bimg")'>我要上传</a>
			</s:if>
			<s:else>
				<input type="file" name="bimg" id="fbimg" />
			</s:else>		
		</dd>
	</dl>
	<dl>
	 	<dt>链接地址</dt>
		<dd>
			<input type="text" class="text-long" name="linkurl" placeholder="地址请以http://开头" value="${dto.cp.linkurl }"/>
		</dd>
	</dl>
	<dl>
	 	<dt>原价</dt>
		<dd>
			<input type="text" class="text-long" name="price" value="${dto.cp.price }" onkeyup="if(this.value==this.value2)return;if(this.value.search(/^\d*(?:\.\d{0,2})?$/)==-1)this.value=(this.value2)?this.value2:'';else this.value2=this.value;">
		</dd>
	</dl>
	<dl>
	 	<dt>促销价</dt>
		<dd>
			<input type="text" class="text-long" name="salesprice" value="${dto.cp.salesprice }" onkeyup="if(this.value==this.value2)return;if(this.value.search(/^\d*(?:\.\d{0,2})?$/)==-1)this.value=(this.value2)?this.value2:'';else this.value2=this.value;">
		</dd>
	</dl>
	<dl>
	 	<dt>产品总数</dt>
		<dd>
			<input type="text" class="text-long" name="total" value="${dto.cp.total }" onkeyup="if(this.value==this.value2)return;if(this.value.search(/^\d*(?:\.\d{0,2})?$/)==-1)this.value=(this.value2)?this.value2:'';else this.value2=this.value;">
		</dd>
	</dl>
	<dl>
	 	<dt>已使用数</dt>
		<dd>
			<input type="text" class="text-long" name="used" value="${dto.cp.used }" onkeyup="if(this.value==this.value2)return;if(this.value.search(/^\d*(?:\.\d{0,2})?$/)==-1)this.value=(this.value2)?this.value2:'';else this.value2=this.value;">
		</dd>
	</dl>
	<dl>
	 	<dt>展示使用数</dt>
		<dd>
			<input type="text" class="text-medium" name="shownum" value="${dto.cp.shownum }" onkeyup="this.value = this.value.replace(/\D/g,'');">
		</dd>
	</dl>
	<dl>
	 	<dt>摘要</dt>
		<dd>
			<script type="text/plain" id="myEditor" name="tag" style="width:100%;height:200px;">${dto.cp.tag }</script>
		</dd>
	</dl>
	<dl>
	 	<dt>详情</dt>
		<dd>
			<script type="text/plain" id="myEditor1" name="detail" style="width:100%;height:200px;">${dto.cp.detail }</script>
		</dd>
	</dl>
	<dl>
	 	<dt>提交审核</dt>
		<dd>
			<input type="radio" name="status" value="DSH" <s:if test='dto.cp.status=="DSH"'>checked="checked"</s:if>>是&nbsp;
			<input type="radio" name="status" value="EDT" <s:if test='dto.cp.status=="EDT"'>checked="checked"</s:if>>下次编辑&nbsp;
			<input type="radio" name="status" value="CMP" <s:if test='dto.cp.status=="CMP"'>checked="checked"</s:if>>直接发布
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