<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<input id='cardid' type='hidden' value='${pcid}' />
<!-- 会员注册 -->
<link href="/css/HYzhuce/index.css" rel="stylesheet" type="text/css" />

<%@include file="/WEB-INF/card/background.jsp"%>

<s:if test='blocks[0].display=="Y"'>
<div class="Hhrbanner block ${blocks[0].ctname}" status="0" hyct="Y" style="${blocks[0].hyct}" hyblk="S" hydata="${blocks[0].rid}"><img style="width:100%;height:100%;" src="${blocks[0].img}" /></div>
</s:if>
<s:if test='blocks[1].display=="Y"'>
<div class="Hhrbox clearfix block ${blocks[1].ctname}" status="0" hyct="Y" style="${blocks[1].hyct}" hyblk="S" hydata="${blocks[1].rid}">
<s:if test='blocks[1].obj.id > 0'>
	<form id="myform${pcid }" method="post" class="preview">
		<s:set name="obj" value='blocks[1].obj' />
			 	<input type="hidden" name="aptRecord.aptid" value="${obj.id}" />
			 	<input type="hidden" name="relationid" value="${blocks[1].rid}">
			 	<input type="hidden" name="aptRecord.pageid" value="${pageid}">
			 	<ul>
				<s:iterator value="#obj.list" var="p" status="st">
				<li>
					<s:if test='#p.isshow == "Y"'>
						<s:if test='#p.stype == "T"'>
						     <span>${p.name }</span>
						     <input type="text" title="${p.name }" class="Hhrinput" name="aptRecord.${p.mapping}" value="${p.defaultvalue}" id="${p.mapping}" msg="${p.req}" onblur="check(this)" />
						     <s:if test='#p.req == "Y"'><font color="red">*</font></s:if>
						</s:if>
						<s:if test='#p.stype == "E"'>
						     <span>${p.name }</span>
						     <input type="email" title="${p.name }" class="Hhrinput" name="aptRecord.${p.mapping}" value="${p.defaultvalue}" id="${p.mapping}" msg="${p.req}" onblur="check(this)" />
						     <s:if test='#p.req == "Y"'><font color="red">*</font></s:if>
						</s:if>
						<s:if test='#p.stype == "N"'>
						     <span>${p.name }</span>
						     <input type="number" title="${p.name }" class="Hhrinput" min="0" name="aptRecord.${p.mapping}" value="${p.defaultvalue}" id="${p.mapping}" msg="${p.req}" onblur="check(this)" />
						     <s:if test='#p.req == "Y"'><font color="red">*</font></s:if>
						</s:if>
						<s:elseif test='#p.stype == "R"'>
							<span>${p.name }</span>
								<s:iterator value="#p.defaultvalueArr" var="f" status="st">
								 <input style="margin-left:10px;" type="radio" name="aptRecord.${p.mapping}" value="${f}" msg="${p.req}" <s:if test="#st.index==0"> checked="checked"</s:if>  />${f}
								 </s:iterator>
							<s:if test='#p.req == "Y"'><font color="red">*</font></s:if>
						</s:elseif>
						<s:elseif test='#p.stype == "D"'>
								<span>${p.name }</span>
								<input type="date" title="${p.name }" class="Hhrinput" name="aptRecord.${p.mapping}" id="${p.mapping}" value="${p.defaultvalue}" msg="${p.req}"/>
								<s:if test='#p.req == "Y"'><font color="red">*</font></s:if>
						</s:elseif>
						<s:elseif test='#p.stype == "C"'>
							<span>${p.name }</span>
						       <s:iterator value="#p.defaultvalueArr" var="f" status="st">
								<input style="margin-left:10px;" type="checkbox" name="aptRecord.${p.mapping}" value="${f}" msg="${p.req}"/>${f}
								</s:iterator>
						      <s:if test='#p.req == "Y"'><font color="red">*</font></s:if>
						</s:elseif>
						<s:elseif test='#p.stype == "S"'>
						     <span>${p.name }</span>
						     <select name="aptRecord.${p.mapping}">
						     <s:iterator value="#p.defaultvalueArr" var="f">
								  <option value ="${f}">${f}</option>
								</s:iterator> 
						    </select>
						    <s:if test='#p.req == "Y"'><font color="red">*</font></s:if>
						</s:elseif>
						<s:elseif test='#p.stype == "A"'>
						<span>${p.name }</span>
								<textarea title="${p.name }" name="aptRecord.${p.mapping}" rows="3"></textarea>
							<s:if test='#p.req == "Y"'><font color="red">*</font></s:if>
						</s:elseif>
					</s:if>
				</li>
				</s:iterator>
				</ul>
   				<input type="button" class="Hhrbutton" id="tijiao${pcid }" value="提交信息" />
	</form>	
</s:if>
<s:else>
	<form action="" method="get">
		<ul>
			<li><span>姓名：</span><input name="" type="text" class="Hhrinput"/></li>
			<li><span>手机号：</span><input name="" type="text" class="Hhrinput"/></li>
			<li><span>邮箱：</span><input name="" type="text" class="Hhrinput"/></li>
			<li><span>地址：</span><input name="" type="text" class="Hhrinput"/></li>
		</ul>
		<input name="提交申请" type="button"  class="Hhrbutton" id="提交申请" value="提交申请"/>
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
			                success: function(data) {
			                	hdCallBack(data,"N");
			                }
			            });
					}
				});
			});
			
</script>
<%@include file="/WEB-INF/card/dzpfileTprize.jsp"%>