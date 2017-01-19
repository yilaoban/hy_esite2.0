<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/WEB-INF/pagechip/iframe_header.jsp" %>
<script type="text/javascript">
	function addVedio(){
		var time = new Date().getTime();
		$("#videodiv").append('<p id="pvideo'+time+'"> <select id="svedio'+time+'"  name="dto.vids" style="width:180px;" onchange="changePic(this.value,this.id,\'${imgDomain }\')"><option value="-1">请选择视频</option> <s:iterator value="dto.list" var="addvideo" status="add1"> <option value="${addvideo.id}">${addvideo.title}</option> </s:iterator></select> <a href="javascript:void(0);" class="btn btn-info" id="avideo'+time+'" onclick="delVedio(this.id)">-视频</a> 位置:<input type="text" name="dto.ididxs" value="0" style="width: 28px" /> <img title="${fvideos.title}" id="imgvedio'+time+'" src="${imgDomain }${fvideos.picurl}" width="50" height="50"/></p>');
	}
	function delVedio(id){
	  var pid=id.substring(1)
	  $("#p"+pid).remove();
	}
	
	function changePic(v,i,domain){
	   var pid=i.substring(1)
	   var url=$("#h"+v).val();
	   $("#img"+pid).attr("src",domain+url); 
	}
</script>
<body class="iframe_body">
<form  id="myform">
<input type="hidden" name="dto.fid" value="${fid}" />
<div id="videodiv">
   <s:if test="dto.flist.size()>0">
       	<s:iterator value="dto.flist" var="fvideos" status="s">
       	     <input type="hidden" name="dto.vlvids" value="${fvideos.vlvid}" />
       	     <input type="hidden" name="dto.oldvids" value="${fvideos.id}" />
       	     <p id="pvideo<s:property value='#s.index'/>"> 
       	         <select name="dto.vids" style="width:180px;" id="svedio<s:property value='#s.index'/>" onchange="changePic(this.value,this.id,'${imgDomain }')" >
                      <s:iterator value="dto.list" var="videos" status="g1">
                           <option value="${videos.id}" <s:if test='#attr.fvideos.id==#attr.videos.id'> selected="selected" </s:if>>${videos.title}</option>
                       </s:iterator>
                 </select>
                <a href="javascript:void(0);" class="btn btn-info" id="avideo<s:property value='#s.index'/>" onclick="delVedio(this.id)">-视频</a>
                  位置:<input type="text" name="dto.ididxs" value="${fvideos.idx}" style="width: 28px" />
                <img title="${fvideos.title}" id="imgvedio<s:property value='#s.index'/>" src="${imgDomain }${fvideos.picurl}" width="50" height="50"/>
             </p>
       	</s:iterator>
       
   </s:if>
   <s:else>
    <p id="pvideoone" >
    <select name="dto.vids" style="width:180px;" id="svedioone" onchange="changePic(this.value,this.id,'${imgDomain }')">
        <option value="-1">请选择视频</option>
        <s:iterator value="dto.list" var="video" status="g1">
           <option value="${video.id}">${video.title}</option>
        </s:iterator>
    </select>
    <a href="javascript:void(0);" class="btn btn-info" id="avideoone" onclick="delVedio(this.id)">-视频</a>
    位置:<input type="text" name="dto.ididxs" value="0" style="width: 28px" />
    <img title="${fvideos.title}" id="imgvedioone" src="${imgDomain }${fvideos.picurl}" width="50" height="50"/>
    </p>
    
   </s:else>
   <s:iterator value="dto.list" var="v">
       <input type="hidden" id="h<s:property value='#v.id'/>" value="${imgDomain }${v.picurl}" />
   </s:iterator>
</div>
<div >
 <p>
     <a href="javascript:void(0);" class="btn btn-info" onclick="addVedio()">+视频</a>
 </p>
 <p align="center">
 
 <input type="button" id="save101" value="保存" >&nbsp;&nbsp;<input type="button" class="btn btn-fail" value="取消"  onclick="closeFrame()">
 </p>
 </div>
</form>
<script type="text/javascript">
$("#save101").click(function(){
	var chk_value =[]; 
	var check=true;   
	$('select[name="dto.vids"] option:selected').each(function(){
	   var value=$(this).val();
	   for(var i=0;i<chk_value.length;i++){
	       if(value==chk_value[i]){
	           parent.layer.alert("你选择了重复的视频");
	           check= false;
	           break;
	       }
	   }
	   chk_value.push(value);   
    });
    if(check){
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
});
</script>
</body>
