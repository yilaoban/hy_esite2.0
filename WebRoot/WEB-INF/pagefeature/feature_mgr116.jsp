<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/WEB-INF/pagechip/iframe_header.jsp" %>
<script type="text/javascript">
	function showDiv(id){
		$(".ca").hide();
		$("#ca_"+id).show();
	}
	function chooseCate(obj,rid){
		var category = obj.value;
		if(category==0){
			return;
		}
		showDiv(category);
		var htm = $("#dai_"+rid).html();
		$("#ca_"+category+" #caa_"+category).append("<li id='caaa_"+rid+"'>"+htm+"<input type='button' value='删除' onclick='deleteCate("+rid+")'></li>");
		$("#ca_"+category+" select").remove();
		$("#caaa_"+rid+" #categoryid"+rid).val(category);
		$("#dai_"+rid).remove();
	}
	
	function deleteCate(rid){
		$("#caaa_"+rid).remove();
	}
	
</script>
<body class="iframe_body">
<form  id="myform">
<div style="width: 100%">
	<div>
	<s:iterator value="dto.category" var = "c">
		<a href="javascript:void(0);" onclick="showDiv(${c.id})">${c.name }</a>
	</s:iterator>
	<%-- <a href="javascript:void(0);" onclick="addCg()">增加分类</a>--%>
	<input type="submit" value="保存">
	</div>
</div>
<div style="width: 100%;">
<s:iterator value="dto.category" var = "c">
	<div class="ca" id="ca_${c.id}" style="display: none">
		<ul id="caa_${c.id}">
		<s:iterator value="#c.list" var ="l">
					<li id="caaa_${l.id}">
						<img src="${imgDomain}${l.simgPath}">${l.content }
						<input type="hidden" id="recordid${l.id}" name="dto.recordid" value="${l.id}"> 
						<input type="hidden" id="categoryid${l.id}" name="dto.categoryid" value="${c.id}">
						<input type="text" name="dto.idx" value="${l.idx}">
						<input type="button" value="删除" onclick="deleteCate(${l.id})">
					</li>
		</s:iterator>
		</ul>
	</div>
</s:iterator>
</div>
<hr>
<div id="check_record_div" style="width: 100%">
	<ul id="">
	<s:iterator value="dto.list" var="r">
		<li id="dai_${r.id }"><img src="${imgDomain}${r.simgPath}">${r.content}
			<select onchange="chooseCate(this,${r.id})">
				<option value="0" selected="selected">请选择</option>
				<s:iterator value="dto.category" var = "c">
					<option value="${c.id }">${c.name }</option>
				</s:iterator>
			</select>
				<input type="hidden" id="recordid${r.id}" name="dto.recordid" value="${r.id}"> 
				<input type="hidden" id="categoryid${r.id}" name="dto.categoryid" value="${r.categoryid}">
				<input type="text" name="dto.idx" value="${r.idx }">
		</li>
	</s:iterator>
	</ul>
	<label ><s:property value="dto.list.size"/> </label>
	<input type="button" id="myform" value="保存">
</div>
</form>
<script type="text/javascript">
		$("#save116").click(function(){
			$.ajax({
		        cache: true,
		        type: "POST",
		        url:"/${oname}/page/config_sub.action?featureid=${featureid}",
		        data:$('#myform').serialize(),
		        async: false,
		        success: function(data) {
		        	if(data == "Y"){
						layer.load(2);
						setTimeout('layer.closeAll("loading");parent.layer.msg("操作成功!",{icon: 6, time: 1500},function(){closeFrame()})',2000);
		        	}else{
						parent.layer.msg("保存失败！请重试！",{icon: 6, time: 1500},3);
		        	}
		        }
		    });
		});
</script>
</body>
