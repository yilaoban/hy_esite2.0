<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script language="javascript">
	function zfshare(length){
		var random = fRandomBy(1,length);
		var html = $("#sre_"+random).html();
		html ="<div id='sre_"+random+"'>"+html+"</div>";
       	$("#sre_"+random).remove();
		$.layer({
	    shade : [0], 
	    area : ['auto','auto'],
	    title : '分享到微博',
	    dialog : {
	        msg: html,
	        btns : 2, 
	        type : -1,
	        yes  : function(index){
	        	var content=$("#textarea_"+random).val();
	        	var pic = $("#img_"+random).attr("src");
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
	
	function zfforward(){
		var html = $("#fwd_div").html();
		html ="<div id='fwd_div'>"+html+"</div>";
       	$("#fwd_div").remove();
		$.layer({
	    shade : [0], 
	    area : ['auto','auto'],
	    title : '转发到微博',
	    dialog : {
	        msg: html,
	        btns : 2, 
	        type : -1,
	        yes  : function(index){
	        	var content=$("#fwd_div").val();
	        	layer.alert("转发成功!",1);
	        	//TODO...
	        },
	    },
        end : function(index){
        	$("#shareDiv").append(html);
        	layer.close(index);
        }
	});
	}
	
	function fRandomBy(under, over){ 
		switch(arguments.length){ 
		case 1: return parseInt(Math.random()*under+1); 
		case 2: return parseInt(Math.random()*(over-under+1) + under); 
		default: return 0; 
		} 
	}
</script>
<ul>
  <li> 传播标题：${spread.title } </li>
  <li> 传播说明：${spread.content }</li>
  <li>
  	<s:set name="olength" value="spread.options.size()"></s:set>
  	<s:if test='spread.type=="SRE"'>
  		<input type="button" onclick="zfshare(${olength})" value="直发"/>
  	</s:if>
  	<s:elseif test='spread.type=="FWD"'>
  		<input type="button" onclick="zfforward()" value="转发"/>
  	</s:elseif>
  	<s:else>
  		<input type="button" onclick="zfforward()" value="转发并评论"/>
  	</s:else>
  	<input type="button" onclick="window.close()" value="关闭"/>
  </li>
</ul>
<div id="shareDiv" style="display:none">
<s:if test='spread.type=="SRE"'>
<s:iterator value="spread.options" var="o" status="st">
  		<div id="sre_${st.count }" >
  			<textarea id="textarea_${st.count }">${o.content}</textarea>
  			<span><s:if test='#o.pic != null && #o.pic !="" '><img src="${imgDomain}${o.pic}" id="img_${st.count }" width="80" height="80"/></s:if></span>
  		</div>
</s:iterator>
</s:if>
<s:else>
	<div id="fwd_div">
	<textarea id="textarea_forward"></textarea>
	<input type="hidden" id="wbid" value="${o.wbid}"/>
	</div>
</s:else>
</div>
