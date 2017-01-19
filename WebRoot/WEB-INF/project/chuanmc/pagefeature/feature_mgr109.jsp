<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/WEB-INF/pagechip/iframe_header.jsp" %>
<script type="text/javascript">
	function addPic(){
		var time = new Date().getTime();
		$("#picdiv").append('<p id="ppic'+time+'"> <select id="spic'+time+'" name="dto.pids" onchange="changePicture(this.value,this.id)"  style="width:180px;"><option value="-1">请选择图片</option> <s:iterator value="dto.list" var="addpic" status="add1"> <option value="${addpic.id}">${addpic.title}</option> </s:iterator></select> <a href="javascript:void(0);" class="btn btn-info" id="apic'+time+'" onclick="delPic(this.id)">-图片</a> 位置:<input type="text" name="dto.ididxs" value="0" style="width: 28px" /><img title="${addpic.title}" id="imgpic'+time+'" src="${imgDomain }${addpic.imgurl}" width="100" height="100"/></p>');
	}
	function delPic(id){
	  var pid=id.substring(1);
	  $("#p"+pid).remove();
	}
	function changePicture(v,i){
	   var pid=i.substring(1);
	   var url=$("#h"+v).val();
	   $("#img"+pid).attr("src",url); 
	}
</script>
<body class="iframe_body">
<form  id="myform">
<input type="hidden" name="dto.fid" value="${fid}" />
<div id="picdiv">
   <s:if test="dto.flist.size()>0">
       	<s:iterator value="dto.flist" var="fpics" status="s">
       	     <input type="hidden" name="dto.vlpids" value="${fpics.vlpid}" />
       	     <input type="hidden" name="dto.oldpids" value="${fpics.id}" />
       	     <p id="ppic<s:property value='#s.index'/>"> 
       	         <select id="spic<s:property value='#s.index'/>" name="dto.pids" onchange="changePicture(this.value,this.id)" style="width:180px;">
                      <s:iterator value="dto.list" var="pics" status="g1">
                           <option value="${pics.id}" <s:if test='#attr.fpics.id==#attr.pics.id'> selected="selected" </s:if>>${pics.title}</option>
                       </s:iterator>
                </select>
                <a href="javascript:void(0);" class="btn btn-info" id="apic<s:property value='#s.index'/>" onclick="delPic(this.id)">-图片</a>
                  位置:<input type="text" name="dto.ididxs" value="${fpics.idx}" style="width: 28px" />
                <img title="${fpics.title}" id="imgpic<s:property value='#s.index'/>" src="${imgDomain }${fpics.imgurl}" width="100" height="100"/>
             </p>
       	</s:iterator>
       
   </s:if>
   <s:else>
    <p id="ppicone" >
    <select name="dto.pids" id="spicone" onchange="changePicture(this.value,this.id)" style="width:180px;">
        <option value="-1">请选择图片</option>
        <s:iterator value="dto.list" var="pic" status="g1">
           <option value="${pic.id}">${pic.title}</option>
        </s:iterator>
    </select>
    <a href="javascript:void(0);" class="btn btn-info" id="apicone" onclick="delPic(this.id)">-图片</a>
    位置:<input type="text" name="dto.ididxs" value="0" style="width: 28px" />
     <img  id="imgpicone"  width="100" height="100"/>
     <img title="${fpics.title}" id="imgvedioone" src="${imgDomain }${fpics.imgurl}" width="50" height="50"/>
    </p>
    
   </s:else>
   <s:iterator value="dto.list" var="v">
       <input type="hidden" id="h<s:property value='#v.id'/>" value="${imgDomain }${v.imgurl}" />
   </s:iterator>
</div>
<div >
 <p>
     <a href="javascript:void(0);" class="btn btn-info" onclick="addPic()">+图片</a>
 </p>
 <p align="center">
 
 <input type="button" id="save108"  value="保存" >&nbsp;&nbsp;<input type="button" class="btn btn-fail" value="取消"  onclick="closeFrame()">
 </p>
 </div>
</form>
<script type="text/javascript">
		$("#save108").click(function(){
			var chk_value =[]; 
			var check=true;   
			$('select[name="dto.pids"] option:selected').each(function(){
			   var value=$(this).val();
			   for(var i=0;i<chk_value.length;i++){
			       if(value==chk_value[i]){
			           parent.layer.alert("你选择了重复的图片");
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
