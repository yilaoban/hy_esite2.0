<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<link href="/js/icheck/skins/all.css" rel="stylesheet">
<script src="/js/icheck/icheck.min.js"></script>
<form id="mbform">
	<div class="step1">
		<dl>
		 	<dd>模板名称:<input type="text" class="text-medium" placeholder="模板名称" id="mbname" name="mb.title" required="required"></dd>
		</dl>
		<dl>
		 	<dd>模板类型:
		 		<input type="radio" class="text-medium" name="mb.type" value="W" checked="checked">网站
				<input type="radio" class="text-medium" name="mb.type" value="C">场景
			</dd>
		</dl>
		<dl>
			<dd>模板标签:</dd>
		 	<dd>
		 		<div class="dropdown"  id="mbtag1">
				  <input class="mbtag text-medium" value="" type="text" name="mb.tags"  placeholder="标签1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
				  <ul class="dropdown-menu">
				 	<li class="dropdown-header"></li>
				    <li><a href="#" class="mbopt">微官网</a></li>
				    <li><a href="#" class="mbopt">活动推广</a></li>
				    <li><a href="#" class="mbopt">企业招聘</a></li>
				    <li><a href="#" class="mbopt">企业介绍</a></li>
				    <li><a href="#" class="mbopt">会议邀请</a></li>
				    <li><a href="#" class="mbopt">教育培训</a></li>
				    <li><a href="#" class="mbopt">产品活动</a></li>
				    <li><a href="#" class="mbopt">婚礼邀请</a></li>
				    <li><a href="#" class="mbopt">节日祝福</a></li>
				    <li><a href="#" class="mbopt">其它场景</a></li>
				  </ul>
				</div>
				<div class="dropdown"  id="mbtag2" style="display: none;margin-top:5px;">
				  <input class="mbtag text-medium" value="" type="text" name="mb.tags"  placeholder="标签2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
				  <ul class="dropdown-menu">
				  	<li class="dropdown-header"></li>
				    <li><a href="#" class="mbopt">微官网</a></li>
				    <li><a href="#" class="mbopt">活动推广</a></li>
				    <li><a href="#" class="mbopt">企业招聘</a></li>
				    <li><a href="#" class="mbopt">企业介绍</a></li>
				    <li><a href="#" class="mbopt">会议邀请</a></li>
				    <li><a href="#" class="mbopt">教育培训</a></li>
				    <li><a href="#" class="mbopt">产品活动</a></li>
				    <li><a href="#" class="mbopt">婚礼邀请</a></li>
				    <li><a href="#" class="mbopt">节日祝福</a></li>
				    <li><a href="#" class="mbopt">其它场景</a></li>
				  </ul>
				</div>
				<div class="dropdown"  id="mbtag3" style="display: none;margin-top:5px">
				  <input class="mbtag text-medium" value="" type="text" name="mb.tags"  placeholder="标签3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
				  <ul class="dropdown-menu">
				  	<li class="dropdown-header"></li>
				    <li><a href="#" class="mbopt">微官网</a></li>
				    <li><a href="#" class="mbopt">活动推广</a></li>
				    <li><a href="#" class="mbopt">企业招聘</a></li>
				    <li><a href="#" class="mbopt">企业介绍</a></li>
				    <li><a href="#" class="mbopt">会议邀请</a></li>
				    <li><a href="#" class="mbopt">教育培训</a></li>
				    <li><a href="#" class="mbopt">产品活动</a></li>
				    <li><a href="#" class="mbopt">婚礼邀请</a></li>
				    <li><a href="#" class="mbopt">节日祝福</a></li>
				    <li><a href="#" class="mbopt">其它场景</a></li>
				  </ul>
				</div>
				<span id="mtag">+</span>
			</dd>
		</dl>
		<nav>
		  <ul class="pager">
		    <li><a href="#" class="next" num='1'>下一步</a></li>
		  </ul>
		</nav>
	</div>
	<div class="step2" style="display: none">
		<dl>
		 	<dd>模板简介:</dd>
			<dd>
				<textarea maxlength="200" placeholder="模板简介" name="mb.shortdesc"></textarea>
			</dd>
		</dl>
		<dl>
		 	<dd>模板图片:</dd>
			<dd>
				<div id="as2"></div>
				<span id="picker2">选择图片</span>
				<input type="hidden" id="mb_pic" name="mb.img">
			</dd>
		</dl>
		<nav>
		  <ul class="pager">
		  	<li><a href="#" class="before" num='2'>上一步</a></li>
		    <li><a href="#" class="next" num='2'>下一步</a></li>
		  </ul>
		</nav>
	</div>
	<div class="step3" style="display: none">
	<table style="width: 80%;text-align: center">
		<tr>
			<th>选择页面</th>
			<th width="20%">选择入口页</th>
		</tr>
		<s:iterator value="pages" var="p" status="st">
			<tr>
				<td><input type="checkbox" name="mb.pages" id="flat-checkbox-${st.index}" value="${p.id }"><label for="flat-checkbox-${st.index}">${p.name}</label>
	            </td>
				<td><input type="radio" name="mb.link" value=" ${pageDomain }/${oname }/user/show/${p.id }/pcn.html" <s:if test='#st.first'>checked="checked"</s:if> ></td>
			</tr>
		</s:iterator>
	</table>
		<nav>
		  <ul class="pager">
		  	 <li><a href="#" class="before" num="3">上一步</a></li>
		    <li><a href="#" class="tijiao">提交</a></li>
		  </ul>
		</nav>
	</div>
</form>
<script type="text/javascript">

$(document).ready(function(){
	$(".next").click(function(){
		var num = Number($(this).attr("num"));
		$(".step"+num).hide();
		$(".step"+(num+1)).show();
	});
	$(".before").click(function(){
		var num = Number($(this).attr("num"));
		$(".step"+num).hide();
		$(".step"+(num-1)).show();
	});
	$(".mbopt").click(function(){
		var val = $(this).text();
		$(this).parent().parent().prev().val(val);
	});
	
	 $('#mbform input').iCheck({
	    checkboxClass: 'icheckbox_minimal-red',
	    radioClass: 'iradio_minimal-red',
	    increaseArea: '20%' // optional
	  });
	 
	 $("#mtag").click(function(){
		 var num = $(".mbtag:visible").length+1;
		 if(num <= 3){
		 	$("#mbtag"+num).show();			 
		 }else{
			 layer.tips('最多支持3个标签', $(this));
		 }
	 });
});
		
		/*
		* 服务器地址,成功返回,失败返回参数格式依照jquery.ajax习惯;
		* 其他参数同WebUploader
		*/
		
		$('#as2').diyUpload({
			url:'/${oname}/user/h5UploadPic.action',
			success:function( data ) {
				$("#mb_pic").val("${imgDomain}"+data.picUrl);
				$("#picker2").hide();
				console.info( data );
			},
			error:function( err ) {
				console.info( err );	
			},
			del:function(filename) {
				$("#picker2").show();	
				console.info( filename );	
			},
			auto: true,
			pick: '#picker2',
			//chunked:true,
			// 分片大小
			//chunkSize:512 * 1024,
			//最大上传的文件数量, 总文件大小,单个文件大小(单位字节);
			fileNumLimit:1,
			fileSizeLimit:500000 * 1024,
			fileSingleSizeLimit:50000 * 1024,
			accept:{
						title:"Images",
						extensions:"gif,jpg,jpeg,bmp,png",
						mimeTypes:"image/*"
					}
		});
		
		$(".tijiao").click(function(){
			if($("#mbname").val()==""){
				layer.msg('请先填写模板名称！',{icon: 6, time: 1500},function(){
					$(".step3").hide();
					$(".step1").show();
				});
				return;
			}
			if($("input[name='mb.tags'][value!='']").length == 0){
				layer.msg('请先填写模板标签！',{icon: 6, time: 1500},function(){
					$(".step3").hide();
					$(".step1").show();
				});
				return;
			}
			if($("#mb_pic").val()==""){
				layer.msg('请先上传模板图片！',{icon: 6, time: 1500},function(){
					$(".step3").hide();
					$(".step2").show();
				});
				return;
			}
			var p = $("input[name='mb.pages']:checked").length;
			var r = $("input[name='mb.link']:checked").length;
			if(p == 0 || r == 0){
				layer.msg('请选择页面或者入口页',{icon: 6, time: 2000});
				return;
			}
			$.ajax({
	                cache: true,
	                type: "POST",
	                url:"addmbsub.action",
	                data:$('#mbform').serialize(),// 你的formid
	                async: false,
	                error: function(request) {
	                    alert("Connection error");
	                },
	                success: function(data) {
	                	if(data==1){
	                		layer.msg('创建成功!',{icon: 6, time: 2000},function(){
		                		closeFrame();
	                		});
	                	}else{
	        				layer.msg('创建失败，请重试!',{icon: 6, time: 2000});
	        			}
	                }
	            });	
		});
</script>