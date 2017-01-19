<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 微申请1 -->
<link rel="stylesheet" href="/css/hudong/shenqing3/list.css" type="text/css"></link>
<%@include file="/WEB-INF/card/background.jsp"%>
	<s:if test='blocks[0].display=="Y"'>
	<div class="shenqing_141120_c clearfix block ${blocks[0].ctname}" status="0" hyct="Y" style="${blocks[0].hyct}" hyblk="I" hydata="${blocks[0].rid}" class_data="hy2014051841526">
	<s:if test='blocks[0].obj.id > 0'>
	<form id="myform${pcid }" method="post" class="preview" >
		<s:set name="obj" value="blocks[0].obj" />
			 	<input type="hidden" name="aptRecord.aptid" value="${obj.id}" />
			 	<input type="hidden" name="relationid" value="${blocks[0].rid}">
			 	<input type="hidden" name="aptRecord.pageid" value="${pageid}">
				<s:iterator value="#obj.list" var="p" status="st">
					<s:if test='#p.isshow == "Y"'>
						<s:if test='#p.stype == "T"'>
							<div class="control-form">
						     <label class="control-label" for="${p.mapping}">${p.name }</label>
						     <div class="controls">
						     <input type="text" title="${p.name}" name="aptRecord.${p.mapping}" value="${p.defaultvalue}" id="${p.mapping}" msg="${p.req}" onblur="check(this)" />
						     <s:if test='#p.req == "Y"'><font color="red">*</font></s:if>
						     </div>
							</div>
						</s:if>
						<s:if test='#p.stype == "E"'>
							<div class="control-form" >
						     <label class="control-label" for="${p.mapping}">${p.name }</label>
						     <div class="controls" >
						     <input type="email" title="${p.name}" name="aptRecord.${p.mapping}" value="${p.defaultvalue}" id="${p.mapping}" msg="${p.req}" onblur="check(this)" />
						     <s:if test='#p.req == "Y"'><font color="red">*</font></s:if>
						     </div>
							</div>
						</s:if>
						<s:if test='#p.stype == "N"'>
							<div class="control-form" class_data="hy2014051841961">
						     <label class="control-label" class_data="hy2014051841324" for="${p.mapping}">${p.name }</label>
						     <div class="controls" class_data="hy2014051841962">
						     <input type="number" title="${p.name}" min="0" name="aptRecord.${p.mapping}" value="${p.defaultvalue}" id="${p.mapping}" msg="${p.req}" onblur="check(this)" />
						     <s:if test='#p.req == "Y"'><font color="red">*</font></s:if>
						     </div>
							</div>
						</s:if>
						<s:elseif test='#p.stype == "R"'>
						<div class="control-form" class_data="hy2014051841877">
							<label class="control-label" class_data="hy2014051841424">${p.name }</label>
								<div class="controls" class_data="hy2014051841799">
								<s:iterator value="#p.defaultvalueArr" var="f" status="st">
								  <input class="dy_sq_left_141120_1" type="radio" name="aptRecord.${p.mapping}" value="${f}" msg="${p.req}" <s:if test="#st.index==0"> checked="checked"</s:if>  />${f}&nbsp;&nbsp;
								 </s:iterator>
								 <s:if test='#p.req == "Y"'><font color="red">*</font></s:if>
								</div>  
					      </div>
						</s:elseif>
						<s:elseif test='#p.stype == "D"'>
							<div class="control-form" class_data="hy2014051841824">
								<label class="control-label" class_data="hy2014051841291" for="${p.mapping}">${p.name }</label>
								<div class="controls" class_data="hy2014051841873">
									<input type="date" title="${p.name}" name="aptRecord.${p.mapping}" id="${p.mapping}" value="${p.defaultvalue}" msg="${p.req}"/>
									<s:if test='#p.req == "Y"'><font color="red">*</font></s:if>
								</div>
							</div>
						</s:elseif>
						<s:elseif test='#p.stype == "C"'>
						<div class="control-form" class_data="hy2014051841307">
							<label class="control-label" class_data="hy2014051841480">${p.name }</label>
						      <div class="controls" class_data="hy2014051841754">
						       <s:iterator value="#p.defaultvalueArr" var="f" status="st">
								<input class="dy_sq_left_141120_1" type="checkbox" name="aptRecord.${p.mapping}" value="${f}" msg="${p.req}"/>${f}
								</s:iterator>
								<s:if test='#p.req == "Y"'><font color="red">*</font></s:if>
						      </div>
						</div>
						</s:elseif>
						<s:elseif test='#p.stype == "S"'>
						 <div class="control-form" class_data="hy2014051841516">
						     <label class="control-label" class_data="hy2014051841340">${p.name }</label>
						     <div class="controls" class_data="hy2014051841254">
						     <select name="aptRecord.${p.mapping}" class_data="hy2014051841477">
						     <s:iterator value="#p.defaultvalueArr" var="f">
								  <option value ="${f}" class_data="hy2014051841587">${f}</option>
								</s:iterator> 
						    </select>
						    <s:if test='#p.req == "Y"'><font color="red">*</font></s:if>
						    </div>
						   </div>
						</s:elseif>
						<s:elseif test='#p.stype == "A"'>
						 <div class="control-form">
						<label class="control-label" class_data="hy2014051841807">${p.name }</label>
							<div class="controls" class_data="hy2014051841408">
								<textarea name="aptRecord.${p.mapping}" title="${p.name}" rows="3"></textarea>
								<s:if test='#p.req == "Y"'><font color="red">*</font></s:if>
							</div>
						</div>
						</s:elseif>
						<s:elseif test='#p.stype == "I"'>
						 <div class="control-form">
						<label class="control-label" class_data="hy2014051841807">${p.name }</label>
							<div class="controls" class_data="hy2014051841408">
								<input type="hidden" name="aptRecord.${p.mapping}" id="aptRecord${p.mapping}"/>
								<div id="as${p.mapping}"></div>
								<div id="picker${p.mapping}">选择图片</div>
								<s:if test='#p.req == "Y"'><font color="red">*</font></s:if>
								<script type="text/javascript">
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
						</div>
						</s:elseif>
					</s:if>
				</s:iterator>
				 <div class="control-form">
	   					<div class="controls" class_data="hy2014051841408" style="text-align:center;">
			   				<input type="button" id="tijiao${pcid }" value="${blocks[0].style118.btcontent}" />
						</div>
					</div>
	</form>	
	</s:if>
	<s:else>
	<form action="" method="get">
		<div class="control-form">
	     <label class="control-label">申请的产品</label>
	     <div class="controls">
	       <input type="checkbox" name=""/>微营销系统
	       <input type="checkbox" name=""/>邮件营销系统
	     </div>
		</div>
		<div class="control-form">
	     <label class="control-label">选择的理由</label>
	     <div class="controls">
	        <input name="" type="radio" value=""/>微营销系统
	        <input name="" type="radio" value=""/>邮件营销系统
	     </div>
		</div>
		<div class="control-form">
	     <label class="control-label">使用年限</label>
	     <div class="controls">
		     <select name="">
		     <option>一年-两年</option>
		     <option">两年-三年</option> 
		   </select>
	     </div>
		</div>
		<div class="control-form">
	     <label class="control-label">姓名</label>
	     <div class="controls">
	     <input type="text" name="textfield" id="textfield" />
	     </div>
		</div>
		<div class="control-form">
	     <label class="control-label">备注</label>
	     <div class="controls">
	     <textarea name="" rows="3" ></textarea>
	     </div>
		</div>
		 <div class="control-form">
 					<div class="controls" style="text-align:center;">
   				<input type="button" value="提交信息" />
			</div>
		</div>
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
				$(".control-form").attr("style","margin-bottom:${blocks[0].style118.bottom}px;");
				$(".control-form label").attr("style","color:${blocks[0].style118.color};font-size:${blocks[0].style118.size}px;");
				$("#tijiao${pcid }").attr("style","color:${blocks[0].style118.btcolor};font-size:${blocks[0].style118.btsize}px;background:rgba(${blocks[0].style118.btbg},${blocks[0].style118.bttm});");
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
			                	hdCallBack(data,"N");
			                }
			            });	
					}
				});
			});
</script>
<%@include file="/WEB-INF/card/dzpfileTprize.jsp"%>
<%@include file="/WEB-INF/card/cardfile.jsp"%>