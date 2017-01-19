<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/WEB-INF/pagechip/iframe_header.jsp" %>

<body class="iframe_body">
	<form id="myform">
	<input type="hidden" name="dto.fid" value="${fid}" />
	<p id="label"></p>
	  <s:iterator value="dto.bbsForumList" var="forum">
           <label class="forradio"><input type="checkbox" value="${forum.id}" name="dto.forumid" <s:if test="dto.froumidList.contains(#forum.id)"> checked="checked" </s:if> /> ${forum.title} </label><br />
      </s:iterator>
	</form>
<input type="button" value="确定" onclick="onClikForum1(${fid},${featureid})"/>
<input type="button" value="取消" onclick="closeFrame()"/>

<script type="text/javascript">
	function onClikForum1(fid,featureid){
	var s = document.getElementsByName("dto.forumid");
	if(s.length == 0){
		alert("请选择一项");
		return;
	}
	var ids="[";
	for(i= 0;i<s.length;i++){
		if(s[i].checked){
			if(i != s.length - 1){
				ids+=s[i].value+",";
			}else{
				ids+=s[i].value;
			}
		}
	}
	ids += "]";
	$.post("/page/config_sub.action",{"dto.fid":fid,"featureid":featureid,"dto.forumid":ids},function(data){
       	if(data == "Y"){
			layer.load(2);
			setTimeout('layer.closeAll("loading");parent.layer.msg("操作成功!",{icon: 6, time: 1500},function(){closeFrame()})',2000);
       	}else{
			parent.layer.msg("保存失败！请重试！",{icon: 6, time: 1500},3);
       	}
	});	
}
</script>
</body>