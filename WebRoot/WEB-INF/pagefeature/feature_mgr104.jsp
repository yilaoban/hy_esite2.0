<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/WEB-INF/pagechip/iframe_header.jsp" %>
<body class="iframe_body">
<script type="text/javascript">
	//实例化编辑器
	var um = UE.getEditor('myEditor');
</script>
<form id="myform">
<div class="frame_content">
<p>新闻标题:<input type="text" name="dto.notice.title" class="text-medium" value="${dto.notice.title}"/></p>
<p>新闻内容:
<script type="text/plain" id="myEditor" name="dto.notice.content" style="width:100%;height:200px;">${dto.notice.content }</script>
</p>
<p>新闻图片:<input type="file" name="dto.img"></p>
<p><img src="${imgDomain }${dto.notice.imgurl }"/></p>
<p>开奖时间:<select name="dto.notice.hour"><s:iterator begin="0" end="23" status="st"><option value="${st.index}" <s:if test="dto.notice.hour==#st.index">selected="selected"</s:if>  >${st.index}</option></s:iterator></select>时
 		<select name="dto.notice.minute"><s:iterator begin="0" end="59" status="st" ><option value="${st.index}" <s:if test="dto.notice.minute==#st.index">selected="selected"</s:if> >${st.index}</option></s:iterator></select>分
		<select name="dto.notice.second"><s:iterator begin="0" end="59" status="st" ><option value="${st.index}" <s:if test="dto.notice.second==#st.index">selected="selected"</s:if> >${st.index}</option></s:iterator></select>秒</p>
<p>星期几:<select name="dto.notice.countdownWeekday">
		<option value="1" <s:if test="dto.notice.countdownWeekday==1">selected="selected"</s:if>>星期一</option>
		<option value="2" <s:if test="dto.notice.countdownWeekday==2">selected="selected"</s:if>>星期二</option>
		<option value="3" <s:if test="dto.notice.countdownWeekday==3">selected="selected"</s:if>>星期三</option>
		<option value="4" <s:if test="dto.notice.countdownWeekday==4">selected="selected"</s:if>>星期四</option>
		<option value="5" <s:if test="dto.notice.countdownWeekday==5">selected="selected"</s:if>>星期五</option>
		<option value="6" <s:if test="dto.notice.countdownWeekday==6">selected="selected"</s:if>>星期六</option>
		<option value="7" <s:if test="dto.notice.countdownWeekday==7">selected="selected"</s:if>>星期天</option>
	</select></p>
	<p>
<input type="button" id="save104" value="保存"/>
<input type="button" value="取消" onclick="closeFrame()"/></p>
<input type="hidden" name="dto.notice.id" value="${dto.notice.id}" />
<input type="hidden" name="dto.notice.imgurl" value="${dto.notice.imgurl}">
</div>
</form>
<script type="text/javascript">
		$("#save104").click(function(){
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
</html>