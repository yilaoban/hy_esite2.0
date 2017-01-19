<%@ page contentType='text/html;charset=utf-8' pageEncoding='utf-8'%>
<%@ taglib uri='/struts-tags' prefix='s'%>
<!-- 合伙人表单 -->
<link href="/css/cb/partner_1.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	$(document).ready(function(){
		$.post("/${oname}/user/findSender.action",function(data){
			if(data == 1){
				window.location.href='${blocks[0].link}';
			}
		});
	})

	var kv = $('#hy_kv').val();
	if(!isNaN(kv)){
		$('#fatherid').val(kv);
	}
	function tijiao(){
		if(formcheck()){
			$.ajax({
				cache: true,
				type: "POST",
				url:'/${oname}/user/cbSenderAptRecord.action',
				data:$('#myform').serialize(),// 你的formid
				async: false,
				error: function(request) {
				},
				success: function(data) {
					if(data > 0){
						$.alert("提交成功!");
						window.location.href='${blocks[0].link}';
					}else if(data == -1){
						$.alert("您申请过合伙人啦!");
					}else{
						$.alert("提交失败!");
					}
				}
			});
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
	
	function check(object){
		var req = object.getAttribute("msg");
		if(req == 'Y'){
			if(object.value==""){
				$.alert(object.title+"不能为空！","");
			}
		}
	}
</script>

<%@include file="/WEB-INF/card/background.jsp"%>
<div class="tj0530link block" status="0" hyct="Y" hydata="${blocks[0].rid}" <s:if test='method != "E"'>style="display:none;"</s:if>><a href="${blocks[0].link}">链接</a></div>  
<div class="main0530 block" status="0" hyct="Y" style="${blocks[1].hyct}" hydata="${blocks[1].rid}">
	<h2>合伙人简介</h2>
	<p>合伙人是指投资组成合伙企业，参与合伙经营的组织和个人，是合伙企业的主体，了解合伙企业首先要了解合伙人。合伙人在法学中是一个比较普通的概念，通常是指以其资产进行合伙投资，参与合伙经营，依协议享受权利，承担义务，并对企业债务承担无限（或有限）责任的自然人或法人。合伙人应具有民事权利能力和行为能力。</p>
	<h2>合伙人申请</h2>
	<form action="/${oname}/user/cbSenderAptRecord.action" id="myform">
	<ul>
	<s:if test='blocks[1].obj.id > 0'>
		<s:set name="obj" value='blocks[1].obj' />
		<input type="hidden" name="aptRecord.pageid" value="${pageid}">
		<input type="hidden" name="fatherid" value="0" id="fatherid">
	 	<input type="hidden" name="aptRecord.aptid" value="${obj.id}" />
	 	<!-- 
	 	<input type="hidden" name="relationid" value="${blocks[1].rid}">
	 	 -->
	 	<s:iterator value="#obj.list" var="p" status="st">
	 		<li>
				<s:if test='#p.isshow == "Y"'>
					<s:if test='#p.stype == "T"'>
						<span>${p.name }：</span>
					     <input type="text" title="${p.name }" name="aptRecord.${p.mapping}" value="${p.defaultvalue}" id="${p.mapping}" msg="${p.req}" onblur="check(this)" />
					     <s:if test='#p.req == "Y"'><font color="red">*</font></s:if>
					</s:if>
					<s:if test='#p.stype == "N"'>
						<span>${p.name }：</span>
					     <input type="tel" title="${p.name }" name="aptRecord.${p.mapping}" value="${p.defaultvalue}" id="${p.mapping}" msg="${p.req}" onblur="check(this)" />
					     <s:if test='#p.req == "Y"'><font color="red">*</font></s:if>
					</s:if>
					<s:elseif test='#p.stype == "R"'>
						<span>${p.name }：</span>
							<s:iterator value="#p.defaultvalueArr" var="f" status="st">
							 <input type="radio" name="aptRecord.${p.mapping}" value="${f}" msg="${p.req}" <s:if test="#st.index==0"> checked="checked"</s:if>  />&nbsp;${f}&nbsp;&nbsp;
							 </s:iterator>
						<s:if test='#p.req == "Y"'><font color="red">*</font></s:if>
					</s:elseif>
				</s:if>
			</li>
	 	</s:iterator>
	</s:if>
	<s:else>
			<li><label for=""><span>姓名</span><input type="text" id="name" name="aptRecord.name" placeholder="请输入您的姓名"></label></li>
			<li><label for="Gender"><span>性别</span><input type="radio" name="aptRecord.gender" value="男" checked="checked">男</label><input type="radio" name="aptRecord.gender" class="jx" value="女">女</label></li>
			<li><label for=""><span>电话</span><input id="tel" type="tel" name="aptRecord.phone" placeholder="请输入您的电话号码" onkeyup="this.value = this.value.replace(/\D/g,'');"></label></li>
			<li><label for=""><span>备注</span><textarea name="aptRecord.f1" id=""></textarea></label></li>
	</s:else>
	</ul>
	</form>
	<s:if test='blocks[1].obj.id > 0'>
		<s:if test='method != "E"'>
		<div class="tj0530"><span onclick="tijiao()">提交信息</span></div>     
		</s:if>
	</s:if>
</div>
<%@include file="/WEB-INF/card/cardfile.jsp"%>