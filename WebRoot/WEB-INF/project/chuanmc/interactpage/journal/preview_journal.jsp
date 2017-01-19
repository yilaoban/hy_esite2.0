<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script language="javascript">
function ju_share(cid,content,pic){
       	$("#s_content").text( content);
		var html = $("#s_Div").html();
		html ="<div id='s_Div'>"+html+"</div>";
       	$("#s_Div").remove();
		$.layer({
	    shade : [0], 
	    area : ['auto','auto'],
	    title : '分享到微博',
	    dialog : {
	        msg: html,
	        btns : 2, 
	        type : -1,
	        yes  : function(index){
	        	//$("#s_content").val();
	        	layer.alert("分享成功!",1);
	        	//TODO...
	        },
	    },
        end : function(index){
        	$("#shareDiv").append(html);
        	layer.close(index);
        }
	});
}
</script>
<ul>
  <li> 期刊标题：${journal.title } </li>
  <s:iterator value="journal.contents" var="c" status="st">
  	<li>
  		<p>内容标题${st.count}:${c.title }</p>
  		<p>${c.content }</p>
  		<s:if test='#c.bimg!=null &&　#c.bimg!=""'><img src="${imgDomain }${c.bimg}"></s:if>
  		<s:if test='#c.simg!=null &&　#c.simg!=""'><img src="${imgDomain }${c.simg}"></s:if>
  		<s:if test='journal.isshare=="Y"'><a href="javascript:void(0);" onclick="ju_share(${c.id},'${c.sharecontent}','${c.sharepic}')">分享</a></s:if>
  	</li>
  </s:iterator>
</ul>
<div id="shareDiv" style="display:none">
<div id="s_Div">
	<textarea id="s_content" rows="10" cols="30"></textarea>
	<input type="hidden" name="sharepic" />
	<input type="hidden" name="contentid" />
	<input type="hidden" name="wbuid" value="${wbuid}" />
	<input type="hidden" name="pageid" value="${pageid }" />
	<input type="button" value="果断分享">
</div>
</div>