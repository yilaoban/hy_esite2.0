<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/WEB-INF/pagechip/iframe_header.jsp" %>
<body class="iframe_body">
<form id="myform">
<div class="frame_content">
	 			<p>选择上传点:<select name="dto.uploadid" onchange="showuploadrecord()">
	 				<option value="0">-请选择-</option>
	 				<s:iterator value="dto.fidList" var="f">
	 				<option value="${f.id }" <s:if test='dto.uploadid==id'>selected="selected"</s:if>>${f.content }</option>
	 				</s:iterator>
	 			</select>
	 			状态:<select onchange="changDiv()" id="cs">
	 			<option value="EDT" selected="selected">待审核</option>
	 			<option value="CMP">审核通过</option>
	 			<option value="DEL">审核不通过</option>
	 			</select>
	 			</p>
	 			<div id="EDT">
		 			<table width="100%">
						<tr>
							<th>序号</th>
							<th>上传人</th>
							<th>上传时间</th>
							<th>图片</th>
							<th>操作</th>
						</tr>		 			
		 			<s:iterator value="dto.record" var="record" status="st">
		 			<s:if test='status=="EDT"'>
		 				<tr>
		 					<td>${record.id }</td>
		 					<td>${record.nickName }</td>
		 					<td><s:date name="uploadtime" format="yyyy-MM-dd HH:mm"/></td>
		 					<td><a href="${imgDomain }${record.bigimg }"target="_blank" class="vab"><img src="${imgDomain }${record.smallimg }" height="40" width="40"></a></td>
		 					<td>
		 						<input type="hidden" name="dto.record[${st.index }].id"  value="${record.id }"/>
		 						<label class="forradio"><input type="radio" name="dto.record[${st.index }].status" value="CMP"/>审核通过</label>
		 						<label class="forradio"><input type="radio" name="dto.record[${st.index }].status" value="DEL"/>审核不通过</label>
		 					</td>
		 				</tr>
		 			</s:if>
		 			</s:iterator>
		 			</table>
	 			</div>
	 			<div id="CMP" style="display: none;">
		 			<table width="100%">
						<tr>
							<th>序号</th>
							<th>上传人</th>
							<th>上传时间</th>
							<th>图片</th>
						</tr>		 			
		 			<s:iterator value="dto.record" var="record" status="st">
		 			<s:if test='status=="CMP"'>
		 				<tr>
		 					<td>${record.id }</td>
		 					<td>${record.nickName }</td>
		 					<td><s:date name="uploadtime" format="yyyy-MM-dd HH:mm"/></td>
		 					<td><a href="${imgDomain }${record.bigimg }"target="_blank" class="vab"><img src="${imgDomain }${record.smallimg }" height="40" width="40"></a></td>
		 				</tr>
		 			</s:if>
		 			</s:iterator>
		 			</table>
	 			</div>
	 			<div id="DEL"  style="display: none;">
		 			<table width="100%">
						<tr>
							<th>序号</th>
							<th>上传人</th>
							<th>上传时间</th>
							<th>图片</th>
						</tr>		 			
		 			<s:iterator value="dto.record" var="record" status="st">
		 			<s:if test='status=="DEL"'>
		 				<tr>
		 					<td>${record.id }</td>
		 					<td>${record.nickName }</td>
		 					<td><s:date name="uploadtime" format="yyyy-MM-dd HH:mm"/></td>
		 					<td><a href="${imgDomain }${record.bigimg }"target="_blank" class="vab"><img src="${imgDomain }${record.smallimg }" height="40" width="40"></a></td>
		 				</tr>
		 			</s:if>
		 			</s:iterator>
		 			</table>
	 			</div>
	 			<input type="button" id="save107" value="确定"/>
	 			<input type="button" value="取消" onclick="closeFrame()"/>
</div>
</form>
		<script type="text/javascript">
			$(function(){
				$("a.image_gall").popImage();
			});
		</script>
</body>
<script type="text/javascript">
		$("#save107").click(function(){
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
