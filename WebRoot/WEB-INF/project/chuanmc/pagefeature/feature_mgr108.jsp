<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/WEB-INF/pagechip/iframe_header.jsp" %>
<script type="text/javascript">
    function checkChange(chkid){
        var id=chkid.substring(3);
        var value=$("#hrd"+id).val();
        if (typeof(value) == "undefined"||value==null||value=='') {
            value=0;
        }
        if(document.getElementById(""+chkid).checked){
           $("#"+chkid).after(' <input type="text" id="idx'+id+'" name="dto.ididxs" value="'+value+'" style="width: 20px" /> ');
        }else{
           $("#idx"+id).remove();
        }
	}
</script>
<body class="iframe_body">
<form  id="myform">
<input type="hidden" name="dto.fid" value="${fid}" />
<div>
    <p>
    <select name="dto.uploadid" onchange="changeUpload(this.form)">
        <option value="-1">请选择上传</option>
        <s:iterator value="dto.uploadlist" var="com" status="c">
         <option value="${com.id}" <s:if test='#attr.dto.upload.id==#attr.com.id'> selected="selected" </s:if>>${com.content}</option>
        </s:iterator>
    </select>
    </p>
     	<ul>
        <s:iterator value="dto.recordlist" var="record" status="c">
          <li class="existProduct" id="divrd<s:property value='#c.index'/>" >
              <img title="${record.content}" src="${imgDomain }${record.smallimg}" width="40" height="40"/>
              <input type="checkbox" id="chk<s:property value='#c.index'/>" name="dto.recordids" <s:if test='#attr.record.issel>0'>checked="checked"</s:if> onchange="checkChange(this.id)" value="${record.id}"/>
              <s:if test='#attr.record.issel>0'> <input type="text" id="idx<s:property value='#c.index'/>" name="dto.ididxs" value="${record.idx}" style="width: 20px" /></s:if>
              <input type="hidden" id="hrd<s:property value='#c.index'/>" value="${record.idx}" /> 
          </li>
        </s:iterator>
        <div style="clear: both"></div>
        </ul>
    
   
</div>
<div >
 <p align="center">
     <input type="button" id="save108"  value="保存">&nbsp;&nbsp;<input type="button" class="btn btn-fail" value="取消"  onclick="closeFrame()">
 </p>
 </div>
</form>
<script type="text/javascript">
		$("#save108").click(function(){
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
