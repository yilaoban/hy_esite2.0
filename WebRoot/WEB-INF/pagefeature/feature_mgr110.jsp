<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/WEB-INF/pagechip/iframe_header.jsp" %>
<body class="iframe_body">
<form  id="myform">
<input type="hidden" name="dto.fid" value="${fid}" />
<div>
		<input type="button" value="添加新闻" onclick="addnewslist(${fid })"/>
		<table width="60%" id="table">
			<tr>
				<td>新闻标题</td>
				<td>排序(从小到大)</td>
				<td>&nbsp;</td>
			</tr>
			<s:iterator value="dto.list" var="l" status="newsstatus">
				<tr class="exist" id="${newsstatus.index }">
					<td>${l.news.title }</td>
					<td>
					<input type="hidden" name="dto.list[${newsstatus.index }].id" value="${l.id }"/>
					<input type="hidden" name="dto.list[${newsstatus.index }].nlid" value="${l.nlid }"/>
					<input type="hidden" name="dto.list[${newsstatus.index }].nid" value="${l.news.id }">
					<input type="text" name="dto.list[${newsstatus.index }].idx" value="${l.idx }"/></td>
					<td><a href="javascript:delnews('${newsstatus.index }')">删除</a></td>
				</tr>
			</s:iterator>
		</table>
		详情页面:
	      <select name="dto.pageid">
	      	<s:iterator value="dto.pagelist" var='p'>
	      		<option value="${p.id}" <s:if test='#p.id == dto.pageid'>selected="selected"</s:if> >${p.name }</option>
	      	</s:iterator>
	      </select>
		<input  type="button" id="save110" value="确定"/><input  type="button" value="取消" onclick="closeFrame()"/>
</div>
</form>
<div id="newsData" style="display: none">
</div>
<script type="text/javascript">
		$.post("/content/ajax_find_news.action",function(data){
			$.each(data,function(index,value){
				$("#newsData").append("<input name='nnid' type='checkbox' value='"+value.id+"'/>"+value.title+"&nbsp;&nbsp;&nbsp;&nbsp;");
			});
		});
</script>
<script type="text/javascript">
		$("#save110").click(function(){
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
