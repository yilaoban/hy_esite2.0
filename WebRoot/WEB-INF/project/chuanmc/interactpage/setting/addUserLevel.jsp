<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<link rel="stylesheet" type="text/css" href="/diyUpload/css/webuploader.css">
<link rel="stylesheet" type="text/css" href="/diyUpload/css/diyUpload.css">
<script type="text/javascript" src="/diyUpload/js/webuploader.js"></script>
<script type="text/javascript" src="/diyUpload/js/diyUpload.js"></script>
<div class="wrap_content left_module">
	<form action="/${oname }/setting/saveUserLevel.action" id="form1" class="formview jNice" method="post">
	<input type="hidden" name="hylevel" value="${hylevel }"/>
	<div class="toolbar pb10">
  	<ul class="c_switch">
	   <li class="selected"><a href="">新增等级</a></li>
	   </ul>
	   <a href="javascript:window.history.back()" class="return" title="返回"></a>
	</div>
	<div>
		<dl>
		 	<dt>名称：</dt>
			<dd>
				<input type="text" name="level.name"/>
			</dd>
		</dl>
		
		<dl class="clearfix">
			<input type="hidden" name="level.img" id="img" value="${level.img }">
			<dt>图片</dt>
			<dd>
				<div id="demo">
					<div id="as" ></div>
					<div id="picker">选择图片</div>
				</div>
			</dd>
		</dl>
		<dl>
			<dt>描述：</dt>
			<dd>
				<textarea  maxlength="200" name="level.hydesc"></textarea>
			</dd>
		</dl>
		<s:if test="hylevel == 1">
			<dl>
				<dt>单次充值：</dt>
				<dd>
					<input type="text" name="dannum" onkeyup="if(this.value==this.value2)return;if(this.value.search(/^\d*(?:\.\d{0,2})?$/)==-1)this.value=(this.value2)?this.value2:'';else this.value2=this.value;"/>元
				</dd>
			</dl>
		</s:if>
		<s:if test="hylevel == 2">
			<dl>
				<dt>累积充值：</dt>
				<dd>
					<input type="text" name="totalnum" onkeyup="if(this.value==this.value2)return;if(this.value.search(/^\d*(?:\.\d{0,2})?$/)==-1)this.value=(this.value2)?this.value2:'';else this.value2=this.value;"/>元
				</dd>
			</dl>
		</s:if>
		<s:if test="hylevel == 3">
			<dl>
				<dt>消费额度：</dt>
				<dd>
					<input type="text" name="usednum" onkeyup="if(this.value==this.value2)return;if(this.value.search(/^\d*(?:\.\d{0,2})?$/)==-1)this.value=(this.value2)?this.value2:'';else this.value2=this.value;"/>元
				</dd>
			</dl>
		</s:if>
	</div>
	<dl>
		<dt></dt>
		<dd>
			<input type="submit" class="btn btn-primary" value="保存"/>
		</dd>
	</dl>
	</form>
</div>
<script type="text/javascript">
	//实例化编辑器
	$('#as').diyUpload({
		url:'/${oname}/user/h5UploadPic.action',
		success:function( data ) {
			console.info( data );
			var url = '${imgDomain}' + data.picUrl;
			$('#img').val(url);
			$('#picker').hide();
		},
		error:function( err ) {
			console.info( err );	
		},
		del:function(filename) {
			$('#img').val("");
			$('#picker').show();
		},
		auto: true,
		pick: '#picker',
		fileNumLimit:1,
		fileSizeLimit:500000 * 1024,
		fileSingleSizeLimit:50000 * 1024,
		accept:{
					title:"Images",
					extensions:"gif,jpg,jpeg,bmp,png",
					mimeTypes:"image/*"
				}
	});
</script>