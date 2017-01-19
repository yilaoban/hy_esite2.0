<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/WEB-INF/pagechip/iframe_header.jsp" %>
<body class="iframe_body">
<form id="myform">
<div class="frame_content">
	 			<p>选择口碑:
	 				<table width="80%">
		 				<s:iterator value="dto.list" var="f" status="st">
		 					<s:if test="#st.count==1">
		 						<tr>
		 					</s:if>
								<td><input type="radio" name="dto.spreadid" value="${f.id }" <s:if test='dto.spreadid ==0 && #st.index==0'>checked="checked"</s:if><s:if test='dto.spreadid==id'>checked="checked"</s:if> />${f.title }</td>
							<s:if test="((#st.count+1)%2==1)||#st.last">
								</tr>
							</s:if>
		 				</s:iterator>
	 				</table>
	 			</p>
	 			<input type="button" id="save134" value="确定"/>
	 			<input type="button" value="取消" onclick="closeFrame()"/>
</div>
</form>
<script type="text/javascript">
		$("#save134").click(function(){
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