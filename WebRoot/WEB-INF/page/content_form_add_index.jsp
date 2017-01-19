<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link rel="stylesheet" type="text/css" href="/diyUpload/css/webuploader.css">
<link rel="stylesheet" type="text/css" href="/diyUpload/css/diyUpload.css">
<script type="text/javascript" src="/diyUpload/js/webuploader.js"></script>
<script type="text/javascript" src="/diyUpload/js/diyUpload.js"></script>
<script type="text/javascript">
//实例化编辑器
var um = UE.getEditor('myEditor');

function createTopic(){
	var title = $('#title').val().trim();
	if(title == ""){
		alert('标题不能为空');
		return;
	}
	if(!um.hasContents()){
		alert('内容不能为空');
		return;
	}
	var content = um.getContent();
	$('#content').val(content);
	$('#myform').submit();
}

document.onreadystatechange = loading; 
function loading(){
	if(document.readyState == "complete")
	{ 
		
		$('.qstr').bind('input propertychange', function() {
			var id=$(this).attr("id").replace("qstr","");
			fbdk(id);
		});
		
		
		$.post("/${oname}/user/areaProvince.action",function(data){
			$.each(data.province,function(index,value){
				$(".lbs_provience").append("<option value='"+value.province+"' data='"+value.provinceId+"'>"+value.province+"</option>");
			});
		});
		
		$(".lbs_provience").bind("change",function(){
			var id=$(this).attr("id").replace("provience","");
			$("#city"+id).empty();
			if(value == -1){
				$("#city"+id).append('<option value="-1">请选择市</option>');
			}else{
				var value = $("#provience"+id).find("option:selected").attr("data");
				$.post("/${oname}/user/areaCity.action",{"provinceId":value},function(data){
					$.each(data.city,function(index,value){
						$("#city"+id).append("<option value='"+value.city+"'>"+value.city+"</option>");
					})
				});
			}
		});
	}
}
function fbdk(id){
	var provience=$("#provience"+id).val();
	var region;
	if(provience.endWith("市")||provience.endWith("特别行政区")){
		region=provience;
	}else{
		region=$("#city"+id).val();
	}
	var qstr=$("#qstr"+id).val();
	$.post("/${oname}/user/bdmap/ak.action",{"q":qstr,"region":region},function(data){
		var html="";
		$.each(data.results,function(index,value){
			var listr="<li><input type='radio' name='record."+id+"' value='"+JSON.stringify(value)+"'/><label>"+value.name+"</label></li>"
			if(value.address!=null){
				listr+="<li>&nbsp;&nbsp;&nbsp;&nbsp;"+value.address+"</li>";
			}
			html+=listr;
		});
		$("#place"+id).empty();
		$("#place"+id).html(html);
	})
}
</script>
<style>
	.imgpicker {margin:0;padding:0;border:0;width:70px;height:20px;border:1px solid #b9b9b9;border-right:20px solid #b9b9b9;line-height:20px;}
</style>
<div class="wrap_content left_module">
	 <s:if test="dto.contentform.showList.size() > 0">
	 <form action="content_form_add_sub.action" method="post" class="formview jNice" id="form1">
	 	<input name="record.formid" value="${dto.contentform.id }" type="hidden"/>
	 	<input name="ccid" value="${dto.current.id }" type="hidden"/>
	 	  <div class="toolbar pb10">
		  	<ul class="c_switch">
			  <li class="selected"><a href="">新增内容</a></li>
			  </ul>
			  <a href="/${oname }/content/content_form_index.action?ccid=${dto.current.id }" class="return" title="返回"></a>
			</div>
		<s:iterator value="dto.contentform.showList" var="p">
				<s:if test='#p.stype == "T"'>
				<dl>
					<dt>${p.name }</dt>
					<dd>
						<input type="text" class="text-medium" name="record.${p.mapping }"/>
					</dd>
				</dl>
				</s:if>
				<s:elseif test='#p.stype == "R"'>
				<dl>
					<dt>${p.name }</dt>
					<dd>
						<!-- 
						<s:iterator value="#p.defaultList" var="f" status="st">
							<label class="select"><input type="radio" value="${f}" name="record.${p.mapping }" <s:if test="#st.index==0"> checked="checked"</s:if>  />${f}</label>
						</s:iterator>
						 -->
						 <input type="text" class="text-medium" name="record.${p.mapping }" value="${p.defaultvalue}" id="${p.mapping}"  onclick="changeContent('${p.mapping}')">
					</dd>
				</dl>
				</s:elseif>
				<s:elseif test='#p.stype == "D"'>
				<dl>
					<dt>${p.name }</dt>
					<dd>
						<input type="text"  class="Wdate" name="record.${p.mapping }" value="${p.defaultvalue}" onfocus="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
					</dd>
				</dl>
				</s:elseif>
				<s:elseif test='#p.stype == "C"'>
				<dl>
					<dt>${p.name }</dt>
					<dd>
					<!-- 
					<s:iterator value="#p.defaultList" var="f" status="st">
						<label class="select"><input type="checkbox" name="record.${p.mapping }" value="${f}"/>${f}</label>
					</s:iterator>
					 -->
					 <input type="text" class="text-medium" name="record.${p.mapping}" value="${p.defaultvalue}" id="${p.mapping}"  onclick="changeContent('${p.mapping}')">
					</dd>
				</dl>
				</s:elseif>
				<s:elseif test='#p.stype == "S"'>
				<dl>
					<dt>${p.name }</dt>
					<dd>
					<!-- 
					<select name="record.${p.mapping }">
						<s:iterator value="#p.defaultList" var="f">
						  <option value ="${f}">${f}</option>
						</s:iterator>  
					</select>
					 -->
					 <input type="text" class="text-medium" name="record.${p.mapping }" value="${p.defaultvalue}" id="${p.mapping}"  onclick="changeContent('${p.mapping}')">
					</dd>
				</dl>
				</s:elseif>
				<s:elseif test='#p.stype == "A"'>
				<dl>
					<dt>${p.name }</dt>
					<dd>
						<script type="text/plain" id="myEditor${p.mapping}" name="record.${p.mapping }" style="width:100%;height:200px;"></script>
					</dd>
				</dl>
				<script type="text/javascript">
					var um = UE.getEditor('myEditor${p.mapping}');
				</script>
				</s:elseif>
				<s:elseif test='#p.stype == "P"'>
				<dl>
					<dt>${p.name }</dt>
					<dd>
						省：<select class="lbs_provience" id="provience${p.mapping }">
								<option value='-1'>请选择省</option>
							</select>
					</dd>
					<dd>
						市：<select class="lbs_city" id="city${p.mapping }">
								<option value='-1'>请选择市</option>
							</select>
					</dd>
					<dd>
						具体地址：<input type="text" class="text-long qstr" id="qstr${p.mapping }" />
						<div>
							<ul id="place${p.mapping }">
							</ul>
						</div>
					</dd>
				</dl>
				</s:elseif>
				<s:elseif test='#p.stype == "B"'>
				<dl>
					<dt>${p.name }</dt>
					<dd>
						<div id="as${p.mapping }"></div>
						<button class="imgpicker" id="picker${p.mapping }">选择图片</button>
						<input type="hidden" name="record.${p.mapping }" id="img${p.mapping }"/>
						<script type="text/javascript">
						
						/*
						* 服务器地址,成功返回,失败返回参数格式依照jquery.ajax习惯;
						* 其他参数同WebUploader
						*/
						
						$('#as${p.mapping }').diyUpload({
							url:'/${oname}/user/h5UploadPic.action',
							success:function( data ) {
								console.info( data );
								$("#img${p.mapping}").val(data.picUrl);
							},
							error:function( err ) {
								console.info( err );	
							},
							del:function(filename) {
								console.info( filename );	
							},
							auto: true,
							pick: '#picker${p.mapping }',
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
						</script>
					</dd>
				</dl>
				</s:elseif>
				<s:elseif test='#p.stype == "Q"'>
				<dl>
					<dt>${p.name }</dt>
					<dd>
						<input type="hidden" name="record.${p.mapping }" id="record${p.id }" value="${p.record }"/>
						<div id="as${p.id }"></div>
						<div id="picker${p.id }">上传zip压缩包</div>
						<script type="text/javascript">
						/*
						* 服务器地址,成功返回,失败返回参数格式依照jquery.ajax习惯;
						* 其他参数同WebUploader
						*/
						
						$('#as${p.id }').diyUpload({
							url:'/${oname}/user/quanjing.action',
							success:function( data ) {
								if(data.xmlPath!=""){
									$("#record${p.id }").val(data.xmlPath);
								}
								$("#picker${p.id }").hide();
							},
							error:function( err ) {
								console.info( err );	
							},
							del:function(filename) {
								$("#picker${p.id }").show();
							},
							auto: true,
							pick: '#picker${p.id }',
							//chunked:true,
							// 分片大小
							//chunkSize:512 * 1024,
							//最大上传的文件数量, 总文件大小,单个文件大小(单位字节);
							fileNumLimit:1,
							fileSizeLimit:500000 * 1024,
							fileSingleSizeLimit:50000 * 1024,
							accept:{
								extensions:"zip",
								mimeTypes:"application/zip|zip"
							}
						});
						</script>
					</dd>
				</dl>
				</s:elseif>
		</s:iterator>
			<dl>
				<dt>&nbsp;</dt>
				<dd>
					<input type="submit" value="确定"/>
					<input type="button" value="关闭" onclick="window.location='/${oname }/content/content_form_index.action?ccid=${dto.current.id}'"/>
				</dd>
			</dl>
	 </form>
	</s:if>
</div>