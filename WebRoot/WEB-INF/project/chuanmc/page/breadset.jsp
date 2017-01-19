<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<form id="myform">
	<dl>
	 	<dt></dt>
		<dd>
			<label class="t1">首页</label>  >> <label class="t2">二级目录</label> >> <label class="t3">当前目录</label>
		</dd>
	</dl>
	<dl class="l1">
	 	<dt>首页名称：</dt>
		<dd>
			<input type="text" name="name1" class="text-medium" value="${jsonObj.name1}">
		</dd>
	</dl>
	<dl class="l1">
	 	<dt>链接：</dt>
		<dd>
			<input type="text" name="link1" class="text-long" value="${jsonObj.link1}">
		</dd>
	</dl>
	<dl class="l2" style="display: none">
	 	<dt>二级目录名称：</dt>
		<dd>
			<input type="text" name="name2" class="text-medium" value="${jsonObj.name2 }">
		</dd>
	</dl>
	<dl class="l2" style="display: none">
	 	<dt>链接：</dt>
		<dd>
			<input type="text" name="link2" class="text-long" value="${jsonObj.link2 }">
		</dd>
	</dl>
	<dl class="l3" style="display: none">
	 	<dt>当前目录名称：</dt>
		<dd>
			<input type="text" name="name3" class="text-medium" value="${jsonObj.name3 }">
		</dd>
	</dl>
	<dl class="l3" style="display: none">
	 	<dt>链接：</dt>
		<dd>
			<input type="text" name="link3" class="text-long" value="${jsonObj.link3 }">
		</dd>
	</dl>
	<dl>
	 	<dt></dt>
		<dd>
			<input type="button" class="btn btn-primary" value="保存" id="baocun">
			<input type="reset" class="btn" value="取消">
		</dd>
	</dl>
</form>
<script type="text/javascript">
$.fn.serializeObject = function()    
{    
   var o = {};    
   var a = this.serializeArray();    
   $.each(a, function() {    
       if (o[this.name]) {    
           if (!o[this.name].push) {    
               o[this.name] = [o[this.name]];    
           }    
           o[this.name].push(this.value || '');    
       } else {    
           o[this.name] = this.value || '';    
       }    
   });    
   return o;    
}; 

$(document).ready(function(){
	$(".t1").click(function(){
		$(".l1").show();
		$(".l2 , .l3").hide();
	});
	$(".t2").click(function(){
		$(".l2").show();
		$(".l1 , .l3").hide();
	});
	$(".t3").click(function(){
		$(".l3").show();
		$(".l1 , .l2").hide();
	});
	$("#baocun").click(function(){
		 var json = JSON.stringify($('#myform').serializeObject());
		$.post("breadsetsub.action",{"json":json,"ccid":'${ccid}'},function(data){
			if(data==1){
				layer.msg('创建成功!',{icon: 6, time: 2000},function(){
            		closeFrame();
        		});
			}else{
				layer.msg('创建失败，请重试!',{icon: 6, time: 2000});
			}
		});
	});
});
</script>