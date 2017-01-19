<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/WEB-INF/pagechip/iframe_header.jsp" %>

<body class="iframe_body">
	<form id="myform">
	<input type="hidden" name="dto.fid" value="${fid}" />
	<p id="label"></p>
	  <s:iterator value="dto.list" var="l">
           <label class="forradio"><input type="radio" value="${l.id}" name="dto.cbid" <s:if test="dto.cb.id==id"> checked="checked" </s:if> /> ${l.title} </label><br />
      </s:iterator>
	</form>
<input type="button" value="确定" onclick="onClikForum1(${fid},${featureid})"/>
<input type="button" value="取消" onclick="closeFrame()"/>
</body>
<script type="text/javascript">
	function onClikForum1(fid,featureid){
	var s = document.getElementsByName("dto.cbid");
	if(s.length == 0){
		alert("请选择一项");
		return;
	}
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
}
</script>


