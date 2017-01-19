<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<input id='cardid' type='hidden' value='${pcid}' />
<!-- 微现场申请 -->
<link href="/css/xc/mo/shenqing/index.css" rel="stylesheet" type="text/css" />

<%@include file="/WEB-INF/card/background.jsp"%>

<s:if test='blocks[0].display=="Y"'>
<div class="Hhrbanner block ${blocks[0].ctname}" status="0" hyct="Y" style="${blocks[0].hyct}" hyblk="S" hydata="${blocks[0].rid}"><img style="width:100%;height:100%;" src="${blocks[0].img}" /></div>
</s:if>
<s:if test='blocks[1].display=="Y"'>
<div class="Hhrbox clearfix block ${blocks[1].ctname}" status="0" hyct="Y" style="${blocks[1].hyct}" hyblk="S" hydata="${blocks[1].rid}">
<s:if test='#session.hy_page_dto["118"].app.id > 0'>
	<form id="myform${pcid }" method="post" class="preview">
		<s:set name="obj" value='#session.hy_page_dto["118"].app' />
			 	<input type="hidden" name="aptRecord.aptid" value="${obj.id}" />
			 	<input type="hidden" name="relationid" value="${blocks[1].rid}">
			 	<input type="hidden" name="aptRecord.pageid" value="${pageid}">
			 	<ul>
				<s:iterator value="#obj.list" var="p" status="st">
				<li>
					<s:if test='#p.isshow == "Y"'>
						<s:if test='#p.stype == "T"'>
						     <s:if test='#st.count%2 == 0'>
						     	<span style='background:url("/images/xc/mo/shenqing/menu_03.png");background-position:center;background-repeat: no-repeat;text-align:center;background-size:100% 100%;'>${p.name }</span>
						     </s:if>
						     <s:else>
						     	<span style='background:url("/images/xc/mo/shenqing/menu_06.png");background-position:center;background-repeat: no-repeat;text-align:center;background-size:100% 100%;'>${p.name }</span>
						     </s:else>
						     <input type="text" title="${p.name}" class="Hhrinput" name="aptRecord.${p.mapping}" value="${p.defaultvalue}" id="${p.mapping}" msg="${p.req}" onblur="check(this)" />
						     <s:if test='#p.req == "Y"'><font color="red">*</font></s:if>
						</s:if>
						<s:if test='#p.stype == "E"'>
							 <s:if test='#st.count%2 == 0'>
						     	<span style='background:url("/images/xc/mo/shenqing/menu_03.png");background-position:center;background-repeat: no-repeat;text-align:center;background-size:100% 100%;'>${p.name }</span>
						     </s:if>
						     <s:else>
						     	<span style='background:url("/images/xc/mo/shenqing/menu_06.png");background-position:center;background-repeat: no-repeat;text-align:center;background-size:100% 100%;'>${p.name }</span>
						     </s:else>
						     <input type="email" title="${p.name}" class="Hhrinput" name="aptRecord.${p.mapping}" value="${p.defaultvalue}" id="${p.mapping}" msg="${p.req}" onblur="check(this)" />
						     <s:if test='#p.req == "Y"'><font color="red">*</font></s:if>
						</s:if>
						<s:if test='#p.stype == "N"'>
							<s:if test='#st.count%2 == 0'>
						     	<span style='background:url("/images/xc/mo/shenqing/menu_03.png");background-position:center;background-repeat: no-repeat;text-align:center;background-size:100% 100%;'>${p.name }</span>
						     </s:if>
						     <s:else>
						     	<span style='background:url("/images/xc/mo/shenqing/menu_06.png");background-position:center;background-repeat: no-repeat;text-align:center;background-size:100% 100%;'>${p.name }</span>
						     </s:else>
						     <input type="number" title="${p.name}" class="Hhrinput" min="0" name="aptRecord.${p.mapping}" value="${p.defaultvalue}" id="${p.mapping}" msg="${p.req}" onblur="check(this)" />
						     <s:if test='#p.req == "Y"'><font color="red">*</font></s:if>
						</s:if>
						<s:elseif test='#p.stype == "R"'>
							<s:if test='#st.count%2 == 0'>
						     	<span style='background:url("/images/xc/mo/shenqing/menu_03.png");background-position:center;background-repeat: no-repeat;text-align:center;background-size:100% 100%;'>${p.name }</span>
						     </s:if>
						     <s:else>
						     	<span style='background:url("/images/xc/mo/shenqing/menu_06.png");background-position:center;background-repeat: no-repeat;text-align:center;background-size:100% 100%;'>${p.name }</span>
						     </s:else>
							<div class="controls">
							<s:iterator value="#p.defaultvalueArr" var="f" status="st">
							  <label><input type="radio" name="aptRecord.${p.mapping}" value="${f}" msg="${p.req}" <s:if test="#st.index==0"> checked="checked"</s:if>  />${f}</label>
							 </s:iterator>
							</div>  
							<s:if test='#p.req == "Y"'><font color="red">*</font></s:if>
						</s:elseif>
						<s:elseif test='#p.stype == "D"'>
							 <s:if test='#st.count%2 == 0'>
						     	<span style='background:url("/images/xc/mo/shenqing/menu_03.png");background-position:center;background-repeat: no-repeat;text-align:center;background-size:100% 100%;'>${p.name }</span>
						     </s:if>
						     <s:else>
						     	<span style='background:url("/images/xc/mo/shenqing/menu_06.png");background-position:center;background-repeat: no-repeat;text-align:center;background-size:100% 100%;'>${p.name }</span>
						     </s:else>
						     	<div class="controls">
									<input type="date" title="${p.name}" class="Hhrinput" name="aptRecord.${p.mapping}" id="${p.mapping}" value="${p.defaultvalue}" msg="${p.req}"/>
								</div>
								<s:if test='#p.req == "Y"'><font color="red">*</font></s:if>
						</s:elseif>
						<s:elseif test='#p.stype == "C"'>
							<s:if test='#st.count%2 == 0'>
						     	<span style='background:url("/images/xc/mo/shenqing/menu_03.png");background-position:center;background-repeat: no-repeat;text-align:center;background-size:100% 100%;'>${p.name }</span>
						     </s:if>
						     <s:else>
						     	<span style='background:url("/images/xc/mo/shenqing/menu_06.png");background-position:center;background-repeat: no-repeat;text-align:center;background-size:100% 100%;'>${p.name }</span>
						     </s:else>
						      <div class="controls">
						       <s:iterator value="#p.defaultvalueArr" var="f" status="st">
								<label><input type="checkbox" name="aptRecord.${p.mapping}" value="${f}" msg="${p.req}"/>${f}</label>
								</s:iterator>
						      </div>
						      <s:if test='#p.req == "Y"'><font color="red">*</font></s:if>
						</s:elseif>
						<s:elseif test='#p.stype == "S"'>
							<s:if test='#st.count%2 == 0'>
						     	<span style='background:url("/images/xc/mo/shenqing/menu_03.png");background-position:center;background-repeat: no-repeat;text-align:center;background-size:100% 100%;'>${p.name }</span>
						     </s:if>
						     <s:else>
						     	<span style='background:url("/images/xc/mo/shenqing/menu_06.png");background-position:center;background-repeat: no-repeat;text-align:center;background-size:100% 100%;'>${p.name }</span>
						     </s:else>
						     <div class="controls">
						     <select name="aptRecord.${p.mapping}">
						     <s:iterator value="#p.defaultvalueArr" var="f">
								  <option value ="${f}">${f}</option>
								</s:iterator> 
						    </select>
						    </div>
						    <s:if test='#p.req == "Y"'><font color="red">*</font></s:if>
						</s:elseif>
						<s:elseif test='#p.stype == "A"'>
							<s:if test='#st.count%2 == 0'>
						     	<span style='background:url("/images/xc/mo/shenqing/menu_03.png");background-position:center;background-repeat: no-repeat;text-align:center;background-size:100% 100%;'>${p.name }</span>
						     </s:if>
						     <s:else>
						     	<span style='background:url("/images/xc/mo/shenqing/menu_06.png");background-position:center;background-repeat: no-repeat;text-align:center;background-size:100% 100%;'>${p.name }</span>
						     </s:else>
							<div class="controls">
								<textarea title="${p.name}" name="aptRecord.${p.mapping}" rows="3"></textarea>
							</div>
							<s:if test='#p.req == "Y"'><font color="red">*</font></s:if>
						</s:elseif>
						<s:elseif test='#p.stype == "I"'>
							<s:if test='#st.count%2 == 0'>
						     	<span style='background:url("/images/xc/mo/shenqing/menu_03.png");background-position:center;background-repeat: no-repeat;text-align:center;background-size:100% 100%;'>${p.name }</span>
						     </s:if>
						     <s:else>
						     	<span style='background:url("/images/xc/mo/shenqing/menu_06.png");background-position:center;background-repeat: no-repeat;text-align:center;background-size:100% 100%;'>${p.name }</span>
						     </s:else>
							<div class="controls">
								<input type="hidden" name="aptRecord.${p.mapping}" id="aptRecord${p.mapping}"/>
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
							<s:if test='#p.req == "Y"'><font color="red">*</font></s:if>
						</s:elseif>
					</s:if>
				</li>
				</s:iterator>
				</ul>
				<span class="Hhrbutton" id="tijiao${pcid }">确认提交</span>
	</form>	
</s:if>
<s:else>
	<form action="" method="get">
		<ul>
			<li><span style='background:url("/images/xc/mo/shenqing/menu_03.png");background-position:center;background-repeat: no-repeat;text-align:center;background-size:100% 100%;'>姓名</span><input name="" type="text" class="Hhrinput"/></li>
			<li><span style='background:url("/images/xc/mo/shenqing/menu_06.png");background-position:center;background-repeat: no-repeat;text-align:center;background-size:100% 100%;'>手机号</span><input name="" type="text" class="Hhrinput"/></li>
			<li><span style='background:url("/images/xc/mo/shenqing/menu_03.png");background-position:center;background-repeat: no-repeat;text-align:center;background-size:100% 100%;'>邮箱</span><input name="" type="text" class="Hhrinput"/></li>
			<li><span style='background:url("/images/xc/mo/shenqing/menu_06.png");background-position:center;background-repeat: no-repeat;text-align:center;background-size:100% 100%;'>地址</span><input name="" type="text" class="Hhrinput"/></li>
		</ul>
		<span class="Hhrbutton">确认提交</span>
	</form>
</s:else>

</div>
</s:if>
<script type="text/javascript">
			function check(object){
				var req = object.getAttribute("msg");
				if(req == 'Y'){
					if(object.value==""){
						$.alert(object.title+"不能为空！","");
					}
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
							     		$.alert("请选择一项","");
							     		return false;
							     	}
							     }
							   }
						  }else if(obj.length == 1){
	      					if(inputs[i].value=="" || inputs[i].value==null){
	      						$.alert(inputs[i].title+"不能为空！","");
								return false;
							}
						  }
					}
				}
				return true;
			}
			
			$(document).ready(function(){
				$("#tijiao${pcid }").click(function(){
					if(formcheck()){
					$.ajax({
			                cache: true,
			                type: "POST",
			                url:"/${oname}/user/comment_record.action",
			                data:$('#myform${pcid}').serialize(),// 你的formid
			                async: false,
			                error: function(request) {
			                    console.log("Connection error");
			                },
			                success: function(data) {
			                	if(data.status<1){
			                		$.alert(data.hydesc,"");
			                	}
			                	if(data.status==1){
			                		window.location.href="${blocks[1].link}";
			                	}
			                }
			            });	
					}
				});
			});
</script>
	<%@include file="/WEB-INF/card/cardfile.jsp"%>