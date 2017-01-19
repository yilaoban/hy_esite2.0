<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/WEB-INF/pagechip/iframe_header.jsp" %>

<body class="iframe_body">
<form id="myform">
	<input type="hidden" name="dto.fid" value="${fid}" />
	  <s:iterator value="dto.emailPeriodical" var="ep" status="st">
	  	<table>
		  <tr>
		    <td>
	           <s:if test="dto.moduleList.contains(#ep.id)">
					<s:if test='#ep.publish == "Y"'>
						<label class="forradio"><input disabled="disabled"  name="dto.list" type="checkbox" value="${ep.id }" checked="checked"/>${ep.id }-${ep.title}</label>
						<input name="dto.list" type="hidden" value="${ep.id }" checked="checked"/>
					</s:if>
					<s:else>
						<label class="forradio"><input <s:if test='#ep.publish == "Y"'>disabled="disabled"</s:if> id="${ep.id }" name="dto.list" type="checkbox" value="${ep.id }" checked="checked"/>${ep.id }-${ep.title}</label>
					</s:else>
				</s:if>
				<s:elseif test='#ep.publish == "Y"'>
					<label class="forradio"><input name="dto.list" type="checkbox" value="${ep.id }" checked="checked" disabled="disabled"/>${ep.id }-${ep.title}</label>
					<input name="dto.list" type="hidden" value="${ep.id }" checked="checked"/>
				</s:elseif>
				<s:else>
					<label class="forradio"><input id="${ep.id }" name="dto.list" type="checkbox" value="${ep.id }"/>${ep.id }-${ep.title}</label>
				</s:else>
			</td>
			<td>名称：<input type="text" id="n_${ep.id}" value="${ep.name }"/></td>
            <td>邮件排列顺序：<input type="text" id="i_${ep.id}" value="0"/></td>
        	</tr>
        </table>
           	
      </s:iterator>
	<input type="button" id="save122" value="确定" />
	<input type="button" value="取消" onclick="closeFrame()"/>
</form>
<script type="text/javascript">
		$("#save122").click(function(){
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
