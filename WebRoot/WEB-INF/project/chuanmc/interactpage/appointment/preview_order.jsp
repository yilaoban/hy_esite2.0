<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
		<script type="text/javascript">
			function check(object){
				var req = object.getAttribute("msg");
				if(req == 'Y'){
					if(object.value==""){
						alert("必填项为空,请输入");
						
					}
				}
			}
			
			function sub(){
				if(formcheck()){
					layer.alert("申请成功!",1);
				}
			}
			
			function formcheck(){
				var inputs = document.getElementsByTagName('input');  
	            for(var i=0,len=inputs.length;i<len;i++){  
	                 if(inputs[i].getAttribute("msg")=="Y"){
	                 	var name = inputs[i].getAttribute("name");
	                 	var obj = document.getElementsByName(name);
	                 	var count = 0;
	                 	  if(obj != null && obj.length >1){
							   for(var j=0; j<obj.length; j++){
							     if(!obj[j].checked) {
							     	count = count + 1;
							     	if(count == obj.length){
							     		alert("请选择一项");
							     		return false;
							     	}
							     }
							   }
						  }else if(obj.length == 1){
	      					if(inputs[i].value=="" || inputs[i].value==null){
								alert(inputs[i].title+"为必填项为空,请输入");
								return false;
							}
						  }
					}
				}
				return true;
			}
</script>
	<div style="margin:0 auto;width:320px;">
	 <s:if test="adto.am.list.size() > 0">
	 <form action="/user/comment_record.action" onsubmit="return formcheck();" method="post" class="preview">
	 	<input type="hidden" name="aptRecord.aptid" value="${adto.am.id}" />
	 	<input type="hidden" name="uid" value="${hyuid}" />
	 	<input type="hidden" name="source" value="" />
		<s:iterator value="adto.am.list" var="p">
			<dl>
			<s:if test='#p.isshow == "Y"'>
				<s:if test='#p.stype == "T"'>
					<dt>${p.name }<s:if test='#p.req == "Y"'><span class="mf">*</span></s:if></dt>
					<dd>
						<input type="text" name="aptRecord.${p.mapping}" value="${p.defaultvalue}" id="${p.mapping}" msg="${p.req}" class="text-long" onblur="check(this)"/>
					</dd>
				</s:if>
				<s:if test='#p.stype == "E"'>
					<dt>${p.name }<s:if test='#p.req == "Y"'><span class="mf">*</span></s:if></dt>
					<dd>
						<input type="email" name="aptRecord.${p.mapping}" value="${p.defaultvalue}" id="${p.mapping}" msg="${p.req}" class="text-long" onblur="check(this)"/>
					</dd>
				</s:if>
				<s:elseif test='#p.stype == "R"'>
					<dt>${p.name }<s:if test='#p.req == "Y"'><span class="mf">*</span></s:if></dt>
					<dd>
						<s:iterator value="#p.defaultvalueArr" var="f" status="st">
							<label class="select"><input type="radio" name="aptRecord.${p.mapping}" value="${f}" msg="${p.req}" <s:if test="#st.index==0"> checked="checked"</s:if>  />${f}</label>
						</s:iterator>
					</dd>
				</s:elseif>
				<s:elseif test='#p.stype == "D"'>
					<dt>${p.name }<s:if test='#p.req == "Y"'><span class="mf">*</span></s:if></dt>
					<dd>
						<input type="text"  name="aptRecord.${p.mapping}" class="Wdate" value="${p.defaultvalue}" onfocus="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})" readonly="readonly" msg="${p.req}"/>
					</dd>
				</s:elseif>
				<s:elseif test='#p.stype == "C"'>
					<dt>${p.name }<s:if test='#p.req == "Y"'><span class="mf">*</span></s:if></dt>
					<dd>
					<s:iterator value="#p.defaultvalueArr" var="f" status="st">
						<label class="select"><input type="checkbox" name="aptRecord.${p.mapping}" value="${f}" msg="${p.req}"/>${f}</label>
					</s:iterator>
					</dd>
				</s:elseif>
				<s:elseif test='#p.stype == "S"'>
					<dt>${p.name }<s:if test='#p.req == "Y"'><span class="mf">*</span></s:if></dt>
					<dd>
					<select name="aptRecord.${p.mapping}">
						<s:iterator value="#p.defaultvalueArr" var="f">
						  <option value ="${f}">${f}</option>
						</s:iterator>  
					</select>
					</dd>
				</s:elseif>
				<s:elseif test='#p.stype == "A"'>
					<dt>${p.name }<s:if test='#p.req == "Y"'><span class="mf">*</span></s:if></dt>
					<dd>
						<textarea name="aptRecord.${p.mapping}" cols="10" rows="5"></textarea>
					</dd>
				</s:elseif>
				<s:elseif test='#p.stype == "I"'>
					<dt>${p.name }<s:if test='#p.req == "Y"'><span class="mf">*</span></s:if></dt>
					<dd>
							<div class="controls" class_data="hy2014051841408">
								<input type="text" name="aptRecord.${p.mapping}" id="aptRecord${p.mapping}"/>
								<div id="as${p.mapping}"></div>
								<div id="picker${p.mapping}">选择图片</div>
								<script type="text/javascript">
							
								/*
								* 服务器地址,成功返回,失败返回参数格式依照jquery.ajax习惯;
								* 其他参数同WebUploader
								*/
								
								$('#as${p.mapping}').diyUpload({
									url:'/${oname}/user/h5UploadPic.action',
									success:function( data ) {
										$("#aptRecord${p.mapping}").val(data.picUrl);
										console.info( data );
									},
									error:function( err ) {
										console.info( err );	
									},
									del:function(filename) {
										console.info( filename );	
									},
									auto: true,
									pick: '#picker${p.mapping}',
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
							</div>
					</dd>
				</s:elseif>
			</s:if>
			</dl>
		</s:iterator>
			<dl>
				<dt>&nbsp;</dt>
				<dd>
					<input type="button" onclick="sub()" value="确定"/>
					<input type="button" onclick="window.close()" value="关闭"/>
				</dd>
			</dl>
	 </form>
	</s:if>
	</div>