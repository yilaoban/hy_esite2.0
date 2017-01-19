<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="/js/rbga.js"></script>
<div class="popup-header">
  <button type="button" class="close closePopup"><span>&times;</span></button>
  <ul class="nav nav-tabs" role="tablist">
    <li id="tab_content" role="presentation" class="active"><a aria-controls="home" role="tab" data-toggle="tab">内容</a></li>
  </ul>
</div>
<div class="popup-body">
	<form id="form1" name="form1" action="/page/blockSub.action" method="post" class="blockview clearfix">
	<s:iterator value="dto.tb.attr" var="j" status="st">
		<div class="edit_content">
			<%--单行 --%>
			<s:if test='#j.value.type=="T" || #j.value.type=="S"'>
				<dl>
				 	<dt>${j.value.mapping}</dt>
					<dd>
						<input type="text" class="text-pop form-control" name="${j.key }" id="var_${j.key }" />${j.value.desc }
					</dd>
				</dl>
			</s:if>
			</div>
		</s:iterator>
		<input type="hidden" id="ct_value" name="hyct">
	</form> 
</div>
    <div class="popup-footer">
<input type="hidden" id="relationid" value="${relationid}">
  <button type="button" class="btn btn-default closePopup">关闭</button>
  <button type="button" class="btn btn-primary" onclick="onClik(${dto.pbr.pcid})">保存</button>
</div>
<script>
$(".closePopup").click(function(){
	$("#rightPopup").animate({width:'hide'});
});

$(document).ready(function() {
	<s:iterator value="dto.pbr.jsonObject" var="j">
   		var id = '${j.key}';
   		var v = '${j.value}';
   		v = v.replace(/&apos;/g,"'");
   		$("#var_"+id).val(v).text(v).attr("hydata",v);
   	</s:iterator>
});
</script>